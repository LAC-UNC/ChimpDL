package com.lac.ui.controllers;

import com.lac.petrinet.exceptions.PetriNetException;

public interface Actionable {
	public void nextAction() throws PetriNetException;
	
	public void renderAction() throws PetriNetException;
}
