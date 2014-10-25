package com.lac.ui.screens;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lac.annotations.ResourcesFinder;
import com.lac.userentry.ResourceInstances;

public class ResourceInstancePanel extends JPanel {
	private JTextField instanceNameText;
	private JComboBox<Class<?>> resourceComboBox;

	/**
	 * Create the panel.
	 */
	public ResourceInstancePanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setMaximumSize(new Dimension(700,25));
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		resourceComboBox = new JComboBox<Class<?>>();
		for(Class<?> clazz : ResourcesFinder.getResources())
			resourceComboBox.addItem(clazz);
		panel.add(resourceComboBox);
		
		instanceNameText = new JTextField();
		panel.add(instanceNameText);
		instanceNameText.setColumns(10);

	} 
	
	public ResourceInstances getInstanceInfo(){
		ResourceInstances information = new ResourceInstances(instanceNameText.getText(),(Class<?>) resourceComboBox.getSelectedItem());
		return information;
	}
	

}
