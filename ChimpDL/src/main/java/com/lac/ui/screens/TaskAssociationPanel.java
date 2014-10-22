package com.lac.ui.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lac.activities.DLContents.TasksContent;
import com.lac.userentry.ResourceInstances;

//TODO: support multiple input transitions for a given task.
public class TaskAssociationPanel extends JPanel {

	JPanel bodyPanel;
	
	private List<ResourceInstances> instanceNames;
	private List<TaskPanel> taskPanels = new ArrayList<TaskPanel>();
	
	/**
	 * Create the panel.
	 */
	public TaskAssociationPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		add(titlePanel, BorderLayout.NORTH);
		
		JLabel taskAssociationLabel = new JLabel("Task Association");
		taskAssociationLabel.setForeground(Color.BLUE);
		taskAssociationLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titlePanel.add(taskAssociationLabel);
		setAutoscrolls(true);
		
		bodyPanel = new JPanel();
		add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		
		JButton btnNewTask = new JButton("new Task");
		btnNewTask.setAlignmentY(Component.TOP_ALIGNMENT);
		btnNewTask.setAlignmentX(Component.CENTER_ALIGNMENT);
		bodyPanel.add(btnNewTask);
		bodyPanel.setAutoscrolls(true);
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAction();
			}
		});
		buttonPanel.add(btnSave);
		btnNewTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewTask();
			}
		});
	}
	
	private void addNewTask(){
		TaskPanel newTask = new TaskPanel();
		taskPanels.add(newTask);
		newTask.setResourceInstances(instanceNames);
		newTask.setMaximumSize(new Dimension(500,40 ));
		newTask.setAlignmentY(Component.TOP_ALIGNMENT);
		newTask.setAlignmentX(Component.CENTER_ALIGNMENT);
		bodyPanel.add(newTask);
		bodyPanel.revalidate();
		bodyPanel.repaint();
		revalidate();
		repaint();
	}
	
	public void setInstanceName(List<ResourceInstances> instanceNames){
		this.instanceNames = instanceNames;
		for(TaskPanel panel : taskPanels){
			panel.setResourceInstances(instanceNames);
		}
	}
	
	@Deprecated
	public List<TasksContent> getTasksContent(){
		List<TasksContent> taskList = new ArrayList<TasksContent>();
		for(TaskPanel panel : taskPanels){
			taskList.add(panel.getTask());
		}
		return taskList;
	}
	
	public void saveAction(){
		for(TaskPanel panel : taskPanels){
			panel.save();
		}
	}
	
}
