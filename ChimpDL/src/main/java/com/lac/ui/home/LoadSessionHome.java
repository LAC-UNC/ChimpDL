package com.lac.ui.home;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;

import com.lac.activities.DLContents.DLContent;
import com.lac.interpreter.ChimpDLFile;
import com.lac.parsers.JsonParser;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.LoadSessionPanel;
import com.lac.userentry.ConfigurationEntryHolder;

public class LoadSessionHome extends Home<LoadSessionPanel>{

	@Override
	public void nextAction() throws PetriNetException {
		//probably at some point would be desirable to have the load session the next button. 
		// in that case, that functionality should be placed here. 
		return;
	}

	@Override
	public void fowardRender() {
		return;
	}

	@Override
	public void backAction() throws PetriNetException {
		return;
	}

	public void loadSession() throws PetriNetException{
		JsonParser parser = new JsonParser();
		DLContent dlContent ;
		try {
			dlContent = parser.parse((new ChimpDLFile()).getDescription(baseComponent.getPathTextField().getText() ));
		} catch (PetriNetException | IOException e) {
			throw new PetriNetException(e.getMessage(),e);
		}
		ConfigurationEntryHolder.getInstance().setDlContent(dlContent);
		
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
