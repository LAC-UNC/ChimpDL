package com.lac.ui.scrceens.tasks;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lac.activities.DLContents.TasksContent;
import com.lac.ui.home.TaskHome;
import com.lac.ui.mainscreens.ControlledJPanel;
import com.lac.userentry.ConfigurationEntryHolder;

public class TaskPanel extends ControlledJPanel<TaskHome> {

	public TaskPanel(TaskHome homeController) {
		super(homeController);
	}

	private JPanel taskPanel;
	private JPanel Content;
	private ActivityFrame actFrame;
	private JComboBox<String> transitionInputComboBox;
	private JComboBox<String> transitionOutputComboBox;
	private JTextField taskNameTextField;
	
	private void editActivities(){
		actFrame.setVisible(true);
	}

	// potencially a problem on the assigment of iinput transition name due the type convertion. 
	public TasksContent getTask(){
		TasksContent task = new TasksContent();
		task.setActivities(actFrame.getActivities());
		task.setInputTransitionName( new ArrayList(Arrays.asList(transitionInputComboBox.getSelectedObjects())));
		task.setName(taskNameTextField.getText());
		task.setOutputTransitionName(transitionOutputComboBox.getSelectedItem().toString());
		
		return task;
	}
	
	@Override
	protected void initComponents() {
		ConfigurationEntryHolder config = ConfigurationEntryHolder.getInstance();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Content = new JPanel();
		add(Content);
		Content.setLayout(new BoxLayout(Content, BoxLayout.Y_AXIS));
		
		taskPanel = new JPanel();
		taskPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		Content.add(taskPanel);
		taskPanel.setSize(600, 80);
		
		taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.X_AXIS));
		
		/* Transition Input */
		JLabel transitionInputLabel = new JLabel("Transition Input:");
		taskPanel.add(transitionInputLabel);
		
		transitionInputComboBox = new JComboBox<String>();
		for(String inputTransitionName : config.getInformedTransitions()){
			transitionInputComboBox.addItem(inputTransitionName);
		}
		taskPanel.add(transitionInputComboBox);
		
		/* Transition Output */
		JLabel lblTransitionOutput = new JLabel("Transition output:");
		taskPanel.add(lblTransitionOutput);
		
		transitionOutputComboBox = new JComboBox<String>();
		for(String inputTransitionName : config.getFiredTransitions()){
			transitionOutputComboBox.addItem(inputTransitionName);
		}
		taskPanel.add(transitionOutputComboBox);
		
		/* Name */
		JLabel lblName = new JLabel("Name:");
		taskPanel.add(lblName);
		
		taskNameTextField = new JTextField();
		taskPanel.add(taskNameTextField);
		taskNameTextField.setColumns(12);
		
		/* New Activity Button */
		JButton NewActivityButton = new JButton("edit Activities");
		taskPanel.add(NewActivityButton);
		NewActivityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(actFrame == null){
					actFrame = new ActivityFrame();
					actFrame.setVisible(true);
				}
				
				editActivities();
			}
		});		
	}

	public ActivityFrame getActFrame() {
		if(actFrame == null)
			this.actFrame = new ActivityFrame();
		return actFrame;
	}

	public void setActFrame(ActivityFrame actFrame) {
		this.actFrame = actFrame;
	}

	public JComboBox<String> getTransitionInputComboBox() {
		return transitionInputComboBox;
	}

	public void setTransitionInputComboBox(JComboBox<String> transitionInputComboBox) {
		this.transitionInputComboBox = transitionInputComboBox;
	}

	public JComboBox<String> getTransitionOutputComboBox() {
		return transitionOutputComboBox;
	}

	public void setTransitionOutputComboBox(
			JComboBox<String> transitionOutputComboBox) {
		this.transitionOutputComboBox = transitionOutputComboBox;
	}

	public JTextField getTaskNameTextField() {
		return taskNameTextField;
	}

	public void setTaskNameTextField(JTextField taskNameTextField) {
		this.taskNameTextField = taskNameTextField;
	}
	public TaskHome getHome(){
		return homeController;
	}
	
}
