package com.malleamus.conductor.model;

import com.malleamus.vellum.RegisterableFactory;
import com.malleamus.vellum.VellumException;

public class ObjectFactory extends RegisterableFactory {

	public ObjectFactory() throws VellumException {
		super();
	}

	@Override
	protected void build() throws VellumException {
		this.register(User.class);
	}

}
