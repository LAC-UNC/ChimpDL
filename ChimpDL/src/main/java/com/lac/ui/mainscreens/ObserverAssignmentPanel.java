package com.lac.ui.mainscreens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.lac.ui.controllers.ObserverAssignmentController;
import com.lac.ui.controllers.ObserverController;
import com.lac.ui.scrceens.observers.ObserverPanel;

//TODO: support multiple input transitions for a given task.
public class ObserverAssignmentPanel extends ControlledJPanel<ObserverAssignmentController> {
	private static final long serialVersionUID = 1L;

	public ObserverAssignmentPanel(ObserverAssignmentController homeController) {
		super(homeController);
	}

	private JPanel bodyPanel;
	private ArrayList<ObserverPanel> obsPanels = new ArrayList<ObserverPanel>();

	/**
	 * Create the panel.
	 */


	@Override
	protected void initComponents() {
		setLayout(new BorderLayout(0, 0));

		JPanel titlePanel = new JPanel();
		add(titlePanel, BorderLayout.NORTH);

		JLabel observerAssociationLabel = new JLabel("Observers Association");
		observerAssociationLabel.setForeground(Color.BLUE);
		observerAssociationLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titlePanel.add(observerAssociationLabel);
		setAutoscrolls(true);

		bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		bodyPanel.setAutoscrolls(true);

		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		
				JButton btnNewObserver = new JButton("New observer");
				buttonPanel.add(btnNewObserver);
				btnNewObserver.setAlignmentY(Component.TOP_ALIGNMENT);
				btnNewObserver.setAlignmentX(Component.CENTER_ALIGNMENT);
				//		add(bodyPanel, BorderLayout.CENTER);

				btnNewObserver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addNewObserver();
					}
				});

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(bodyPanel);
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);

	}

	public ObserverPanel addNewObserver(){
		ObserverController observerHome = new ObserverController(this.getHomeController());
		ObserverPanel newObserver = new ObserverPanel(observerHome);
		observerHome.setBaseComponent(newObserver);
		getObserverPanels().add(newObserver);
		newObserver.setMaximumSize(new Dimension(999,27 ));
		newObserver.setAlignmentY(Component.TOP_ALIGNMENT);
		newObserver.setAlignmentX(Component.CENTER_ALIGNMENT);
		getBodyPanel().add(newObserver);
		getBodyPanel().revalidate();
		getBodyPanel().repaint();
		revalidate();
		repaint();
		return newObserver;
	}
	
	public void eraseObserverPanel(ObserverPanel toErase) {
		getObserverPanels().remove(toErase);
		getBodyPanel().remove(toErase);
		revalidate();
		repaint();
	}
	
	public ArrayList<ObserverPanel> getObserverPanels() {
		return obsPanels;
	}
	
	
	public JPanel getBodyPanel() {
		return bodyPanel;
	}



	public void setBodyPanel(JPanel bodyPanel) {
		this.bodyPanel = bodyPanel;
	}
	
	

}
