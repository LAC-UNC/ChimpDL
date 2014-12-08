package com.lac.ui.mainscreens;

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
import javax.swing.JScrollPane;

import com.lac.ui.controllers.TaskAssociationController;
import com.lac.ui.controllers.TaskController;
import com.lac.ui.scrceens.tasks.TaskPanel;

//TODO: support multiple input transitions for a given task.
public class TaskAssociationPanel extends ControlledJPanel<TaskAssociationController> {
	private static final long serialVersionUID = 1L;

	public TaskAssociationPanel(TaskAssociationController homeController) {
		super(homeController);
	}

	private JPanel bodyPanel;

	private List<TaskPanel> taskPanels = new ArrayList<TaskPanel>();

	/**
	 * Create the panel.
	 */


	@Override
	protected void initComponents() {
		setLayout(new BorderLayout(0, 0));

		JPanel titlePanel = new JPanel();
		add(titlePanel, BorderLayout.NORTH);

		JLabel taskAssociationLabel = new JLabel("Task Association");
		taskAssociationLabel.setForeground(Color.BLUE);
		taskAssociationLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titlePanel.add(taskAssociationLabel);
		setAutoscrolls(true);

		bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		bodyPanel.setAutoscrolls(true);

		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		
				JButton btnNewTask = new JButton("new Task");
				buttonPanel.add(btnNewTask);
				btnNewTask.setAlignmentY(Component.TOP_ALIGNMENT);
				btnNewTask.setAlignmentX(Component.CENTER_ALIGNMENT);
				//		add(bodyPanel, BorderLayout.CENTER);

				btnNewTask.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addNewTask();
					}
				});

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(bodyPanel);
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);

	}

	public TaskPanel addNewTask(){
		TaskController taskHome = new TaskController(this.getHomeController());
		TaskPanel newTask = new TaskPanel(taskHome);
		taskHome.setBaseComponent(newTask);
		getTaskPanels().add(newTask);
		newTask.setMaximumSize(new Dimension(999,27 ));
		newTask.setAlignmentY(Component.TOP_ALIGNMENT);
		newTask.setAlignmentX(Component.CENTER_ALIGNMENT);
		getBodyPanel().add(newTask);
		getBodyPanel().revalidate();
		getBodyPanel().repaint();
		revalidate();
		repaint();
		return newTask;
	}

	public void eraseObserverPanel(TaskPanel toErase) {
		getTaskPanels().remove(toErase);
		getBodyPanel().remove(toErase);
		revalidate();
		repaint();
	}
	
	public List<TaskPanel> getTaskPanels() {
		return taskPanels;
	}



	public void setTaskPanels(List<TaskPanel> taskPanels) {
		this.taskPanels = taskPanels;
	}



	public JPanel getBodyPanel() {
		return bodyPanel;
	}



	public void setBodyPanel(JPanel bodyPanel) {
		this.bodyPanel = bodyPanel;
	}
	
	

}
