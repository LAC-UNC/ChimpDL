package com.lac.ui.mainscreens;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lac.ui.resources.ResourceInstancePanel;
import com.lac.userentry.ResourceInstances;

import javax.swing.JScrollPane;

public class ResourcesManagementPanel extends JPanel {

	JPanel contentPanel;
	List<ResourceInstancePanel> instancePanelList = new ArrayList<ResourceInstancePanel>();
	
	/**
	 * Create the panel.
	 */
	public ResourcesManagementPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		add(titlePanel, BorderLayout.NORTH);
		
		JLabel lblResourcesManagement = new JLabel("Resources Management");
		titlePanel.add(lblResourcesManagement);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JButton btnAddResourceInstance = new JButton("Add Resource Instance ");
		btnAddResourceInstance.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAddResourceInstance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addResourceInstancePanel();
			}
		});
		contentPanel.add(btnAddResourceInstance);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(contentPanel);

	}
	
	public void addResourceInstancePanel(){
		ResourceInstancePanel resourcePanel = new ResourceInstancePanel();
		instancePanelList.add(resourcePanel);
		resourcePanel.setAlignmentY(Component.TOP_ALIGNMENT);
		resourcePanel.setSize(139, 16);
		resourcePanel.setVisible(true);
		contentPanel.add(resourcePanel);
		revalidate();
		repaint();
	}
	
//	public List<ResourcesContent> saveInstance(){
//		List<ResourcesContent> resourceInstances = new ArrayList<ResourcesContent>(); 
//		for(ResourceInstancePanel panel : instancePanelList){
//			resourceInstances.add(panel.getInstanceInfo());
//		}
//		return resourceInstances;
//	}
	
	public List<ResourceInstances> getResourceInstances() {
		List<ResourceInstances> instanceNames = new ArrayList<ResourceInstances>();
		for(ResourceInstancePanel panel : instancePanelList){
			instanceNames.add(panel.getInstanceInfo());
		}
		return instanceNames;
	}


}
