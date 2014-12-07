package com.lac.ui.controllers;

import java.util.List;

import com.lac.model.Model;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.ErrorDialog;
import com.lac.ui.scrceens.observers.ObserverPanel;

public class ObserverController extends ActionablePanelController<ObserverPanel> {
	
	private ObserverAssignmentController parentController;
	
	public ObserverController(ObserverAssignmentController parentController) {
		super();
		
		this.parentController = parentController;
	}

	//TODO: fix the List of transition to be a real list not just one ! the get(0) is a major issue for the future
	public void setObserverConfiguration(List<String> list) throws SecurityException, ClassNotFoundException{
		baseComponent.setObservedInformedTransitions(list);
	}
	
	public void save(){
		Model config = Model.getInstance();
		try {
			config.addObserver(baseComponent.getObservedInformedTransitions());
		} catch (PetriNetException e) {
			new ErrorDialog(e);
		}
	}

	public void deleteThisObserver() {
		this.parentController.erase(baseComponent);
	}	
}
