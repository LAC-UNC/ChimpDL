package com.lac.ui.mainscreens;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lac.annotations.ResourcesFinder;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.home.LoadSessionHome;
import com.lac.ui.home.MainFrameHome;
import com.lac.ui.home.PnmlSelectionHome;
import com.lac.ui.home.ResourcesManagementHome;
import com.lac.ui.home.SaveConfigHome;
import com.lac.ui.home.TaskAssociationHome;

public class MainFrame extends JFrame {
	//panels 
	protected JPanel contentPane;
	protected PnmlSelectionPanel pnmlPanel ;
	protected TaskAssociationPanel taskAssocPanel ;
	protected ResourcesManagementPanel resourcePanel;
	protected LoadSessionPanel sessionPanel ;
	protected SaveConfigPanel saveConfigPanel ;

	// properties  

	// Homes 
	protected LoadSessionHome loadSessionHome ;
	protected MainFrameHome mainFrameHome ;
	protected PnmlSelectionHome pnmlSelectionHome ;
	protected ResourcesManagementHome resourcesHome ;
	private SaveConfigHome saveConfigHome ;
	private TaskAssociationHome taskAssociationHome ;

	/**
	 * Create the frame.
	 * TODO: improve the way that the home and ControlledJPanels are initialized.
	 */
	public MainFrame() {
		// load the resources 
		ResourcesFinder.getResources();

		// initialization of panels
		loadSessionHome = new LoadSessionHome(); 
		mainFrameHome = new MainFrameHome();
		pnmlSelectionHome = new PnmlSelectionHome();
		resourcesHome = new ResourcesManagementHome();
		saveConfigHome = new SaveConfigHome();
		taskAssociationHome = new TaskAssociationHome();
		
		sessionPanel = new LoadSessionPanel(loadSessionHome);
		//mainFrame is not a ControlledJPanel, that is why is not handle in this way. 
		pnmlPanel = new PnmlSelectionPanel(pnmlSelectionHome);
		resourcePanel = new ResourcesManagementPanel(resourcesHome);
		saveConfigPanel = new SaveConfigPanel(saveConfigHome);
		taskAssocPanel = new TaskAssociationPanel(taskAssociationHome);

		loadSessionHome.setBaseComponent(sessionPanel);
		mainFrameHome.setBaseComponent(this);
		pnmlSelectionHome.setBaseComponent(pnmlPanel);
		resourcesHome.setBaseComponent(resourcePanel);
		saveConfigHome.setBaseComponent(saveConfigPanel);
		taskAssociationHome.setBaseComponent(taskAssocPanel);
		
		//init frame components.
		initComponents(); 
	}

	private void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);


		contentPane.add(pnmlPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		JButton btnAtras = new JButton("Back");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainFrameHome.backAction();
				} catch (PetriNetException e1) {
					new ErrorDialog(e1);
				}
			}
		});
		buttonPanel.add(btnAtras);

		JButton btnAccept = new JButton("Next");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainFrameHome.nextAction();
				} catch (PetriNetException e1) {
					new ErrorDialog(e1);
				}
			}
		});
		buttonPanel.add(btnAccept);
	}

	public TaskAssociationPanel getTaskAssocPanel() {
		return taskAssocPanel;
	}

	public ResourcesManagementPanel getResourcePanel() {
		return resourcePanel;
	}

	public PnmlSelectionPanel getPnmlPanel() {
		return pnmlPanel;
	}

	public void setPnmlPanel(PnmlSelectionPanel pnmlPanel) {
		this.pnmlPanel = pnmlPanel;
	}

	public LoadSessionPanel getSessionPanel() {
		return sessionPanel;
	}

	public void setSessionPanel(LoadSessionPanel sessionPanel) {
		this.sessionPanel = sessionPanel;
	}

	public SaveConfigPanel getSaveConfigPanel() {
		return saveConfigPanel;
	}

	public void setSaveConfigPanel(SaveConfigPanel saveConfigPanel) {
		this.saveConfigPanel = saveConfigPanel;
	}

	public ResourcesManagementHome getResourcesHome() {
		return resourcesHome;
	}

	public void setResourcesHome(ResourcesManagementHome resourcesHome) {
		this.resourcesHome = resourcesHome;
	}

	public TaskAssociationHome getTaskAssociationHome() {
		return taskAssociationHome;
	}

	public void setTaskAssociationHome(TaskAssociationHome taskAssociationHome) {
		this.taskAssociationHome = taskAssociationHome;
	}

}
