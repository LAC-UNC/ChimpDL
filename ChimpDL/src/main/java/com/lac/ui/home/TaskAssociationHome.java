package com.lac.ui.home;

import com.lac.activities.DLContents.TasksContent;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.TaskAssociationPanel;
import com.lac.ui.scrceens.tasks.TaskPanel;
import com.lac.userentry.ConfigurationEntryHolder;

public class TaskAssociationHome extends Home<TaskAssociationPanel> {
	
	@Override
	public void nextAction() throws PetriNetException {
		saveAction();
		
	}

	@Override
	public void fowardRender() throws PetriNetException {
		for(TasksContent tasks : ConfigurationEntryHolder.getInstance().getDlContent().getTasks()){
			TaskPanel taskPanel = baseComponent.addNewTask();
			try {
				taskPanel.getHome().setTask(tasks.getInputTransitionName(), tasks.getOutputTransitionName(),
						tasks.getName(), tasks.getActivities());
			} catch (SecurityException | ClassNotFoundException e) {
				throw new PetriNetException(e.getMessage(),e);
			} 
		}
		
	}

	@Override
	public void backAction() throws PetriNetException {
		return;
	}
	
	//TODO: check this functionality. The call to getHome.save() it is kind of award, shouldn't be doing everything in this single method 
	// instead of calling hierarchy to other ones ? 
	public void saveAction(){
		ConfigurationEntryHolder config = ConfigurationEntryHolder.getInstance();
		config.emptyTask();
		for(TaskPanel panel : baseComponent.getTaskPanels()){
			panel.getHome().save();
		}
	}
}
