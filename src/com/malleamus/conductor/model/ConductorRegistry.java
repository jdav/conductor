package com.malleamus.conductor.model;

import com.malleamus.vellum.Registry;
import com.malleamus.vellum.VellumException;

import java.io.File;

public class ConductorRegistry extends Registry {
	
	private static ConductorRegistry instance = null;
	
	public static ConductorRegistry instance() throws VellumException {
		return (instance == null ? (instance = new ConductorRegistry()) : instance);
	}
	
	private ConductorRegistry() throws VellumException {
		//Get Registry from persistent storage
		super(new File("C:\\\\Users\\jdav\\conductorregistry.txt"), new ObjectFactory());
		try {
			load();
		} catch (Exception e) {
			throw new VellumException(e);
		}
		buildIndices();
	}
}

