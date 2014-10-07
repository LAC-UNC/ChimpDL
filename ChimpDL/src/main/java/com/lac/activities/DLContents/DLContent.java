package com.lac.activities.DLContents;

import java.util.ArrayList;

public class DLContent {
	ArrayList<ResourcesContent> resources = new ArrayList<ResourcesContent>();
	ArrayList<TasksContent> tasks= new ArrayList<TasksContent>();
	
	public ArrayList<ResourcesContent> getResourcesContentList() {
		return resources;
	}
	public void setResourcesContentList(
			ArrayList<ResourcesContent> resourcesContentList) {
		this.resources = resourcesContentList;
	}
	public ArrayList<TasksContent> getMappingContentList() {
		return tasks;
	}
	public void setMappingContentList(ArrayList<TasksContent> mappingContentList) {
		this.tasks = mappingContentList;
	}
	
	

	
}
