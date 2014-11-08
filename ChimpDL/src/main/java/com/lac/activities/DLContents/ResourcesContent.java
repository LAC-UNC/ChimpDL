package com.lac.activities.DLContents;

public class ResourcesContent {

	private String implementationName;
	private String className;
	
	public ResourcesContent(){}
	
	public ResourcesContent(String implementationName, String className){
		this.implementationName = implementationName;
		this.className = className;
	}
	
	public String getImplementationName() {
		return implementationName;
	}
	public void setImplementationName(String implementationName) {
		this.implementationName = implementationName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
