package com.lac.ui.scrceens.observers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.lac.activities.DLContents.ResourcesContent;
import com.lac.model.Model;

public class ObserverConfigurationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JComboBox<String> informedTransitionsComboBox;
	Set<String> instanceNamesSet = new HashSet<String>();
	Map<String, String> instanceMap = new HashMap<String, String>();
	
	/**
	 * Create the panel.
	 */
	public ObserverConfigurationPanel() {
		initComponents();
		addResources(Model.getInstance().getDlContent().getResources());
	}
	
	private void initComponents(){
		Model config = Model.getInstance();
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		informedTransitionsComboBox = new JComboBox<String>();
		for(String inputTransitionName : config.getInformedTransitions()){
			informedTransitionsComboBox.addItem(inputTransitionName);
		}
		add(informedTransitionsComboBox);
		this.setMaximumSize(new Dimension(999,25));
		this.setAlignmentY(Component.TOP_ALIGNMENT);
	}
	
	public void addResources(List<ResourcesContent> instanceNames){
		for(ResourcesContent instanceInfo : instanceNames){
			instanceMap.put(instanceInfo.getImplementationName(), instanceInfo.getClassName());
			informedTransitionsComboBox.addItem(instanceInfo.getImplementationName());
		}
		informedTransitionsComboBox.validate();
		informedTransitionsComboBox.repaint();
	}
	
	/*public ActivityContent getActivity(){
		ActivityContent content = new ActivityContent();
		content.setMethod(methodComboBox.getSelectedItem().toString());
		content.setObj(informedTransitionsComboBox.getSelectedItem().toString());
		return content;
	}*/
	
	public void addActivity(String instanceName, String method ) throws SecurityException, ClassNotFoundException{
		informedTransitionsComboBox.setSelectedItem(instanceName);
		ActionEvent e = new ActionEvent(informedTransitionsComboBox, 1, "");
	}
	
}
