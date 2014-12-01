package com.lac.ui.controllers;

import java.net.URISyntaxException;

import com.lac.interpreter.ChimpDL;
import com.lac.interpreter.ChimpDLFile;
import com.lac.interpreter.Interpreter;
import com.lac.model.Model;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.ErrorDialog;
import com.lac.ui.mainscreens.SaveConfigPanel;

public class SaveConfigController extends ActionablePanelController<SaveConfigPanel> {

	private Interpreter interpreter;
	
	public SaveConfigController(Interpreter intr) {
		this.interpreter = intr;		
	}
	
	public void saveAndStartAction(){
		Model config = Model.getInstance();
		ChimpDL contentManager = new ChimpDLFile() ;
		String path;
		try {
			path = Interpreter.getJarpath();
			contentManager.saveConfiguration(path, config.getDlContent());
			this.interpreter.start(path, true);
		} catch (PetriNetException | URISyntaxException e) {
			new ErrorDialog(e);
			return;
		}
		
	}
}
