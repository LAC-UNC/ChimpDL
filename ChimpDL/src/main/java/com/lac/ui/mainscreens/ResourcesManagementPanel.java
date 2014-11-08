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
import javax.swing.JScrollPane;

import com.lac.ui.home.ResourcesManagementHome;
import com.lac.ui.resources.ResourceInstancePanel;

public class ResourcesManagementPanel extends ControlledJPanel<ResourcesManagementHome> {

	
	public ResourcesManagementPanel(ResourcesManagementHome homeController) {
		super(homeController);
	}

	private JPanel contentPanel;
	JScrollPane scrollPane ;
	JButton btnAddResourceInstance ;
	private List<ResourceInstancePanel> instancePanelList = new ArrayList<ResourceInstancePanel>();



	protected void initComponents(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		add(titlePanel, BorderLayout.NORTH);
		
		JLabel lblResourcesManagement = new JLabel("Resources Management");
		titlePanel.add(lblResourcesManagement);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		btnAddResourceInstance = new JButton("Add Resource Instance ");
		btnAddResourceInstance.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAddResourceInstance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addResourceInstancePanel();
			}
		});
		contentPanel.add(btnAddResourceInstance);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setVisible(true);
		scrollPane.setViewportView(contentPanel);
		contentPanel.setVisible(true);

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

	public void removeResources(){
		for(JPanel resourcePanel : instancePanelList ){
			contentPanel.remove(resourcePanel);
		}
		//TODO: Check if this kind of removeAll is possible. 
		instancePanelList.removeAll(instancePanelList);
	}

	public List<ResourceInstancePanel> getInstancePanelList() {
		return instancePanelList;
	}

	public void setInstancePanelList(List<ResourceInstancePanel> instancePanelList) {
		this.instancePanelList = instancePanelList;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}


}
