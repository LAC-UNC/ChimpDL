package com.lac.ui.mainscreens;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import com.lac.ui.home.Home;

public abstract  class ControlledJPanel<K extends Home<?>> extends JPanel {
	
	protected K homeController;

	public ControlledJPanel(K homeController) {
		this.homeController = homeController;
		initComponents();
	}

	public ControlledJPanel(LayoutManager layout, K homeController) {
		super(layout);
		this.homeController = homeController;
		initComponents();
	}

	public ControlledJPanel(boolean isDoubleBuffered,K homeController) {
		super(isDoubleBuffered);
		this.homeController = homeController;
		initComponents();
	}

	public ControlledJPanel(LayoutManager layout, boolean isDoubleBuffered,K homeController) {
		super(layout, isDoubleBuffered);
		this.homeController = homeController;
		initComponents();
	}
	
	protected abstract void initComponents();

	public K getHomeController() {
		return homeController;
	}

}
