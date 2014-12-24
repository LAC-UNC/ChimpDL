package com.lac.ui.scrceens.observers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.lac.ui.scrceens.observers.transition.TransitionObserver;

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

	protected void initComponents(){
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		informedTransitionsComboBox = new JComboBox<String>();
		for(String transition : TransitionObserver.getInstance().addObserver(this)){
			informedTransitionsComboBox.addItem(transition);
		}
		TransitionObserver.getInstance().updateSelected((String)this.informedTransitionsComboBox.getSelectedItem(), ItemEvent.SELECTED, this);
		informedTransitionsComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemStateChantedAction(e);
			}
		});
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
	
	protected void itemStateChantedAction(ItemEvent e) {
		TransitionObserver.getInstance().updateSelected((String)e.getItem(),e.getStateChange(),this);		
	}

	public void deleteAction() {
		TransitionObserver.getInstance().updateSelected((String)this.informedTransitionsComboBox.getSelectedItem(), ItemEvent.DESELECTED, this);
		TransitionObserver.getInstance().removeObserver(this);
		parentFrame.eraseTransitionSelector(this);
	}
	
	public String getSelectedTransition() {
		return (String) informedTransitionsComboBox.getSelectedItem();
	}

	public void setSelected(String it) {
		informedTransitionsComboBox.setSelectedItem(it);		
	}
	
	//TODO: create a way to update the view for the transitions list updated. 
	public synchronized void updateRemovingTransitions(String transition){
		informedTransitionsComboBox.removeItem(transition);
	}
	
	public synchronized void updateAddingTransitioins(String transition){
		informedTransitionsComboBox.addItem(transition);
	}
	
	public synchronized void updateTransitionList(List<String> transitions){
		for(String item : transitions){
			this.informedTransitionsComboBox.addItem(item);
		}
	}
}
