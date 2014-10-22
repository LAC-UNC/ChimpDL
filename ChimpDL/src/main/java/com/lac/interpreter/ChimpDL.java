package com.lac.interpreter;

import com.lac.activities.DLContents.DLContent;
import com.lac.petrinet.exceptions.PetriNetException;

public interface ChimpDL {

	public DLContent getDescription() throws PetriNetException ;
	
	public void saveConfiguration(String path , DLContent content) throws PetriNetException ;
	
}
