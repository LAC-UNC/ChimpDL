package com.lac.activities.DLContents;

import java.util.List;

public class TasksContent {

	private List<String> inputTransitionName;
	private String outputTransitionName;
	private List<ActivityContent> activities;
	private String name; 
	
	public List<String> getInputTransitionName() {
		return inputTransitionName;
	}
	public void setInputTransitionName(List<String> inputTransitionName) {
		this.inputTransitionName = inputTransitionName;
	}
	public String getOutputTransitionName() {
		return outputTransitionName;
	}
	public void setOutputTransitionName(String outputTransitionName) {
		this.outputTransitionName = outputTransitionName;
	}
	public List<ActivityContent> getActivities() {
		return activities;
	}
	public void setActivities(List<ActivityContent> activities) {
		this.activities = activities;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
