package com.lac.activities;

import java.util.List;

import com.lac.petrinet.components.Dummy;
import com.lac.petrinet.core.PetriNet;
import com.lac.petrinet.exceptions.PetriNetException;

public class Task extends Dummy{

	private List<Activity> activities;
	
	protected Task(PetriNet pn, String tName) {
		super(pn, tName);
	}

	@Override
	protected void execute() throws PetriNetException {
		for(Activity act : activities){
			try {
				act.call();
			} catch (Exception e) {
				throw new PetriNetException(e.getMessage(),e);
			}
		}
		
	}

}
