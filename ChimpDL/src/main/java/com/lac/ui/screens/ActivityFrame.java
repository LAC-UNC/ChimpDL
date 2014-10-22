package com.lac.ui.screens;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lac.activities.DLContents.ActivityContent;
import com.lac.userentry.ResourceInstances;

public class ActivityFrame extends JFrame {

	private JPanel contentPane;
	private List<ResourceInstances> resourceInstancesNames;
	private List<ActivityPanel> activities = new ArrayList<ActivityPanel>(); 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityFrame frame = new ActivityFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ActivityFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel lblActivitiesList = new JLabel("Activities list");
		lblActivitiesList.setAlignmentY(Component.TOP_ALIGNMENT);
		lblActivitiesList.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblActivitiesList);
		
		JButton btnAddActivity = new JButton("add Activity");
		btnAddActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewActivityPanel();
			}
		});
		btnAddActivity.setAlignmentY(Component.TOP_ALIGNMENT);
		btnAddActivity.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(btnAddActivity);
	}
	
	private void addNewActivityPanel(){
		ActivityPanel actPanel = new ActivityPanel();
		activities.add(actPanel);
		actPanel.addResources(resourceInstancesNames);
		actPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		actPanel.setSize(139, 16);
		actPanel.setVisible(true);
		getContentPane().add(actPanel);
		
		revalidate();
		repaint();
	}

	public void setResourceInstances(List<ResourceInstances> instanceNames) {
		this.resourceInstancesNames = instanceNames;
		for(ActivityPanel activitiy : activities){
			activitiy.addResources(instanceNames);		
			
		}
	}
	
	public List<ActivityContent> getActivities(){
		List<ActivityContent> activityList = new ArrayList<ActivityContent>();
		for(ActivityPanel panel : activities){
			activityList.add(panel.getActivity());
		}
		
		return activityList;
	}

}
