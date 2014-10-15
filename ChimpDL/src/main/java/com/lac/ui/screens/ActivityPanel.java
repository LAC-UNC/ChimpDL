package com.lac.ui.screens;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class ActivityPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ActivityPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JComboBox comboBox = new JComboBox();
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		add(comboBox_1);

	}

}
