package com.lac.ui.scrceens.observers;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.lac.model.Model;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ObserverTransitionSelector extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JComboBox<String> informedTransitionsComboBox;
	ObserverConfigurationFrame parentFrame;
	
	/**
	 * Create the panel.
	 */	
	public ObserverTransitionSelector(ObserverConfigurationFrame observerConfigurationFrame) {
		this.parentFrame = observerConfigurationFrame;
		initComponents();
	}

	private void initComponents(){
		Model config = Model.getInstance();
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		informedTransitionsComboBox = new JComboBox<String>();
		for(String inputTransitionName : config.getInformedTransitions()){
			informedTransitionsComboBox.addItem(inputTransitionName);
		}
		add(informedTransitionsComboBox);
		this.setMaximumSize(new Dimension(999,25));
		this.setAlignmentY(Component.TOP_ALIGNMENT);
		
		JButton btnDeleteTransition = new JButton("Delete");
		btnDeleteTransition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAction();
			}
		});
		add(btnDeleteTransition);
	}
	
	public void deleteAction() {
		parentFrame.eraseTransitionSelector(this);
	}
	
	public String getSelectedTransition() {
		return (String) informedTransitionsComboBox.getSelectedItem();
	}

	public void setSelected(String it) {
		informedTransitionsComboBox.setSelectedItem(it);		
	}
}