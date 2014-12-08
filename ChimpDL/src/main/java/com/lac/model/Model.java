package com.lac.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lac.activities.DLContents.ActivityContent;
import com.lac.activities.DLContents.DLContent;
import com.lac.activities.DLContents.ObserverContent;
import com.lac.activities.DLContents.TasksContent;
import com.lac.petrinet.core.PetriNet;
import com.lac.petrinet.exceptions.PetriNetException;

//TODO: check concurrency in this class.
public class Model {

	private static Model INSTANCE = null;

	private PetriNet petriNet;
	private DLContent dlContent ;

	// Private constructor suppresses 
	private Model(){
		dlContent = new DLContent();
	}

	private static void createInstance() {
		if (INSTANCE == null) {
			// Solo se accede a la zona sincronizada
			// cuando la instancia no esta creada
			synchronized(Model.class) {
				// En la zona sincronizada seria necesario volver
				// a comprobar que no se ha creado la instancia
				if (INSTANCE == null) { 
					INSTANCE = new Model();
				}
			}
		}
	}

	public static Model getInstance() {
		if (INSTANCE == null) createInstance();
		return INSTANCE;
	}
	
	// Methods for manage the configuration. 
	public void setPetriNet(PetriNet petriNet){
		this.petriNet = petriNet;
	}
	
//	public void addResourceInstance(String name, ResourceInstances resource){
//		resourceInstancesMap.put(name, resource);
//		this.notifyObservers();
//	}
	
	public PetriNet getPetriNet() {
		return petriNet;
	}

	public void addUserEntryResource(String implementationName, Class<?> clazz) throws PetriNetException{
		dlContent.addResource(implementationName,clazz.getCanonicalName());
	}
	
	public void addtask( List<String> inputTransitionName,String outputTransitionName, List<ActivityContent> activities, 
			String name ) throws PetriNetException{
		TasksContent task = new TasksContent();
		task.setActivities(activities);
		task.setInputTransitionName(inputTransitionName);
		task.setName(name);
		task.setOutputTransitionName(outputTransitionName);
		dlContent.addTask(task);
		/*ChimpDLFile chimpDl = new ChimpDLFile();
		try {
			chimpDl.saveConfiguration(getJarpath(), dlContent);
		} catch (URISyntaxException e) {
			throw new PetriNetException(e.getMessage(),e);
		}*/
	}
	
	public void addObserver( List<String> informedTransitions) throws PetriNetException{
		ObserverContent observer = new ObserverContent();
		observer.setInformedTransitions(informedTransitions);
		dlContent.addObserver(observer);
		petriNet.addTransitionNameGroup(informedTransitions);
		/*ChimpDLFile chimpDl = new ChimpDLFile();
		try {
			chimpDl.saveConfiguration(getJarpath(), dlContent);
		} catch (URISyntaxException e) {
			throw new PetriNetException(e.getMessage(),e);
		}*/
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

	public DLContent getDlContent() {
		return dlContent;
	}
	
	public void setDlContent(DLContent dlContent) {
		this.dlContent = dlContent;
	}

	public void emptyTask(){
		dlContent.emptyTasks();
	}
	
	public void emptyObservers(){
		dlContent.emptyObservers();
	}
	
//	private String getJarpath() throws URISyntaxException {
//		String uri;
//		uri = Model.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
//		while(uri.contains(".jar")){
//			uri = uri.substring(0,  uri.lastIndexOf("/"));
//		}
//		return uri;
//	}
}
