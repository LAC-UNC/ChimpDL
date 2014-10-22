package com.lac.ui.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.lac.interpreter.ChimpDL;
import com.lac.interpreter.ChimpDLImpl;
import com.lac.interpreter.Interpreter;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.userentry.ConfigurationEntryHolder;

public class SaveConfigPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SaveConfigPanel() {
		
		JButton btnSaveConfigurationAnd = new JButton("Save Configuration and start");
		btnSaveConfigurationAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAndStartAction();
			}
		});
		add(btnSaveConfigurationAnd);

	}
	
	public void saveAndStartAction(){
		
		Interpreter interpreter = ((MainFrame) getTopLevelAncestor()).getInterpreter();
		ConfigurationEntryHolder config = ConfigurationEntryHolder.getInstance();
		ChimpDL contentManager = new ChimpDLImpl() ;
		String path;
		try {
			path = getJarpath();;
			contentManager.saveConfiguration(path, config.getUserSelection());
			interpreter.startListening(path);
		} catch (PetriNetException | URISyntaxException e) {
			e.printStackTrace();
			((MainFrame) getTopLevelAncestor()).showError(e);
			return;
		}
		
	}
	
	
	private String getJarpath() throws URISyntaxException {
		String uri;
		uri = ChimpDLImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		while(uri.contains(".jar")){
			uri = uri.substring(0,  uri.lastIndexOf("/"));
		}
		return uri;
	}

}
