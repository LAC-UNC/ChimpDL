package com.lac.ui.home;

import java.awt.Component;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.lac.activities.DLContents.ResourcesContent;
import com.lac.interpreter.ChimpDLImpl;
import com.lac.interpreter.Interpreter;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.ResourcesManagementPanel;
import com.lac.ui.resources.ResourceInstancePanel;
import com.lac.userentry.ConfigurationEntryHolder;

public class ResourcesManagementHome extends Home<ResourcesManagementPanel>{
	
	
	public void fowardRender(){
		baseComponent.removeResources();
		for( ResourcesContent resource : ConfigurationEntryHolder.getInstance().getDlContent().getResources()){
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
		ConfigurationEntryHolder.getInstance().getDlContent().setResources(resourceList);
		try {
			(new ChimpDLImpl()).saveConfiguration(Interpreter.getJarpath(), ConfigurationEntryHolder.getInstance().getDlContent());
		} catch (URISyntaxException e) {
			throw new PetriNetException(e.getMessage(),e);
		}
	}
	
	public void backAction()throws PetriNetException{
		return;
	}
}
