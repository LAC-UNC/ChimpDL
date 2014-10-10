package com.lac.interpreter.mock;

import java.util.ArrayList;
import java.util.List;

import com.lac.activities.DLContents.ResourcesContent;
import com.lac.activities.DLContents.TasksContent;

public class SimpleJsonContainerInnerClass{
	private List<ResourcesContent> resources;
	private ArrayList<TasksContent> tasks;
	
	public SimpleJsonContainerInnerClass(){}

	public List<ResourcesContent> getResources() {
		return resources;
	}

	public void setResources(List<ResourcesContent> resources) {
		this.resources = resources;
	}

	public ArrayList<TasksContent> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<TasksContent> tasks) {
		this.tasks = tasks;
	}
	
}