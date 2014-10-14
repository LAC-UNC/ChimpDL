package com.lac.ui.screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.FileSystemException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.lac.interpreter.Interpreter;
import com.lac.petrinet.exceptions.PetriNetException;

public class PnmlScreen {

	private JFrame frame;
	private JTextField pathTextField;
	private JLabel errorLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PnmlScreen window = new PnmlScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PnmlScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel titlePanel = new JPanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JLabel pnmlTitleLabel = new JLabel("Load PNML");
		titlePanel.add(pnmlTitleLabel);
		
		JPanel bodyPanel = new JPanel();
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel subtitleLabel = new JLabel("Pnml file path:");
		bodyPanel.add(subtitleLabel, "4, 6");
		
		pathTextField = new JTextField();
		pathTextField.setText("C:\\\\");
		pathTextField.setHorizontalAlignment(SwingConstants.LEFT);
		bodyPanel.add(pathTextField, "4, 8, fill, default");
		pathTextField.setColumns(10);
		errorLabel = new JLabel();
		errorLabel.setText("");
		
		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					File pnmlFile = browse();
					if(pnmlFile != null)
						pathTextField.setText(pnmlFile.getAbsolutePath());
				}
				catch(FileSystemException fse){
					errorLabel.setText(fse.getMessage());
					new ErrorDialog(fse.getMessage());
					
				}
			}
		});
		bodyPanel.add(browseButton, "4, 10, right, default");
		
		JLabel errorLabel = new JLabel("");
		bodyPanel.add(errorLabel, "4, 18");
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{188, 65, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 7;
		gbc_btnCancel.gridy = 0;
		panel.add(btnCancel, gbc_btnCancel);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptAction();
			}
		});
		btnAccept.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_btnAccept = new GridBagConstraints();
		gbc_btnAccept.insets = new Insets(0, 0, 0, 5);
		gbc_btnAccept.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnAccept.gridx = 8;
		gbc_btnAccept.gridy = 0;
		panel.add(btnAccept, gbc_btnAccept);
	}
	
	
	protected void acceptAction() {
		String path = pathTextField.getText();
		Interpreter interpreter = new Interpreter();
		try {
			interpreter.readPnmlFile(path);
			
		} catch (PetriNetException e) {
			new ErrorDialog(e.getMessage());
		}
		
	}

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

}
