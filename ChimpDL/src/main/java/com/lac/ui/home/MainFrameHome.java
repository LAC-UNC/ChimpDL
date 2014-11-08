package com.lac.ui.home;

import java.awt.BorderLayout;

import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.ControlledJPanel;
import com.lac.ui.mainscreens.ErrorDialog;
import com.lac.ui.mainscreens.MainFrame;

public class MainFrameHome extends Home<MainFrame> {
	
	@Override
	public void nextAction() throws PetriNetException {
		try{
			if(baseComponent.getPnmlPanel().isVisible()){
				changeContentPanel(baseComponent.getPnmlPanel(), baseComponent.getSessionPanel(), true);
			}
			else if(baseComponent.getSessionPanel().isVisible()){
				changeContentPanel(baseComponent.getSessionPanel(), baseComponent.getResourcePanel(), true);
			}
			else if(baseComponent.getResourcePanel().isVisible()){
				changeContentPanel(baseComponent.getResourcePanel(), baseComponent.getTaskAssocPanel(), true);
			}
			else if(baseComponent.getTaskAssocPanel().isVisible()){
				changeContentPanel(baseComponent.getTaskAssocPanel(), baseComponent.getSaveConfigPanel(),  true);
			}
		}catch (Exception e) {
			new ErrorDialog(e);
		}
		
	}

	@Override
	public void fowardRender() {
		return;
	}

	@Override
	public void backAction() throws PetriNetException {
		if(baseComponent.getPnmlPanel().isVisible()){
		}
		else if(baseComponent.getSessionPanel().isVisible()){
			changeContentPanel(baseComponent.getSessionPanel(), baseComponent.getPnmlPanel(), false);
		}
		else if(baseComponent.getResourcePanel().isVisible()){
			changeContentPanel(baseComponent.getResourcePanel(),baseComponent.getSessionPanel(),  false);
		}
		else if(baseComponent.getTaskAssocPanel().isVisible()){
			changeContentPanel(baseComponent.getTaskAssocPanel(),baseComponent.getResourcePanel(), false);
		}
		else if(baseComponent.getSaveConfigPanel().isVisible()){
			changeContentPanel(baseComponent.getSaveConfigPanel(), baseComponent.getTaskAssocPanel(), false);
		}
	}
	
	private void changeContentPanel(ControlledJPanel<?> source, ControlledJPanel<?> target, boolean isFoward ) throws PetriNetException{
		if(source.getHomeController() != null && isFoward)
			source.getHomeController() .nextAction();
		else if(source.getHomeController()  != null && ! isFoward)
			source.getHomeController() .backAction();
		source.setVisible(false);
		baseComponent.remove(source);
		target.setVisible(true);
		baseComponent.getContentPane().add(target, BorderLayout.CENTER);
		if(target.getHomeController()  != null && isFoward)
			target.getHomeController() .fowardRender();
		else if((target.getHomeController()  != null && ! isFoward))
			target.getHomeController() .backAction();
		baseComponent.getContentPane().revalidate();
		baseComponent.getContentPane().repaint();
		baseComponent.revalidate();
		baseComponent.repaint();
	}


}
