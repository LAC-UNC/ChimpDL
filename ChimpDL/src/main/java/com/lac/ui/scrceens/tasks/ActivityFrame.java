package com.lac.ui.scrceens.tasks;

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
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.lac.activities.DLContents.ActivityContent;

public class ActivityFrame extends JFrame {

	private JPanel contentPane;
	private List<ActivityPanel> activities = new ArrayList<ActivityPanel>(); 
	private JScrollPane scrollPane;

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
		setBounds(100, 100, 658, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
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
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(contentPane);
		scrollPane.setPreferredSize(this.getSize());
		setContentPane(scrollPane);
	}
	
	private void addNewActivityPanel(){
		ActivityPanel actPanel = new ActivityPanel();
		activities.add(actPanel);
		actPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		actPanel.setSize(actPanel.getWidth(), 16);
		contentPane.add(actPanel);
		revalidate();
		repaint();
		contentPane.revalidate();
		contentPane.revalidate();
	}
	
	public void addActivityPanel(ActivityPanel actPanel){
		activities.add(actPanel);
		actPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		actPanel.setSize(actPanel.getWidth(), 16);
		contentPane.add(actPanel);
	}

	// TODO: probably this should be move to a home functionality. 
	public List<ActivityContent> getActivities(){
		List<ActivityContent> activityList = new ArrayList<ActivityContent>();
		for(ActivityPanel panel : activities){
			activityList.add(panel.getActivity());
		}
		return activityList;
	}

}
