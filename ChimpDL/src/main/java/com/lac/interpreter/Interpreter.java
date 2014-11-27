package com.lac.interpreter;

import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.lac.activities.Activity;
import com.lac.activities.Task;
import com.lac.activities.DLContents.ActivityContent;
import com.lac.activities.DLContents.DLContent;
import com.lac.activities.DLContents.ResourcesContent;
import com.lac.activities.DLContents.TasksContent;
import com.lac.parsers.JsonParser;
import com.lac.petrinet.configuration.ConfigurationReader;
import com.lac.petrinet.configuration.providers.PNMLConfigurationReader;
import com.lac.petrinet.core.PetriNet;
import com.lac.petrinet.exceptions.PetriNetException;

public class Interpreter {
	private ConfigurationReader configReader;
	private PetriNet petriNet;
	private DLContent objectDescription;
	
	public void boot(String pnmlPath, String confPath) throws PetriNetException{
		// Get the pnml configuration 
		readPnmlFile(pnmlPath);	
		start(confPath, false);
	}
	
	private void setUpSystem() throws PetriNetException {
		createTasks(createResorces());
	}
	
	private Map<String, Object> createResorces() throws PetriNetException {
		// create the resources objects
		Map<String, Object> resources = new HashMap<String,Object>();
		for (ResourcesContent resource : objectDescription.getResources()){
			Object resourceObj;
			try {
				resourceObj = Class.forName(resource.getClassName()).newInstance();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				throw new PetriNetException(e.getMessage(),e);
			}
			
			if(!resources.containsKey(resource.getImplementationName()))
				resources.put(resource.getImplementationName(), resourceObj);
		}
		
		return resources;
	}
	
	private void createTasks(Map<String, Object> resources) throws PetriNetException {
		// create the Tasks objects 
		for (TasksContent task: objectDescription.getTasks()){
			Task taskObj = new Task(petriNet, task.getOutputTransitionName());
			
			// create all the activities and add those to the task.
			for(ActivityContent activity : task.getActivities()) {
				Activity activityObj;
				Object resourceInstance = resources.get(activity.getObj());
				// TODO: select here the method based on the parameters we will use (to implement) 
				Method method; 
				try {
					method = resourceInstance.getClass().getDeclaredMethod(activity.getMethod()) ;
				} catch (Exception e) {
					throw new PetriNetException(e.getMessage(),e);
				} 
				
				activityObj  = new Activity(resourceInstance, method);
				taskObj.addActivity(activityObj);
			}
			// Assign the dummy to the inputTransition and the task to the container
			for(String inputTransitionName : task.getInputTransitionName()){
				petriNet.assignDummy(inputTransitionName, taskObj);
			}
		}
	}	
	
	public PetriNet readPnmlFile(String pnmlPath) throws PetriNetException{
		// Get the pnml configuration 
		configReader = new PNMLConfigurationReader();
		petriNet = configReader.loadConfiguration(pnmlPath);
		return petriNet;
	}
	
	public void start(String ConfigFileLocation, boolean isDefaultName) throws PetriNetException{
		JsonParser parser = new JsonParser();

		try {
			if(isDefaultName)
				objectDescription = parser.parse((new ChimpDLFile()).getDescription(ConfigFileLocation + "./configuration.conf"));
			else
				objectDescription = parser.parse((new ChimpDLFile()).getDescription(ConfigFileLocation ));
		} catch (PetriNetException e) {
			throw new PetriNetException(e.getMessage(),e);
		}
		
		// create the resources and tasks that will be used
		setUpSystem();
		//start  the application
		petriNet.startListening();
	}
	
	
	public static String getJarpath() throws URISyntaxException {
		String uri;
		uri = Interpreter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		while(uri.contains(".jar")){
			uri = uri.substring(0,  uri.lastIndexOf("/"));
		}
		return uri;
	}
	
}
