package com.lac.ui.screens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.lac.activities.DLContents.ActivityContent;
import com.lac.userentry.ResourceInstances;

public class ActivityPanel extends JPanel {

	JComboBox<String> resourcesComboBox;
	JComboBox<String> methodComboBox;	
	Set<String> instanceNamesSet = new HashSet<String>();
	Map<String, Class<?>> instanceMap = new HashMap<String, Class<?>>();
	
	/**
	 * Create the panel.
	 */
	public ActivityPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		resourcesComboBox = new JComboBox<String>();
		resourcesComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeMethodComboBox(e);
			}
		});
		resourcesComboBox.setMaximumRowCount(20);
		add(resourcesComboBox);
		
		methodComboBox = new JComboBox<String>();
		methodComboBox.setMaximumRowCount(20);
		add(methodComboBox);
		this.setMaximumSize(new Dimension(420,25));
		this.setAlignmentY(Component.TOP_ALIGNMENT);
		
//		this.setPreferredSize(new Dimension(139, 16));

	}
	
	//TODO: arreglar el getDeclaredMethod xq lo entrega con las variables no solo el nombre y nosotros necesitamos solo el nombre. 
	public void addResources(List<ResourceInstances> instanceNames){
		for(ResourceInstances instanceInfo : instanceNames){
			if(instanceMap.put(instanceInfo.getInstanceName(), instanceInfo.getClazz()) == null){
				resourcesComboBox.addItem(instanceInfo.getInstanceName());
			}
		}
		resourcesComboBox.validate();
		resourcesComboBox.repaint();
	}
	
	public ActivityContent getActivity(){
		ActivityContent content = new ActivityContent();
		content.setMethod(methodComboBox.getSelectedItem().toString());
		content.setObj(resourcesComboBox.getSelectedItem().toString());
		return content;
	}
	
	public void changeMethodComboBox(ActionEvent e){
		Class<?> clazz = instanceMap.get(((JComboBox) e.getSource()).getSelectedItem());
		methodComboBox.removeAllItems();			
		for(Method m : clazz.getDeclaredMethods()){
			methodComboBox.addItem(m.getName());
		}
		
	}
	
}
