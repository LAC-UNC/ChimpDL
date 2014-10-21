package com.lac.ui.screens;

import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.lac.userentry.ResourceInstances;

public class ActivityPanel extends JPanel {

	JComboBox<String> resourcesComboBox;
	JComboBox<Method> methodComboBox;	
	Set<String> instanceNamesSet = new HashSet<String>();
	
	/**
	 * Create the panel.
	 */
	public ActivityPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		resourcesComboBox = new JComboBox<String>();
		resourcesComboBox.setMaximumRowCount(3);
		add(resourcesComboBox);
		
		methodComboBox = new JComboBox<Method>();
		methodComboBox.setMaximumRowCount(3);
		add(methodComboBox);
		this.setMaximumSize(new Dimension(420,25));
		this.setAlignmentY(Component.TOP_ALIGNMENT);
		
//		this.setPreferredSize(new Dimension(139, 16));

	}
	
	public void addResources(List<ResourceInstances> instanceNames){
		for(ResourceInstances instanceInfo : instanceNames){
			if(instanceNamesSet.add(instanceInfo.getInstanceName()))
				resourcesComboBox.addItem(instanceInfo.getInstanceName());
			for(Method m : instanceInfo.getClazz().getDeclaredMethods()){
				methodComboBox.addItem(m);
			}
		}
		resourcesComboBox.validate();
		resourcesComboBox.repaint();
	}
	
}
