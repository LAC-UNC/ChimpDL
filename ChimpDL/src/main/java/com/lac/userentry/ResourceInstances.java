package com.lac.userentry;

import java.util.Observable;

public class ResourceInstances extends Observable {
	
	Class<?> clazz;
	
	String instanceName;
	
	public ResourceInstances(String name, Class<?> clazz){
		this.clazz = clazz;
		this.instanceName = name;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	
	
}
