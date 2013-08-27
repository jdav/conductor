package com.malleamus.conductor.model;

import java.util.Date;

import com.malleamus.vellum.Persistings;
import com.malleamus.vellum.Registerable;
import com.malleamus.vellum.RegistrationNumber;
import com.malleamus.vellum.VellumException;

public class LoginAttempt implements Registerable {
	
	private Date dateAndTime = null;
	private boolean successful = false;

	@Override
	public boolean hasRegistrationNumber() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RegistrationNumber getRegistrationNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRegistrationNumber(RegistrationNumber rn) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
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

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

}
