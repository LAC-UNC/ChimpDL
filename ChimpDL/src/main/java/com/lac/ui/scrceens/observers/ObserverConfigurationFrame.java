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
	private List<ObserverTransitionSelector> informedTransitions = new ArrayList<ObserverTransitionSelector>(); 
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
				addNewTransitionSelectionPanel();
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
	
	private ObserverTransitionSelector addNewTransitionSelectionPanel(){
		ObserverTransitionSelector obsPanel = new ObserverTransitionSelector(this);
		informedTransitions.add(obsPanel);
		obsPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		obsPanel.setSize(obsPanel.getWidth(), 16);
		contentPane.add(obsPanel);
		revalidate();
		repaint();
		contentPane.revalidate();
		contentPane.revalidate();
		
		return obsPanel;
	}

	public ArrayList<String> getObservedInformedTransitions() {
		ArrayList<String> its = new ArrayList<String>();
		
		for(ObserverTransitionSelector panel : informedTransitions){
			its.add(panel.getSelectedTransition());
		}
		return its;
	}

	public void setObservedInformedTransitions(List<String> list) {
		for(String it : list){
			addNewTransitionSelectionPanel().setSelected(it);
		}
	}

	public void eraseTransitionSelector(ObserverTransitionSelector toErase) {
		informedTransitions.remove(toErase);
		contentPane.remove(toErase);
		revalidate();
		repaint();
		contentPane.revalidate();
		contentPane.revalidate();
	}
}
