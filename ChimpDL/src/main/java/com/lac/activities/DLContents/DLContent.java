package com.lac.activities.DLContents;

import java.util.ArrayList;
import java.util.List;

public class DLContent {
	private List<ResourcesContent> resources;
	private ArrayList<TasksContent> tasks;
	
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
