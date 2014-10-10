package com.lac.interpreter;

import java.util.HashMap;
import java.util.Map;

import com.lac.activities.Task;

public class ResourcesTasksContainer {

	private Map<String, Object> resources = new HashMap<String,Object>();
	private Map<String, Task> tasks = new HashMap<String,Task>();
	
	public boolean addResources(String name, Object resource){
		if( resources.containsKey(name))
			return false;
		
		resources.put(name, resource);
		return true;
	}
	
	public boolean addTask(String name, Task task){
		if( tasks.containsKey(name))
			return false;
		
		tasks.put(name, task);
		return true;
	}
	
	public Object getResource(String instanceName){
		return resources.get(instanceName);
	}

	public Task getTask(String taskName) {
		return tasks.get(taskName);
	}
}
