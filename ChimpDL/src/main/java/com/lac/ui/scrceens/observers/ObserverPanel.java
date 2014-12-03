package com.lac.ui.scrceens.observers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lac.model.Model;
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
	private JComboBox<String> transitionInputComboBox;
	
	private void configurateObervser(){
		obsFrame.setVisible(true);
	}

	// potencially a problem on the assigment of iinput transition name due the type convertion. 
	/*public TasksContent getTask(){
		TasksContent task = new TasksContent();
		task.setActivities(obsFrame.getActivities());
		task.setInputTransitionName( new ArrayList(Arrays.asList(transitionInputComboBox.getSelectedObjects())));
		task.setName(taskNameTextField.getText());
		task.setOutputTransitionName(transitionOutputComboBox.getSelectedItem().toString());
		
		return task;
	}*/
	
	@Override
	protected void initComponents() {
		Model config = Model.getInstance();
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
		
		transitionInputComboBox = new JComboBox<String>();
		for(String inputTransitionName : config.getInformedTransitions()){
			transitionInputComboBox.addItem(inputTransitionName);
		}
		
		/* New Activity Button */
		JButton NewObserverButton = new JButton("Set observer transitions");
		observerPanel.add(NewObserverButton);
		NewObserverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(obsFrame == null){
					obsFrame = new ObserverConfigurationFrame();
				}
				
				configurateObervser();
			}
		});		
	}

	public ObserverConfigurationFrame getObserverConfigurationFrame() {
		if(obsFrame == null)
			this.obsFrame = new ObserverConfigurationFrame();
		return obsFrame;
	}

	public void setActFrame(ObserverConfigurationFrame obsFrame) {
		this.obsFrame = obsFrame;
	}

	public JComboBox<String> getTransitionInputComboBox() {
		return transitionInputComboBox;
	}

	public void setTransitionInputComboBox(JComboBox<String> transitionInputComboBox) {
		this.transitionInputComboBox = transitionInputComboBox;
	}

	public ObserverController getHome(){
		return homeController;
	}
	
}
