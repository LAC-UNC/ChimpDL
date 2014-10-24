package com.lac.activities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lac.petrinet.components.Dummy;
import com.lac.petrinet.core.PetriNet;
import com.lac.petrinet.exceptions.PetriNetException;

public class Task extends Dummy{

	private List<Activity> activities;
	
	public Task(PetriNet pn, String outputTransitionName) {
		super(outputTransitionName);
		activities = new ArrayList<Activity>();
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
	
	public void addActivity(Activity act){
		activities.add(act);
	}
	
	public void addAllActivities(Collection<? extends Activity> activityList){
		activities.addAll(activityList);
	}

}
