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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		contentPane.add(pnmlPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptAction();
			}
		});
		buttonPanel.add(btnAccept);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelAction();
			}
		});
		buttonPanel.add(btnCancel);
		
	}
	
	protected void cancelAction() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));		
	}

	protected void acceptAction() {
		
		if(pnmlPanel.isVisible()){
			String path = pnmlPanel.getPath();
			try {
				// TODO:add interpreter logic
//				Interpreter interpreter = new Interpreter();
//				interpreter.readPnmlFile(path);
				pnmlPanel.setVisible(false);
				this.remove(pnmlPanel);
				taskAssocPanel.setVisible(true);
				contentPane.add(taskAssocPanel, BorderLayout.CENTER);
				contentPane.revalidate();
				contentPane.repaint();
				this.revalidate();
				this.repaint();
			} catch (Exception e) {
				String message = e.getMessage();
				while((message == null || message.equals("")) && e.getCause() != null){
					message = e.getCause().getMessage();
				}
				if(message == null || message.equals("")){
					message = "Unexpected error.";
				}
				new ErrorDialog(message);
			}
		}		
		
		else if(taskAssocPanel.isVisible()){
			taskAssocPanel.setVisible(false);
			this.remove(taskAssocPanel);
			pnmlPanel.setVisible(true);
			contentPane.add(pnmlPanel, BorderLayout.CENTER);
			contentPane.revalidate();
			contentPane.repaint();
		}
		
		
	}

}
