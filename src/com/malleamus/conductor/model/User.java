package com.malleamus.conductor.model;

import com.malleamus.vellum.Persistings;
import com.malleamus.vellum.Registerable;
import com.malleamus.vellum.Registerables;
import com.malleamus.vellum.RegistrationNumber;
import com.malleamus.vellum.VellumException;

public class User implements Registerable {
	
	private RegistrationNumber rn = null;
	private String loginID = null;
	private String password = null;
	private String startOfCurrentLoginSession = null;
	private int numberFailedLoginAttempts = 0;
	private boolean lockedOut = false;
	private Registerables loginAttempts = new Registerables();

	@Override
	public boolean hasRegistrationNumber() {
		return rn != null;
	}

	@Override
	public RegistrationNumber getRegistrationNumber() {
		return rn;
	}

	@Override
	public void setRegistrationNumber(RegistrationNumber rn) {
		this.rn = rn;
	}

	@Override
	public String getObjectType() {
		return "USER";
	}

	@Override
	public boolean isValid() {
		return false;
	}

	@Override
	public void setState(Persistings props) throws VellumException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Persistings getState() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLockedOut() {
		return lockedOut;
	}

	public void lockOut() {
		this.lockedOut = true;
	}

	public String getClockStartTime() {
		return startOfCurrentLoginSession;
	}

	public void setStartOfCurrentLoginSession(String lastLoginAttempt) {
		this.startOfCurrentLoginSession = lastLoginAttempt;
	}

	public int loginFailureCount() {
		return numberFailedLoginAttempts;
	}

	public void setLoginFailureCount(int numberFailedLoginAttempts) {
		this.numberFailedLoginAttempts = numberFailedLoginAttempts;
	}

	public Registerables getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(Registerables loginAttempts) {
		this.loginAttempts = loginAttempts;
	}
}
