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

import com.lac.ui.home.TaskAssociationHome;
import com.lac.ui.home.TaskHome;
import com.lac.ui.scrceens.tasks.TaskPanel;

//TODO: support multiple input transitions for a given task.
public class TaskAssociationPanel extends ControlledJPanel<TaskAssociationHome> {

	public TaskAssociationPanel(TaskAssociationHome homeController) {
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

		JButton btnNewTask = new JButton("new Task");
		btnNewTask.setAlignmentY(Component.TOP_ALIGNMENT);
		btnNewTask.setAlignmentX(Component.CENTER_ALIGNMENT);
		bodyPanel.add(btnNewTask);
		bodyPanel.setAutoscrolls(true);

		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(bodyPanel);
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		//		add(bodyPanel, BorderLayout.CENTER);

		btnNewTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewTask();
			}
		});

	}

	public TaskPanel addNewTask(){
		TaskHome taskHome = new TaskHome();
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
