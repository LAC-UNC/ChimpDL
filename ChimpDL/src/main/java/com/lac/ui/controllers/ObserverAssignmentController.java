package com.lac.ui.controllers;

import java.util.List;

import com.lac.activities.DLContents.ObserverContent;
import com.lac.model.Model;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.ObserverAssignmentPanel;
import com.lac.ui.scrceens.observers.ObserverPanel;

public class ObserverAssignmentController extends ActionablePanelController<ObserverAssignmentPanel> {

	@Override
	public void renderAction() throws PetriNetException {
		baseComponent.getBodyPanel().removeAll();
		baseComponent.getObserverPanels().clear();
		for(ObserverContent obs : Model.getInstance().getDlContent().getObservers()){
			ObserverPanel obsPanel = baseComponent.addNewObserver();
			try {
				List<String> a = obs.getInformedTransitions();
				ObserverController b = obsPanel.getController();
				b.setObserverConfiguration(a);
				//obsPanel.getController().setObserverConfiguration(a);
			} catch (SecurityException | ClassNotFoundException e) {
				throw new PetriNetException(e.getMessage(),e);
			} 
		}
	}
	
	@Override
	public void nextAction() throws PetriNetException {
		saveAction();	
	}
	
	//TODO: check this functionality. The call to getHome.save() it is kind of award, shouldn't be doing everything in this single method 
	// instead of calling hierarchy to other ones ? 
	public void saveAction(){
		Model config = Model.getInstance();
		config.emptyObservers();
		for(ObserverPanel panel : baseComponent.getObserverPanels()){
			panel.getController().save();
		}
	}

	public void erase(ObserverPanel toErase) {
		baseComponent.eraseObserverPanel(toErase);		
	}
}
