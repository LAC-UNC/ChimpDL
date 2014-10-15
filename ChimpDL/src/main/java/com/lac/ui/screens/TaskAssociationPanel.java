package com.lac.ui.screens;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class TaskAssociationPanel extends JPanel {

	int lastColumn;
	int lastRow;
	JPanel bodyPanel;
	
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
		
		bodyPanel = new JPanel();
		add(bodyPanel, BorderLayout.CENTER);
		
		JButton btnNewTask = new JButton("new Task");
		bodyPanel.add(btnNewTask);
		btnNewTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewTask();
			}
		});
	}
	
	private void addNewTask(){
		TaskPanel newtask = new TaskPanel();
		newtask.setVisible(true);
		bodyPanel.add(newtask);
		bodyPanel.revalidate();
		bodyPanel.repaint();
		revalidate();
		repaint();
	}
	
}
