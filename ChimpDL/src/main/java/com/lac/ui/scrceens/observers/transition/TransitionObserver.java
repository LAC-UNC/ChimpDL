package com.lac.ui.scrceens.observers.transition;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lac.model.Model;
import com.lac.ui.mainscreens.ErrorDialog;
import com.lac.ui.scrceens.observers.ObserverTransitionSelector;

public class TransitionObserver  {

	private static TransitionObserver INSTANCE ;
	private List<ObserverTransitionSelector> listeners = new ArrayList<ObserverTransitionSelector>();
	private Set<String> transitionsNotSelected = new HashSet<String>();
	

	public synchronized void updateSelected( String	transitionName , int eventType, ObserverTransitionSelector selector) {
		int i = 1;
		if(transitionName == null )
			return;
		if(transitionName.isEmpty() )
			return;
		switch(eventType){
		case ItemEvent.SELECTED:
			transitionsNotSelected.remove(transitionName);
			for(ObserverTransitionSelector panel : listeners ){
				if(!panel.equals(selector)){
					panel.updateRemovingTransitions(transitionName);
				}
			}
			break;
		case ItemEvent.DESELECTED:
			transitionsNotSelected.add(transitionName);
			for(ObserverTransitionSelector panel : listeners ){
				if(!panel.equals(selector)){
					panel.updateAddingTransitioins(transitionName);
				}
			}
			break;
		default:
			new ErrorDialog("Transition combobox thrown an error. ItemEvent unkown.");
		}

	}
	
	public synchronized List<String> addObserver(ObserverTransitionSelector selectorPanel){
		this.listeners.add(selectorPanel);
		List<String> temporalList = new ArrayList<String>();
		temporalList.addAll(transitionsNotSelected);
		return temporalList;
	}
	
	public synchronized void removeObserver(ObserverTransitionSelector selectorPanel){
		this.listeners.remove(selectorPanel);
	}
	
	public void setTransitionsNotSelected(List<String> transitionsNotSelected) {
		for(ObserverTransitionSelector selector : this.listeners){
			selector.deleteAction();
		}
		this.transitionsNotSelected.removeAll(transitionsNotSelected);
		this.transitionsNotSelected.addAll(transitionsNotSelected);
	}
	
	private TransitionObserver(){
		this.transitionsNotSelected.addAll( Model.getInstance().getInformedTransitions());
	}

	private static void createInstance() {
		if (INSTANCE == null) {
			// Solo se accede a la zona sincronizada
			// cuando la instancia no esta creada
			synchronized(Model.class) {
				// En la zona sincronizada seria necesario volver
				// a comprobar que no se ha creado la instancia
				if (INSTANCE == null) { 
					INSTANCE = new TransitionObserver();
				}
			}
		}
	}

	public static TransitionObserver getInstance() {
		if (INSTANCE == null) createInstance();
		return INSTANCE;
	}
	
	public Object clone() throws CloneNotSupportedException {
    	throw new CloneNotSupportedException(); 
	}
}
