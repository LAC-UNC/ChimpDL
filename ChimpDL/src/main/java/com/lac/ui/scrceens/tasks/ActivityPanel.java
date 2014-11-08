package com.lac.ui.scrceens.tasks;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.lac.activities.DLContents.ActivityContent;
import com.lac.activities.DLContents.ResourcesContent;
import com.lac.ui.mainscreens.ErrorDialog;
import com.lac.userentry.ConfigurationEntryHolder;

public class ActivityPanel extends JPanel {

	JComboBox<String> resourcesComboBox;
	JComboBox<String> methodComboBox;	
	Set<String> instanceNamesSet = new HashSet<String>();
	Map<String, String> instanceMap = new HashMap<String, String>();
	
	/**
	 * Create the panel.
	 */
	public ActivityPanel() {
		initComponents();
		addResources(ConfigurationEntryHolder.getInstance().getDlContent().getResources());
	}
	
	private void initComponents(){
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		resourcesComboBox = new JComboBox<String>();
		resourcesComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					changeMethodComboBox(e);
				} catch (SecurityException | ClassNotFoundException e1) {
					new ErrorDialog(e1);
				}
			}
		});
		resourcesComboBox.setMaximumRowCount(20);
		add(resourcesComboBox);
		
		methodComboBox = new JComboBox<String>();
		methodComboBox.setMaximumRowCount(20);
		add(methodComboBox);
		this.setMaximumSize(new Dimension(999,25));
		this.setAlignmentY(Component.TOP_ALIGNMENT);
	}
	
	public void addResources(List<ResourcesContent> instanceNames){
		for(ResourcesContent instanceInfo : instanceNames){
			instanceMap.put(instanceInfo.getImplementationName(), instanceInfo.getClassName());
			resourcesComboBox.addItem(instanceInfo.getImplementationName());
		}
		resourcesComboBox.validate();
		resourcesComboBox.repaint();
	}
	
	public ActivityContent getActivity(){
		ActivityContent content = new ActivityContent();
		content.setMethod(methodComboBox.getSelectedItem().toString());
		content.setObj(resourcesComboBox.getSelectedItem().toString());
		return content;
	}
	
	protected void changeMethodComboBox(ActionEvent e) throws SecurityException, ClassNotFoundException{
		@SuppressWarnings("unchecked")
		String className = instanceMap.get(((JComboBox<String>) e.getSource()).getSelectedItem());
		methodComboBox.removeAllItems();
		for(Method m : Class.forName(className).getDeclaredMethods()){
			methodComboBox.addItem(m.getName());
		}
		
	}
	
	public int getComboBoxesWidth(){
		return methodComboBox.getWidth()+resourcesComboBox.getWidth();
	}
	
	public void addActivity(String instanceName, String method ) throws SecurityException, ClassNotFoundException{
		resourcesComboBox.setSelectedItem(instanceName);
		ActionEvent e = new ActionEvent(resourcesComboBox, 1, "");
		changeMethodComboBox(e);
		methodComboBox.setSelectedItem(method);
	}
	
}
