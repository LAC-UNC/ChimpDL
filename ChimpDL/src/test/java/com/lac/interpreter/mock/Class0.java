package com.lac.interpreter.mock;

import com.lac.annotations.Resource;

@Resource
public class Class0 {
	private boolean method0Executed = false;
	private boolean method1Executed = false;
	
	public void method0(){
		method0Executed = true;
	}
	
	public void method1(){
		method1Executed = true;
	}

	public boolean isMethod0Executed() {
		return method0Executed;
	}

	public boolean isMethod1Executed() {
		return method1Executed;
	}
}
