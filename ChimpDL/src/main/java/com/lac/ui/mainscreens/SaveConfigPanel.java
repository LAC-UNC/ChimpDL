package com.lac.ui.mainscreens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.lac.ui.controllers.SaveConfigController;

public class SaveConfigPanel extends ControlledJPanel<SaveConfigController> {
	private static final long serialVersionUID = 1L;

	public SaveConfigPanel(SaveConfigController homeController) {
		super(homeController);
	}

	@Override
	protected void initComponents() {
		JButton btnSaveConfigurationAnd = new JButton("Save Configuration and start");
		btnSaveConfigurationAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeController.saveAndStartAction();
			}
		});
		add(btnSaveConfigurationAnd);
		
	}
	

}
