package com.lac.parsers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.lac.activities.Activity;
import com.lac.activities.Task;
import com.lac.activities.DLContents.ActivityContent;
import com.lac.activities.DLContents.DLContent;
import com.lac.activities.DLContents.ResourcesContent;
import com.lac.activities.DLContents.TasksContent;
import com.lac.interpreter.ResourcesTasksContainer;
import com.lac.petrinet.core.PetriNet;
import com.lac.petrinet.exceptions.PetriNetException;

/**
 * TODO: check if we can remove the container. 
 * @author icaio
 *
 */
public class FunctionalParser {
	private PetriNet petriNet;
	
	
	
	public FunctionalParser(PetriNet petriNet){
		this.petriNet = petriNet;
	}
	
	public ResourcesTasksContainer parseAndCreate(String jsonContent) throws PetriNetException {
		ResourcesTasksContainer container = new ResourcesTasksContainer();
		// get all the description of the resources and tasks that will be used. 
		DLContent objectDescription ;
		try{
			objectDescription = getObjectsDescription(jsonContent);
		}catch(Exception e){
			throw new PetriNetException(e.getMessage(),e);
		}
		
		
		// create the resources objects
		for (ResourcesContent resource : objectDescription.getResources()){
			Object resourceObj;
			try {
				resourceObj = Class.forName(resource.getClassName()).newInstance();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				throw new PetriNetException(e.getMessage(),e);
			}
			container.addResources(resource.getImplementationName(), resourceObj);
		}
		
		// create the Tasks objects 
		for (TasksContent task: objectDescription.getTasks()){
			Task taskObj = new Task(petriNet, task.getOutputTransitionName());
			
			// create all the activities and add those to the task.
			for(ActivityContent activity : task.getActivities()) {
				Activity activityObj ;
				Object resourceInstance = container.getResource(activity.getObj());
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
			container.addTask(task.getName(), taskObj);
		}
		return container;
	}
	
	@SuppressWarnings("unused")
	private DLContent getObjectsDescription(File jsonFile) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonFile, DLContent.class);
	}
	
	public DLContent getObjectsDescription(String jsonFileContent) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonFileContent, DLContent.class);
	}
}
