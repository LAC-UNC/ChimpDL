package com.lac.ui.mainscreens;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.FileSystemException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.home.LoadSessionHome;

public class LoadSessionPanel extends ControlledJPanel<LoadSessionHome> {
	
	
	public LoadSessionPanel(LoadSessionHome homeController) {
		super(homeController);
	}

	private JTextField pathTextField;

	
	protected void initComponents(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel ContentPanel = new JPanel();
		add(ContentPanel);
		ContentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		ContentPanel.add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("Load session ");
		titlePanel.add(titleLabel);
		
		JPanel panel = new JPanel();
		ContentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel explanationLabel = new JLabel("If you want to load a previous sesion select a file, if not, just continue");
		explanationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(explanationLabel);
		
		JPanel seleccionPanel = new JPanel();
		seleccionPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.add(seleccionPanel);
		seleccionPanel.setLayout(new BoxLayout(seleccionPanel, BoxLayout.X_AXIS));
		seleccionPanel.setMaximumSize(new Dimension(999,25));
		setPathTextField(new JTextField());
		seleccionPanel.add(getPathTextField());
		getPathTextField().setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					File confFile = homeController.browseAction();
					if(confFile != null)
						getPathTextField().setText(confFile.getAbsolutePath());
				}
				catch(FileSystemException fse){
					new ErrorDialog(fse);
				}
			}
		});
		seleccionPanel.add(btnNewButton);
		
		JButton btnLoadStart = new JButton("Load & start");
		btnLoadStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					homeController.loadSession();
				} catch (PetriNetException e1) {
					new ErrorDialog(e1);
				}
			}
		});
		seleccionPanel.add(btnLoadStart);
	}
	

	
	public JTextField getPathTextField() {
		return pathTextField;
	}

	public void setPathTextField(JTextField pathTextField) {
		this.pathTextField = pathTextField;
	}

}
