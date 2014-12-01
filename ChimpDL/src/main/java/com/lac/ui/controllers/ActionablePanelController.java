package com.lac.ui.controllers;

import javax.swing.JPanel;

import com.lac.petrinet.exceptions.PetriNetException;

public abstract class ActionablePanelController<K extends JPanel> extends PanelController<K> implements Actionable{
	
	public void nextAction() throws PetriNetException {
		return;
	};
	
	public void renderAction() throws PetriNetException {
		return;
	};
	
}
