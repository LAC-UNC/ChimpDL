package com.lac.ui.scrceens.observers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lac.ui.controllers.ObserverController;
import com.lac.ui.mainscreens.ControlledJPanel;

public class ObserverPanel extends ControlledJPanel<ObserverController> {
	private static final long serialVersionUID = 1L;

	public ObserverPanel(ObserverController homeController) {
		super(homeController);
	}

	private JPanel observerPanel;
	private JPanel Content;
	private ObserverConfigurationFrame obsFrame;
	
	private void configurateOberver(){
		obsFrame.setVisible(true);
	}
	
	@Override
	protected void initComponents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Content = new JPanel();
		add(Content);
		Content.setLayout(new BoxLayout(Content, BoxLayout.Y_AXIS));
		
		observerPanel = new JPanel();
		observerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		Content.add(observerPanel);
		observerPanel.setSize(600, 80);
		
		observerPanel.setLayout(new BoxLayout(observerPanel, BoxLayout.X_AXIS));
		
		/* Transition Input */
		JLabel transitionInputLabel = new JLabel("Observer");
		observerPanel.add(transitionInputLabel);
		
		/* New Activity Button */
		JButton NewObserverButton = new JButton("Set observer transitions");
		observerPanel.add(NewObserverButton);
		NewObserverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(obsFrame == null){
					obsFrame = new ObserverConfigurationFrame();
				}
				
				configurateOberver();
			}
		});		
	}

	public ObserverConfigurationFrame getObserverConfigurationFrame() {
		if(obsFrame == null)
			this.obsFrame = new ObserverConfigurationFrame();
		return obsFrame;
	}

	public void setObsFrame(ObserverConfigurationFrame obsFrame) {
		this.obsFrame = obsFrame;
	}

	public ArrayList<String> getObservedInformedTransitions() {
		return obsFrame.getObservedInformedTransitions();
	}

	public ObserverController getController(){
		return homeController;
	}
	
	public void setObservedInformedTransitions(List<String> list) {
		getObserverConfigurationFrame().setObservedInformedTransitions(list);
	}
	
}
