package com.lac.ui.mainscreens;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.lac.parsers.SessionLoader;
import com.lac.petrinet.exceptions.PetriNetException;

public class LoadSessionPanel extends JPanel {
	private JTextField pathTextField;

	/**
	 * Create the panel.
	 */
	public LoadSessionPanel() {
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
		pathTextField = new JTextField();
		seleccionPanel.add(pathTextField);
		pathTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					File confFile = browseAction();
					if(confFile != null)
						pathTextField.setText(confFile.getAbsolutePath());
				}
				catch(FileSystemException fse){
					new ErrorDialog(fse.getMessage());
				}
			}
		});
		seleccionPanel.add(btnNewButton);
		
		JButton btnLoadStart = new JButton("Load & start");
		btnLoadStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSession();
			}
		});
		seleccionPanel.add(btnLoadStart);

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
	
	public void loadSession(){
		SessionLoader loader = new SessionLoader();
		try {
			loader.startLoadedSession(pathTextField.getText(), (MainFrame) SwingUtilities.getWindowAncestor(this));
		} catch (PetriNetException | IOException e) {
			ErrorDialog errorDialog = new ErrorDialog(e.getMessage());
		}
	}

}
