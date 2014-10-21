package com.lac.ui.screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lac.interpreter.Interpreter;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	PnmlSelectionPanel pnmlPanel = new PnmlSelectionPanel();
	TaskAssociationPanel taskAssocPanel = new TaskAssociationPanel();
	ResourcesManagementPanel resourcePanel = new ResourcesManagementPanel();

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
		
		JButton btnAccept = new JButton("Next");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptAction();
			}
		});
		buttonPanel.add(btnAccept);
		buttonPanel.add(btnAtras);
		
	}
	
	protected void backAction() {
		if(pnmlPanel.isVisible()){
		}		
		else if(resourcePanel.isVisible()){
			changeContentPanel(resourcePanel, pnmlPanel);
		}
		else if(taskAssocPanel.isVisible()){
			changeContentPanel(taskAssocPanel, resourcePanel);
		}
	}

	protected void acceptAction() {
		
		if(pnmlPanel.isVisible()){
			String path = pnmlPanel.getPath();
			try {
				Interpreter interpreter = new Interpreter();
				interpreter.readPnmlFile(path);
				changeContentPanel(pnmlPanel, resourcePanel);
			} catch (Exception e) {
				showError(e);
			}
		}		
		
		else if(resourcePanel.isVisible()){
			taskAssocPanel.setInstanceName(resourcePanel.getResourceInstances());
			changeContentPanel(resourcePanel, taskAssocPanel);
			
		}
		
		else if(taskAssocPanel.isVisible()){
			this.setVisible(false);
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
	
	private void showError(Exception e){
		String message = e.getMessage();
		while((message == null || message.equals("")) && e.getCause() != null){
			message = e.getCause().getMessage();
		}
		if(message == null || message.equals("")){
			message = "Unexpected error.";
		}
		new ErrorDialog(message);
	}
	
//	private String getJarpath() throws URISyntaxException {
//		String uri;
//		uri = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
//		if(uri.endsWith("/")){
//			uri = uri.substring(0,uri.length()-1);
//		}
//		if(uri.contains(".jar")){
//			uri = uri.substring(0,uri.lastIndexOf("/"));
//		}
//		while(uri.contains(".jar")){
//			uri = uri.substring(0,  uri.lastIndexOf("/"));
//		}
//		return uri;
//	}

}
