package com.lac.ui.mainscreens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorDialog {

	private JDialog frame;

	/**
	 * Create the application.
	 */
	public ErrorDialog(String text) {
		initialize(text);
	}
	
	public ErrorDialog(Exception e){
		e.printStackTrace();
		String message = e.getMessage();
		while((message == null || message.equals("")) && e.getCause() != null){
			message = e.getCause().getMessage();
		}
		if(message == null || message.equals("")){
			message = "Unexpected error.";
		}
		initialize(message);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String text) {
		frame = new JDialog();
		frame.setBounds(100, 100, 436, 147);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel titlePanel = new JPanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JLabel errorTitle = new JLabel("Error ! ");
		errorTitle.setForeground(Color.RED);
		errorTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titlePanel.add(errorTitle);
		
		JPanel bodyPanel = new JPanel();
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		
		JLabel errorLabel = new JLabel(text);
		bodyPanel.add(errorLabel);
		
		JPanel buttonsPanel = new JPanel();
		frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		buttonsPanel.add(btnAccept);
		frame.setVisible(true);
	}
	


}
