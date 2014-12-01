package com.lac.ui.controllers;

import java.awt.Component;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.lac.activities.DLContents.ResourcesContent;
import com.lac.interpreter.ChimpDLFile;
import com.lac.interpreter.Interpreter;
import com.lac.model.Model;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.ResourcesManagementPanel;
import com.lac.ui.resources.ResourceInstancePanel;

public class ResourcesManagementController extends ActionablePanelController<ResourcesManagementPanel>{
	
	
	public void renderAction(){
		baseComponent.removeResources();
		for( ResourcesContent resource : Model.getInstance().getDlContent().getResources()){
			ResourceInstancePanel resourcePanel = new ResourceInstancePanel();
			resourcePanel.setResource(resource.getClassName());
			resourcePanel.setInstaceName(resource.getImplementationName());
			baseComponent.getInstancePanelList().add(resourcePanel);
			resourcePanel.setAlignmentY(Component.TOP_ALIGNMENT);
			resourcePanel.setSize(139, 16);
			resourcePanel.setVisible(true);
			baseComponent.getContentPanel().add(resourcePanel);
		}
		baseComponent.revalidate();
		baseComponent.repaint();
	}
	
	public void nextAction() throws PetriNetException{
		List<ResourcesContent> resourceList = new ArrayList<ResourcesContent>();
		
		for(ResourceInstancePanel instancePanel : baseComponent.getInstancePanelList()){
			resourceList.add(instancePanel.getInstanceInfo());
		}
		Model.getInstance().getDlContent().setResources(resourceList);
		try {
			(new ChimpDLFile()).saveConfiguration(Interpreter.getJarpath(), Model.getInstance().getDlContent());
		} catch (URISyntaxException e) {
			throw new PetriNetException(e.getMessage(),e);
		}
	}
}
