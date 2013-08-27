package com.malleamus.conductor.controller;

import com.malleamus.conductor.model.User;
import com.malleamus.speckle.Context;
import com.malleamus.speckle.InvitationView;
import com.malleamus.speckle.PromptingView;
import com.malleamus.speckle.SpeckleException;
import com.malleamus.speckle.UseCase;
import com.malleamus.speckle.View;

public class Review implements UseCase {
	
	private User user = null;
	private Context context = null;

	@Override
	public void setContext(Context context) throws SpeckleException {
		this.context = context;
	}

	@Override
	public Context getContext() throws SpeckleException {
		return context;
	}

	@Override
	public boolean isAuthenticated() throws SpeckleException {
		return (user = (User) context.get("USER")) != null;
	}
	
	@Override
	public boolean isAuthorized() throws SpeckleException {
		return true;
	}

	@Override
	public PromptingView getOpening() throws SpeckleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InvitationView getRebuff() throws SpeckleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() throws SpeckleException {
		// TODO Auto-generated method stub

	}

	@Override
	public InvitationView getClosing() throws SpeckleException {
		// TODO Auto-generated method stub
		return null;
	}


}
