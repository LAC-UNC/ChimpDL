package com.lac.activities.DLContents;

import java.util.ArrayList;
import java.util.List;
/**
 * Representation of the json which contains all the configuration needed for the running. 
 * @author icaio
 *
 */
public class DLContent {
	private List<ResourcesContent> resources;
	private List<TasksContent> tasks;
	
	public DLContent(){
		resources = new ArrayList<ResourcesContent>();
		tasks = new ArrayList<TasksContent>();
	}
	
	public List<ResourcesContent> getResources() {
		return resources;
	}

	public void setResources(List<ResourcesContent> resources) {
		this.resources = resources;
	}

	public List<TasksContent> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<TasksContent> tasks) {
		this.tasks = tasks;
	}
	
	public void addResource(String InstanceName, String clazz){
		ResourcesContent resource = new ResourcesContent();
		resource.setImplementationName(InstanceName);
		resource.setClassName(clazz);
		if(resources  == null){
			resources = new ArrayList<ResourcesContent>();
		}
		resources.add(resource);
	}
	
	public void addTask(TasksContent task){
		if(tasks == null){
			tasks = new ArrayList<TasksContent>();
		}
		tasks.add(task);
	}
	
	public void emptyTask(){
		tasks = new ArrayList<TasksContent>();
	}
}
