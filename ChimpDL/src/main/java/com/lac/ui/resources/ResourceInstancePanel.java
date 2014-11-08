package com.lac.ui.resources;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lac.activities.DLContents.ResourcesContent;
import com.lac.annotations.ResourcesFinder;

public class ResourceInstancePanel extends JPanel {
	private JTextField instanceNameText;
	private JComboBox<String> resourceComboBox;

	/**
	 * Create the panel.
	 */
	public ResourceInstancePanel() {
		initContent();
	} 
	
	public ResourceInstancePanel(String resource, String method) {
		
	} 
	
	private void  initContent(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setMaximumSize(new Dimension(700,25));
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		resourceComboBox = new JComboBox<String>();
		for(Class<?> clazz : ResourcesFinder.getResources())
			resourceComboBox.addItem(clazz.getName());
		panel.add(resourceComboBox);
		
		instanceNameText = new JTextField();
		panel.add(instanceNameText);
		instanceNameText.setColumns(10);
	}
	
	public ResourcesContent getInstanceInfo(){
		ResourcesContent information = new ResourcesContent(instanceNameText.getText(),(String) resourceComboBox.getSelectedItem());
		return information;
	}
	
	public void setResource(String resource){
		this.resourceComboBox.setSelectedItem(resource);
	}
	public void setInstaceName(String instanceName){
		this.instanceNameText.setText(instanceName);
	}
	

}
