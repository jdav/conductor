package com.malleamus.conductor.controller;

import java.util.Date;

import com.malleamus.conductor.model.ConductorRegistry;
import com.malleamus.conductor.model.LoginAttempt;
import com.malleamus.conductor.model.User;
import com.malleamus.speckle.Configuration;
import com.malleamus.speckle.Context;
import com.malleamus.speckle.InvitationView;
import com.malleamus.speckle.PromptingView;
import com.malleamus.speckle.SpeckleException;
import com.malleamus.speckle.UI;
import com.malleamus.speckle.UseCase;
import com.malleamus.vellum.Registerable;
import com.malleamus.vellum.Registerables;
import com.malleamus.vellum.RegistrationNumber;
import com.malleamus.vellum.RegistrationNumbers;

public class Authenticate implements UseCase {
	
	private Context context = null;
	private Configuration config = null;
	private UI ui = null;
	private int maxLoginTries = 0;
	private boolean loggedIn = false;
	
	@Override
	public void setContext(Context context) {
		this.context = context;
		this.config = context.getConfiguration();
		this.ui = context.getUI();
		maxLoginTries = Integer.parseInt(config.getProperty("MAX_LOGIN_TRIES"));
	}
	
	@Override
	public Context getContext() {
		return context;
	}
	
	@Override
	public boolean isAuthenticated() throws SpeckleException {
		return true;
	}
	
	@Override
	public boolean isAuthorized() throws SpeckleException {
		return true;
	}

	@Override
	public PromptingView getOpening() throws SpeckleException {
		return null;
	}
	
	@Override
	public InvitationView getRebuff() throws SpeckleException {
		throw new SpeckleException("Authentication use case should never be rebuffed.");
	}
	
	@Override
	public InvitationView getClosing() throws SpeckleException {
		throw new SpeckleException("Authentication use case should never show closing view.");
	}
	
	@Override
	public void execute() throws SpeckleException {
		try {
			String userIDString = context.getProperty("USER_ID");
			String passwordString = context.getProperty("PASSWORD");
			context.remove("USER_ID");
			context.remove("PASSWORD");
			
			//Try to find the user based on this input...
			ConductorRegistry registry = ConductorRegistry.instance();
			User user = new User();
			RegistrationNumbers rns = 
				registry.find(user.getObjectType(), "USER_ID", userIDString);
			
			//If you find the user...
			if (rns.size() == 1) {
				RegistrationNumber rn = rns.get(0);
				user = (User) registry.checkOut(rn);
				
				LoginAttempt thisLoginAttempt = new LoginAttempt();
				//If the login is successful...
				if (!user.isLockedOut() && user.getPassword().equals(passwordString)) {
					thisLoginAttempt.setSuccessful(true);
					user.getLoginAttempts().add(thisLoginAttempt);
					loggedIn = true;
					context.put("USER", user);
				} else {
					thisLoginAttempt.setSuccessful(false);
					
					//...determine if the number of failed login attempts 
					//in the last thirty minutes has exceeded the maximum
					Registerables loginAttempts = user.getLoginAttempts();
					loginAttempts.add(thisLoginAttempt);
					int failures = 0;
					for (Registerable loginAttempt : loginAttempts) {
						LoginAttempt la = (LoginAttempt) loginAttempt;
						
						//if this login attempt was within the
						//last thirty minutes and failed...
						if (la.getDateAndTime().compareTo(new Date()) < 1800000 &&
							!la.isSuccessful()) {
							if (++failures > maxLoginTries) {
								break; //Don't keep iterating if you don't need to...
							}
						}
					}
					
					//lock the user out if they've exceeded the
					//max failures within a thirty minute period
					if (failures >= maxLoginTries) {
						user.lockOut();
					}
				}
				
				registry.register(thisLoginAttempt);
				registry.checkIn(user);
			}
				
		} catch (Throwable t) {
			throw new SpeckleException(t);
		}
	}
}
