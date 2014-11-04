package com.lac.ui.mainscreens;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lac.annotations.ResourcesFinder;
import com.lac.interpreter.Interpreter;
import com.lac.petrinet.core.PetriNet;
import com.lac.userentry.ConfigurationEntryHolder;
import com.lac.userentry.ResourceInstances;

public class MainFrame extends JFrame {

	protected JPanel contentPane;
	protected PnmlSelectionPanel pnmlPanel = new PnmlSelectionPanel();
	protected TaskAssociationPanel taskAssocPanel = new TaskAssociationPanel();
	protected ResourcesManagementPanel resourcePanel = new ResourcesManagementPanel();
	LoadSessionPanel sessionPanel = new LoadSessionPanel();
	protected Interpreter interpreter;
	protected SaveConfigPanel saveConfigPanel = new SaveConfigPanel();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		// load the resources 
		ResourcesFinder.getResources();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);


		contentPane.add(pnmlPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		JButton btnAtras = new JButton("Back");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backAction();
			}
		});
		buttonPanel.add(btnAtras);

		JButton btnAccept = new JButton("Next");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptAction();
			}
		});
		buttonPanel.add(btnAccept);

	}

	protected void backAction() {
		if(pnmlPanel.isVisible()){
		}
		else if(sessionPanel.isVisible()){
			changeContentPanel(sessionPanel, pnmlPanel);
		}
		else if(resourcePanel.isVisible()){
			changeContentPanel(resourcePanel, sessionPanel);
		}
		else if(taskAssocPanel.isVisible()){
			changeContentPanel(taskAssocPanel, resourcePanel);
		}
		else if(saveConfigPanel.isVisible()){
			changeContentPanel(saveConfigPanel, taskAssocPanel);
		}
	}

	protected void acceptAction() {
		try{
			ConfigurationEntryHolder config = ConfigurationEntryHolder.getInstance();
			if(pnmlPanel.isVisible()){
				String path = pnmlPanel.getPath();
				interpreter = new Interpreter();
				PetriNet petriNet = interpreter.readPnmlFile(path);
				config.setPetriNet(petriNet);
				changeContentPanel(pnmlPanel, sessionPanel);
			}
			else if(sessionPanel.isVisible()){
				changeContentPanel(sessionPanel, resourcePanel);
			}
			else if(resourcePanel.isVisible()){
				for(ResourceInstances resource : resourcePanel.getResourceInstances()){
					config.addUserEntryResource(resource.getInstanceName(),resource.getClazz() );
				}
				taskAssocPanel.setInstanceName(resourcePanel.getResourceInstances());
				changeContentPanel(resourcePanel, taskAssocPanel);
			}
			else if(taskAssocPanel.isVisible()){
				changeContentPanel(taskAssocPanel, saveConfigPanel);
			}
		}catch (Exception e) {
			showError(e);
		}
	}

	private void changeContentPanel(JPanel source, JPanel target){
		source.setVisible(false);
		this.remove(source);
		target.setVisible(true);
		contentPane.add(target, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
		this.revalidate();
		this.repaint();
	}

	public static void showError(Exception e){
		String message = e.getMessage();
		while((message == null || message.equals("")) && e.getCause() != null){
			message = e.getCause().getMessage();
		}
		if(message == null || message.equals("")){
			message = "Unexpected error.";
		}
		new ErrorDialog(message);
	}

	public Interpreter getInterpreter() {
		return interpreter;
	}

	public TaskAssociationPanel getTaskAssocPanel() {
		return taskAssocPanel;
	}

	public ResourcesManagementPanel getResourcePanel() {
		return resourcePanel;
	}
	
	

}
