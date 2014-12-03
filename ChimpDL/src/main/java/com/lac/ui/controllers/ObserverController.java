package com.lac.ui.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lac.activities.DLContents.ActivityContent;
import com.lac.model.Model;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.ErrorDialog;
import com.lac.ui.scrceens.observers.ObserverPanel;
import com.lac.ui.scrceens.tasks.ActivityFrame;
import com.lac.ui.scrceens.tasks.ActivityPanel;
import com.lac.ui.scrceens.tasks.TaskPanel;

public class ObserverController extends ActionablePanelController<ObserverPanel> {

	//TODO: fix the List of transition to be a real list not just one ! the get(0) is a major issue for the future
	/*public void setTask(List<String> transitionInputComboBox, String transitionOutputComboBox, String taskNameText, 
			List<ActivityContent> activities) throws SecurityException, ClassNotFoundException{
		baseComponent.getTransitionInputComboBox().setSelectedItem(transitionInputComboBox.get(0));
		baseComponent.getTransitionOutputComboBox().setSelectedItem(transitionOutputComboBox);
		baseComponent.getTaskNameTextField().setText(taskNameText);
		ActivityFrame activityFrame = baseComponent.getActFrame();
		for(ActivityContent activity : activities){
			ActivityPanel activityPanel = new ActivityPanel();
			activityPanel.addActivity(activity.getObj(), activity.getMethod());
			activityFrame.addActivityPanel(activityPanel);
		}
	}
	
	public void save(){
		Model config = Model.getInstance();
		try {
			config.addtask(new ArrayList(Arrays.asList(baseComponent.getTransitionInputComboBox().getSelectedObjects())), 
					baseComponent.getTransitionOutputComboBox().getSelectedItem().toString(),
					baseComponent.getActFrame().getActivities(), baseComponent.getTaskNameTextField().getText());
		} catch (PetriNetException e) {
			new ErrorDialog(e);
		}
	}*/
	
}
