package com.lac.ui.home;

import java.net.URISyntaxException;

import com.lac.interpreter.ChimpDL;
import com.lac.interpreter.ChimpDLImpl;
import com.lac.interpreter.Interpreter;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.ErrorDialog;
import com.lac.ui.mainscreens.SaveConfigPanel;
import com.lac.userentry.ConfigurationEntryHolder;

public class SaveConfigHome extends Home<SaveConfigPanel> {

	@Override
	public void nextAction() throws PetriNetException {
		return;
	}

	@Override
	public void fowardRender() {
		return;
	}

	@Override
	public void backAction() throws PetriNetException {
		return;
	}
	
	public void saveAndStartAction(){
		
		Interpreter interpreter = ConfigurationEntryHolder.getInstance().getInterpreter();
		ConfigurationEntryHolder config = ConfigurationEntryHolder.getInstance();
		ChimpDL contentManager = new ChimpDLImpl() ;
		String path;
		try {
			path = Interpreter.getJarpath();
			contentManager.saveConfiguration(path, config.getDlContent());
			interpreter.startListening(path, true);
		} catch (PetriNetException | URISyntaxException e) {
			new ErrorDialog(e);
			return;
		}
		
	}
	

}
