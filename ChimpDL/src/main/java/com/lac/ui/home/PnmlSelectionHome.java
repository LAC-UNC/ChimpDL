package com.lac.ui.home;

import com.lac.interpreter.Interpreter;
import com.lac.petrinet.core.PetriNet;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.PnmlSelectionPanel;
import com.lac.userentry.ConfigurationEntryHolder;

public class PnmlSelectionHome extends Home<PnmlSelectionPanel> {

	@Override
	public void nextAction() throws PetriNetException {
		String path = baseComponent.getPath();
		ConfigurationEntryHolder.getInstance().setInterpreter(new Interpreter());
		PetriNet petriNet = ConfigurationEntryHolder.getInstance().getInterpreter().readPnmlFile(path);
		ConfigurationEntryHolder.getInstance().setPetriNet(petriNet);		
	}

	@Override
	public void fowardRender() {
		return;
	}

	@Override
	public void backAction() throws PetriNetException {
		return;
	}

}
