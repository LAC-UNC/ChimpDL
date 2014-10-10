package com.lac.activities;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class Activity implements Callable<Void> {

	public Activity(Object instance, Method method) {
		super();
		this.instance = instance;
		this.method = method;
	}


	private Object instance;
	private Method method;
	
	
	@Override
	public Void call() throws Exception {
		method.invoke(instance);
		return null;
	}

}
