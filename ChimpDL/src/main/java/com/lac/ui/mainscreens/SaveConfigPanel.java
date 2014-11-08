package com.lac.ui.mainscreens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.lac.ui.home.SaveConfigHome;

public class SaveConfigPanel extends ControlledJPanel<SaveConfigHome> {

	public SaveConfigPanel(SaveConfigHome homeController) {
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
