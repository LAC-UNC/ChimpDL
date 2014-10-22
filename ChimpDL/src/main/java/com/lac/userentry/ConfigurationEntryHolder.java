package com.lac.userentry;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import com.lac.activities.DLContents.ActivityContent;
import com.lac.activities.DLContents.DLContent;
import com.lac.activities.DLContents.TasksContent;
import com.lac.petrinet.core.PetriNet;

//TODO: check concurrency in this class.
public class ConfigurationEntryHolder extends Observable{

	private static ConfigurationEntryHolder INSTANCE = null;

	private PetriNet petriNet;
	private Map<String,Class<?>> resourceInstancesMap ;
	private DLContent userSelection ;

	// Private constructor suppresses 
	private ConfigurationEntryHolder(){
		userSelection = new DLContent();
		resourceInstancesMap = new HashMap<String, Class<?>>(); 
	}

	private static void createInstance() {
		if (INSTANCE == null) {
			// Sólo se accede a la zona sincronizada
			// cuando la instancia no está creada
			synchronized(ConfigurationEntryHolder.class) {
				// En la zona sincronizada sería necesario volver
				// a comprobar que no se ha creado la instancia
				if (INSTANCE == null) { 
					INSTANCE = new ConfigurationEntryHolder();
				}
			}
		}
	}

	public static ConfigurationEntryHolder getInstance() {
		if (INSTANCE == null) createInstance();
		return INSTANCE;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException(); 
	}
	
	
	// Methods for manage the configuration. 
	public void setPetriNet(PetriNet petriNet){
		this.petriNet = petriNet;
		notifyObservers();
	}
	
//	public void addResourceInstance(String name, ResourceInstances resource){
//		resourceInstancesMap.put(name, resource);
//		this.notifyObservers();
//	}
	
	public void addUserEntryResource(String implementationName, Class<?> clazz){
		userSelection.addResource(implementationName,clazz.getCanonicalName());
		resourceInstancesMap.put(implementationName, clazz);
	}
	
	public void addtask( List<String> inputTransitionName,String outputTransitionName, List<ActivityContent> activities, 
			String name ){
		TasksContent task = new TasksContent();
		task.setActivities(activities);
		task.setInputTransitionName(inputTransitionName);
		task.setName(name);
		task.setOutputTransitionName(outputTransitionName);
		userSelection.addTask(task);
	}
	
	public Set<String> getInformedTransitions(){
		if(petriNet != null)
			return petriNet.getInformedNameList();
		else 
			return new HashSet<String>();
	}
	
	public Set<String> getFiredTransitions(){
		if(petriNet != null)
			return petriNet.getFiredTransitions();
		else
			return new HashSet<String>();
	}
	
	public Set<String> getResourceInstances(){
		return resourceInstancesMap.keySet();
	}
	
	public Method[] getDeclaredMethod(String instanceName){
		return resourceInstancesMap.get(instanceName).getDeclaredMethods();
	}

	public DLContent getUserSelection() {
		return userSelection;
	}
	
	public void emptyTask(){
		userSelection.emptyTask();
	}

}
