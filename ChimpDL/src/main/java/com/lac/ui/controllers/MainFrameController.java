package com.lac.ui.controllers;

import java.awt.BorderLayout;
import java.util.ArrayList;

import com.lac.annotations.ResourcesFinder;
import com.lac.interpreter.Interpreter;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.LoadSessionPanel;
import com.lac.ui.mainscreens.MainFrame;
import com.lac.ui.mainscreens.ObserverAssignmentPanel;
import com.lac.ui.mainscreens.PnmlSelectionPanel;
import com.lac.ui.mainscreens.ResourcesManagementPanel;
import com.lac.ui.mainscreens.SaveConfigPanel;
import com.lac.ui.mainscreens.TaskAssociationPanel;

public class MainFrameController {
	
	private int actual;
	private ArrayList<ActionablePanelController> controllers;
	private MainFrame baseComponent;
	
	public MainFrameController(MainFrame mainf) {
		baseComponent = mainf;
		// load the resources
		ResourcesFinder.getResources();
		Interpreter interpreter = new Interpreter();
		

		// initialization of panels 
		PnmlSelectionController pnmlSelectionController = new PnmlSelectionController(interpreter);
		ObserverAssignmentController observerAssocController = new ObserverAssignmentController();
		LoadSessionController loadSessionController = new LoadSessionController();
		ResourcesManagementController resourcesController = new ResourcesManagementController();
		TaskAssociationController taskAssociationController = new TaskAssociationController();
		SaveConfigController saveConfigController = new SaveConfigController(interpreter);
		
		PnmlSelectionPanel pnmlPanel = new PnmlSelectionPanel(pnmlSelectionController);
		ObserverAssignmentPanel observerAssocPanel = new ObserverAssignmentPanel(observerAssocController);
		LoadSessionPanel sessionPanel = new LoadSessionPanel(loadSessionController); 
		ResourcesManagementPanel resourcePanel = new ResourcesManagementPanel(resourcesController);
		TaskAssociationPanel taskAssocPanel = new TaskAssociationPanel(taskAssociationController);
		SaveConfigPanel saveConfigPanel = new SaveConfigPanel(saveConfigController);

		pnmlSelectionController.setBaseComponent(pnmlPanel);
		observerAssocController.setBaseComponent(observerAssocPanel);
		loadSessionController.setBaseComponent(sessionPanel);
		resourcesController.setBaseComponent(resourcePanel);
		taskAssociationController.setBaseComponent(taskAssocPanel);
		saveConfigController.setBaseComponent(saveConfigPanel);
		
		baseComponent.getContentPane().add(pnmlPanel, BorderLayout.CENTER);
		
		controllers = new ArrayList<ActionablePanelController>();
		
		controllers.add(pnmlSelectionController);
		controllers.add(loadSessionController);
		controllers.add(observerAssocController);
		controllers.add(resourcesController);
		controllers.add(taskAssociationController);
		controllers.add(saveConfigController);
		
		actual = 0;
	}
	
	public void nextButtonAction() throws PetriNetException {
		if(actual < controllers.size() - 1){
			ActionablePanelController source = controllers.get(actual);
			ActionablePanelController target = controllers.get(++actual);
			source.nextAction();
			changeContentPanel(source, target);
			target.renderAction();
		}
	}

	public void backButtonAction() throws PetriNetException {
	
		if(actual > 0){
			changeContentPanel(controllers.get(actual), controllers.get(--actual));
		}
	}
	
	private void changeContentPanel(ActionablePanelController source, ActionablePanelController target) throws PetriNetException{
		source.setComponentVisible(false);
		baseComponent.remove(source.getBaseComponent());
		target.setComponentVisible(true);
		baseComponent.getContentPane().add(target.getBaseComponent(), BorderLayout.CENTER);		
		baseComponent.getContentPane().revalidate();
		baseComponent.getContentPane().repaint();
		baseComponent.revalidate();
		baseComponent.repaint();
	}
}
