package com.lac.ui.controllers;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;

import com.lac.activities.DLContents.DLContent;
import com.lac.interpreter.ChimpDLFile;
import com.lac.model.Model;
import com.lac.parsers.JsonParser;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.LoadSessionPanel;

public class LoadSessionController extends ActionablePanelController<LoadSessionPanel>{

	public void loadSession() throws PetriNetException{
		JsonParser parser = new JsonParser();
		DLContent dlContent ;
		try {
			dlContent = parser.parse((new ChimpDLFile()).getDescription(baseComponent.getPathTextField().getText() ));
		} catch (PetriNetException | IOException e) {
			throw new PetriNetException(e.getMessage(),e);
		}
		Model.getInstance().setDlContent(dlContent);
		
	}
	
	public File browseAction() throws FileSystemException{
		Frame mainFrame = new Frame();
		FileDialog fd = new FileDialog(mainFrame, "Choose a file", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.conf");
		fd.setVisible(true);
		fd.setMultipleMode(false);
		
		if (fd.getFiles().length != 1){
			return null;
		}
		File filesSelected = fd.getFiles()[0];

		if(filesSelected.isDirectory()){
			System.out.println("You can not select a directory");
			throw new FileSystemException("cannot choose a directory for pnml file");
		}
		else if(!filesSelected.getName().toLowerCase().endsWith(".conf")){
			System.out.println("You must select a file with extension .pnml");
			throw new FileSystemException("cannot choose a file different from pnml type");
		}
		else{
			return filesSelected;
		}
		
	}
	
}
