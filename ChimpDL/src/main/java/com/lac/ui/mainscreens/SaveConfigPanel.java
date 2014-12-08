package com.lac.ui.mainscreens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.lac.ui.controllers.SaveConfigController;

public class SaveConfigPanel extends ControlledJPanel<SaveConfigController> {
	private static final long serialVersionUID = 1L;
	JButton btnSaveConfigurationAnd;

	public SaveConfigPanel(SaveConfigController homeController) {
		super(homeController);
	}

	@Override
	protected void initComponents() {
		btnSaveConfigurationAnd = new JButton("Save Configuration and start");
		btnSaveConfigurationAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAction();
			}
		});
		add(btnSaveConfigurationAnd);
		
	}
	private void saveAction(){
		btnSaveConfigurationAnd.setEnabled(false);
		homeController.saveAndStartAction();
	}
	

}
