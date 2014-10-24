package com.lac.userentry;

import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import com.lac.activities.DLContents.ActivityContent;
import com.lac.activities.DLContents.DLContent;
import com.lac.activities.DLContents.TasksContent;
import com.lac.interpreter.ChimpDL;
import com.lac.interpreter.ChimpDLImpl;
import com.lac.petrinet.core.PetriNet;
import com.lac.petrinet.exceptions.PetriNetException;

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
			// Solo se accede a la zona sincronizada
			// cuando la instancia no esta creada
			synchronized(ConfigurationEntryHolder.class) {
				// En la zona sincronizada seria necesario volver
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
	
	public void addUserEntryResource(String implementationName, Class<?> clazz) throws PetriNetException{
		userSelection.addResource(implementationName,clazz.getCanonicalName());
		resourceInstancesMap.put(implementationName, clazz);
		ChimpDLImpl chimpDl = new ChimpDLImpl();
		try {
			chimpDl.saveConfiguration(getJarpath(), userSelection);
		} catch (URISyntaxException e) {
			throw new PetriNetException(e.getMessage(),e);
		}
		
	}
	
	public void addtask( List<String> inputTransitionName,String outputTransitionName, List<ActivityContent> activities, 
			String name ) throws PetriNetException{
		TasksContent task = new TasksContent();
		task.setActivities(activities);
		task.setInputTransitionName(inputTransitionName);
		task.setName(name);
		task.setOutputTransitionName(outputTransitionName);
		userSelection.addTask(task);
		ChimpDLImpl chimpDl = new ChimpDLImpl();
		try {
			chimpDl.saveConfiguration(getJarpath(), userSelection);
		} catch (URISyntaxException e) {
			throw new PetriNetException(e.getMessage(),e);
		}
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
	
	private String getJarpath() throws URISyntaxException {
		String uri;
		uri = ChimpDLImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		while(uri.contains(".jar")){
			uri = uri.substring(0,  uri.lastIndexOf("/"));
		}
		return uri;
	}


}
