package com.lac.ui.home;

import com.lac.petrinet.exceptions.PetriNetException;

public abstract class Home<K> {
	protected K baseComponent;
	
	public abstract void nextAction() throws PetriNetException;
	public abstract void fowardRender() throws PetriNetException;
	public abstract void backAction()throws PetriNetException;
	
	public void setBaseComponent(K baseComponent){
		this.baseComponent = baseComponent;
	}
	
}
