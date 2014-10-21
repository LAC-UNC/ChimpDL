package com.lac.ui.screens;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lac.userentry.ResourceInstances;

public class TaskPanel extends JPanel {
	private JPanel taskPanel;
	private JPanel Content;
	private ActivityFrame actFrame;
	
	/**
	 * Create the panel.
	 */
	public TaskPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Content = new JPanel();
		add(Content);
		Content.setLayout(new BoxLayout(Content, BoxLayout.Y_AXIS));
		
		taskPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) taskPanel.getLayout();
		Content.add(taskPanel);
		taskPanel.setSize(600, 80);
		taskPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		JLabel transitionInputLabel = new JLabel("Transition Input:");
		taskPanel.add(transitionInputLabel);
		
		JComboBox transitionInputComboBox = new JComboBox();
		taskPanel.add(transitionInputComboBox);
		
		JLabel lblTransitionOutput = new JLabel("Transition output:");
		taskPanel.add(lblTransitionOutput);
		
		JComboBox transitionOutputComboBox = new JComboBox();
		taskPanel.add(transitionOutputComboBox);
		
		JLabel lblTasks = new JLabel("Tasks:");
		taskPanel.add(lblTasks);
		
		JButton NewActivityButton = new JButton("edit Activities");
		taskPanel.add(NewActivityButton);
		NewActivityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(actFrame == null){
					actFrame = new ActivityFrame();
					actFrame.setVisible(true);
				}
				
				editActivities();
			}
		});
	}
	
	private void editActivities(){
		actFrame.setVisible(true);
	}

	public void setResourceInstances(List<ResourceInstances> instanceNames) {
		if(actFrame == null){
			actFrame = new ActivityFrame();
		}
		actFrame.setResourceInstances(instanceNames);
	}

}
