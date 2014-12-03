package com.lac.ui.controllers;

import com.lac.ui.mainscreens.ObserverAssignmentPanel;

public class ObserverAssociationController extends ActionablePanelController<ObserverAssignmentPanel> {

	/*@Override
	public void renderAction() throws PetriNetException {
		baseComponent.getBodyPanel().removeAll();
		baseComponent.getObserverPanels().clear();
		for(TasksContent tasks : Model.getInstance().getDlContent().getTasks()){
			ObserverPanel taskPanel = baseComponent.addNewObserver();
			try {
				taskPanel.getHome().setTask(tasks.getInputTransitionName(), tasks.getOutputTransitionName(),
						tasks.getName(), tasks.getActivities());
			} catch (SecurityException | ClassNotFoundException e) {
				throw new PetriNetException(e.getMessage(),e);
			} 
		}
	}*/
}
