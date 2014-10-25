package com.lac.activities;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.lac.interpreter.mock.Class0;

import static org.mockito.Mockito.mock;

public class ActivityTest {

	@Test
	public void validCall() throws Exception{
		Class0 instance = new Class0();
		Method method = instance.getClass().getMethod("method0");
		Method method1 = instance.getClass().getMethod("method1");
		
		Activity act = new Activity(instance, method);
		act.call();
		Assert.assertTrue(instance.isMethod0Executed());
		Assert.assertFalse(instance.isMethod1Executed());
		
		instance = new Class0();
		act = new Activity(instance, method1);
		act.call();
		Assert.assertFalse(instance.isMethod0Executed());
		Assert.assertTrue(instance.isMethod1Executed());
	}
	
	@Test(expectedExceptions = Exception.class)
	public void invalidCall() throws Exception{
		Class0 instance = mock(Class0.class);
		Method method = instance.getClass().getMethod("NotValid");
		
		Activity act = new Activity(instance, method);
		act.call();
		Assert.assertFalse(instance.isMethod0Executed());
		Assert.assertFalse(instance.isMethod1Executed());
	}
}
