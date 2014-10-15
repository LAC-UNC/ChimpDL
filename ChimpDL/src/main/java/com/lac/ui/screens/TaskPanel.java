package com.lac.ui.screens;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class TaskPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TaskPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel transitionInputLabel = new JLabel("Transition Input:");
		add(transitionInputLabel);
		
		JComboBox transitionInputComboBox = new JComboBox();
		add(transitionInputComboBox);
		
		JLabel lblTransitionOutput = new JLabel("Transition output:");
		add(lblTransitionOutput);
		
		JComboBox transitionOutputComboBox = new JComboBox();
		add(transitionOutputComboBox);
		
		JLabel lblTasks = new JLabel("Tasks:");
		add(lblTasks);
		
		JButton NewActivityButton = new JButton("new activity");
		NewActivityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewActivityPanel();
			}
		});
		add(NewActivityButton);

	}
	
	private void addNewActivityPanel(){
		ActivityPanel actPanel = new ActivityPanel();
		actPanel.setVisible(true);
		add(actPanel);
		revalidate();
		repaint();
	}

}
