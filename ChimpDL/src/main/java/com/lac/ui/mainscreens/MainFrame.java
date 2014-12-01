package com.lac.ui.mainscreens;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.controllers.MainFrameController;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	protected MainFrameController mainFrameController;
	protected JPanel contentPane;
	/**
	 * Create the frame.
	 * TODO: improve the way that the home and ControlledJPanels are initialized.
	 */
	public MainFrame() {
		//init frame components.
		initComponents();
		mainFrameController = new MainFrameController(this);
	}

	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		JButton btnAtras = new JButton("Back");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainFrameController.backButtonAction();
				} catch (PetriNetException e1) {
					new ErrorDialog(e1);
				}
			}
		});
		buttonPanel.add(btnAtras);

		JButton btnAccept = new JButton("Next");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainFrameController.nextButtonAction();
				} catch (PetriNetException e1) {
					new ErrorDialog(e1);
				}
			}
		});
		buttonPanel.add(btnAccept);
	}
}
