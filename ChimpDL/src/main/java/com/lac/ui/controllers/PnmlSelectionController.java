package com.lac.ui.controllers;

import com.lac.interpreter.Interpreter;
import com.lac.model.Model;
import com.lac.petrinet.core.PetriNet;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.PnmlSelectionPanel;

public class PnmlSelectionController extends ActionablePanelController<PnmlSelectionPanel> {

	private Interpreter interpreter;
	
	public PnmlSelectionController(Interpreter intr) {
		this.interpreter = intr;		
	}
	
	@Override
	public void nextAction() throws PetriNetException {
		String path = baseComponent.getPath();
		PetriNet petriNet = interpreter.readPnmlFile(path);
		Model.getInstance().setPetriNet(petriNet);		
	}
}
