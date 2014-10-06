package com.lac.activities;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class Activity implements Callable<Void> {

	private Object instance;
	private Method method;
	
	@Override
	public Void call() throws Exception {
		method.invoke(instance);
		return null;
	}

}
