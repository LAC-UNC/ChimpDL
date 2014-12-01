package com.lac.ui.controllers;

import javax.swing.JPanel;

public class PanelController<K extends JPanel> {
	protected K baseComponent;
	
	public void setComponentVisible(boolean b){
		baseComponent.setVisible(b);
	}
	
	public void setBaseComponent(K baseComponent){
		this.baseComponent = baseComponent;
	}
	
	public K getBaseComponent(){
		return this.baseComponent;
	}
}
