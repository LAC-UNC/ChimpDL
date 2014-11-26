package com.lac.ui.mainscreens;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.FileSystemException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.lac.ui.home.PnmlSelectionHome;

public class PnmlSelectionPanel extends ControlledJPanel<PnmlSelectionHome> {
	
	public PnmlSelectionPanel(PnmlSelectionHome homeController) {
		super(homeController);
	}

	private JTextField pathTextField;


	public File browse() throws FileSystemException{
		Frame mainFrame = new Frame();
		FileDialog fd = new FileDialog(mainFrame, "Choose a file", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.pnml");
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
		else if(!filesSelected.getName().toLowerCase().endsWith(".pnml")){
			System.out.println("You must select a file with extension .pnml");
			throw new FileSystemException("cannot choose a file different from pnml type");
		}
		else{


			return filesSelected;
		}
	}

	public String getPath(){
		return pathTextField.getText();
	}

	@Override
	protected void initComponents() {
		setLayout(new BorderLayout(0, 0));

		JPanel titlePanel = new JPanel();
		add(titlePanel, BorderLayout.NORTH);

		JLabel titleLabel = new JLabel("Pnml file Selection");
		titlePanel.add(titleLabel);

		JPanel bodyPanel = new JPanel();
		add(bodyPanel, BorderLayout.CENTER);
//		bodyPanel.setLayout(new FormLayout(new ColumnSpec[] {
//				FormSpecs.UNRELATED_GAP_COLSPEC,
//				ColumnSpec.decode("40dlu"),
//				ColumnSpec.decode("152px:grow"),
//				ColumnSpec.decode("86px"),},
//				new RowSpec[] {
//				FormSpecs.LINE_GAP_ROWSPEC,
//				RowSpec.decode("20px"),
//				RowSpec.decode("29px"),
//				RowSpec.decode("25px"),}));

		JLabel filePathLabel = new JLabel("File path:");
		bodyPanel.add(filePathLabel, "2, 4, left, center");

		pathTextField = new JTextField();
		pathTextField.setText("C:\\\\");
		bodyPanel.add(pathTextField, "3, 4, fill, default");
		pathTextField.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					File pnmlFile = browse();
					if(pnmlFile != null)
						pathTextField.setText(pnmlFile.getAbsolutePath());
				}
				catch(FileSystemException fse){
					new ErrorDialog(fse);
				}
			}
		});
		bodyPanel.add(btnBrowse, "4, 4, center, center");		
	}
}
