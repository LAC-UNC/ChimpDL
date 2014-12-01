package com.lac.ui.mainscreens;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import com.lac.ui.controllers.ActionablePanelController;

public abstract class ControlledJPanel<K extends ActionablePanelController<?>> extends JPanel {
	private static final long serialVersionUID = 1L;
	
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
