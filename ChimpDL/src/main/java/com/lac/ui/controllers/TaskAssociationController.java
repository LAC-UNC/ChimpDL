package com.lac.ui.controllers;

import com.lac.activities.DLContents.TasksContent;
import com.lac.model.Model;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.TaskAssociationPanel;
import com.lac.ui.scrceens.tasks.TaskPanel;

public class TaskAssociationController extends ActionablePanelController<TaskAssociationPanel> {

	@Override
	public void nextAction() throws PetriNetException {
		saveAction();	
	}
	
	@Override
	public void renderAction() throws PetriNetException {
		baseComponent.getBodyPanel().removeAll();
		baseComponent.getTaskPanels().clear();
		for(TasksContent tasks : Model.getInstance().getDlContent().getTasks()){
			TaskPanel taskPanel = baseComponent.addNewTask();
			try {
				taskPanel.getHome().setTask(tasks.getInputTransitionName(), tasks.getOutputTransitionName(),
						tasks.getName(), tasks.getActivities());
			} catch (SecurityException | ClassNotFoundException e) {
				throw new PetriNetException(e.getMessage(),e);
			} 
		}
	}
	
	//TODO: check this functionality. The call to getHome.save() it is kind of award, shouldn't be doing everything in this single method 
	// instead of calling hierarchy to other ones ? 
	public void saveAction(){
		Model config = Model.getInstance();
		config.emptyTask();
		for(TaskPanel panel : baseComponent.getTaskPanels()){
			panel.getHome().save();
		}
	}
}
