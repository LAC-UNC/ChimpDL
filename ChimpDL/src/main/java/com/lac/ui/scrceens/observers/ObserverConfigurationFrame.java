package com.lac.ui.scrceens.observers;

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

public class ObserverConfigurationFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private List<ObserverConfigurationPanel> activities = new ArrayList<ObserverConfigurationPanel>(); 
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObserverConfigurationFrame frame = new ObserverConfigurationFrame();
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
	public ObserverConfigurationFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 658, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel lblInformedTransitionsList = new JLabel("Informed transitions list");
		lblInformedTransitionsList.setAlignmentY(Component.TOP_ALIGNMENT);
		lblInformedTransitionsList.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblInformedTransitionsList);
		
		JButton btnAddTransition = new JButton("add transition");
		btnAddTransition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewObserverPanel();
			}
		});
		btnAddTransition.setAlignmentY(Component.TOP_ALIGNMENT);
		btnAddTransition.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(btnAddTransition);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(contentPane);
		scrollPane.setPreferredSize(this.getSize());
		setContentPane(scrollPane);
	}
	
	private void addNewObserverPanel(){
		ObserverConfigurationPanel actPanel = new ObserverConfigurationPanel();
		activities.add(actPanel);
		actPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		actPanel.setSize(actPanel.getWidth(), 16);
		contentPane.add(actPanel);
		revalidate();
		repaint();
		contentPane.revalidate();
		contentPane.revalidate();
	}

	// TODO: probably this should be move to a home functionality. 
	/*public List<ActivityContent> getActivities(){
		List<ActivityContent> activityList = new ArrayList<ActivityContent>();
		for(ObserverPanel panel : activities){
			activityList.add(panel.getActivity());
		}
		return activityList;
	}*/

}
