package com.lac.ui.screens;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.FileSystemException;

public class PnmlSelectorScreen extends Panel {

	TextField fileLocationTextField = new TextField();
	Label pnmlSelectorLabel = new Label();


	public PnmlSelectorScreen(){
		setLayout(new BorderLayout());

		Panel centerPanel = new Panel();
		// selector label
		pnmlSelectorLabel.setText("Select pnml file: ");
		centerPanel.add(pnmlSelectorLabel);

		// Text Fields for the file location
		fileLocationTextField.setColumns(30);
		centerPanel.add(fileLocationTextField);

		// Browse button
		Button browseButton = new Button("Browse");
		browseButton.setLabel("browse");
		browseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					File pnmlFile = browse();
					fileLocationTextField.setText(pnmlFile.getAbsolutePath());
				}
				catch(FileSystemException fse){
					showError(fse);
				}
				
			}
		});
		centerPanel.add(browseButton);
		add("Center",centerPanel);
	}

	
	/**
	 * TODO: render the error.
	 * @param fse
	 */
	protected void showError(FileSystemException fse) {
		Dialog erroDialog = new Dialog(new Frame(), "Error");
		Label errorLabel = new Label();
		errorLabel.setText("Could not load file. Reason: " + fse.getMessage());
		erroDialog.add("Center", errorLabel);
		System.out.println("Yasdasdasdasdry");
		paintComponents(getGraphics());
		Button acceptButton = new Button("Accept");
		acceptButton.setSize(200, getHeight());
		erroDialog.add( "South",acceptButton);
		erroDialog.pack();
		erroDialog.setVisible(true);
	}

	public File browse() throws FileSystemException{
		Frame mainFrame = new Frame();
		FileDialog fd = new FileDialog(mainFrame, "Choose a file", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.pnml");
		fd.setVisible(true);
		fd.setMultipleMode(false);

		if (fd.getFiles().length != 1){
			System.out.println("Browse operation cancelled.");
			throw new FileSystemException("cannot choose a directory for pnml file");
		}
		File filesSelected = fd.getFiles()[0];

		if(filesSelected.isDirectory()){
			System.out.println("You can not select a directory");
			throw new FileSystemException("cannot choose a directory for pnml file");
		}
		else if(!filesSelected.getName().toLowerCase().endsWith(".pnml")){
			System.out.println("You must select a file with extension .pnml");
			throw new FileSystemException("cannot choose a file different from pnml type");
		}
		else{


			return filesSelected;
		}
	}


}
