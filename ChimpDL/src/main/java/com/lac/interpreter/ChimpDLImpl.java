package com.lac.interpreter;

import java.io.File;

import org.codehaus.jackson.map.ObjectMapper;

import com.lac.activities.DLContents.DLContent;
import com.lac.petrinet.exceptions.PetriNetException;

public class ChimpDLImpl implements ChimpDL {

	@Override
	public DLContent getDescription() {
		
		
		return null;
	}

	@Override
	public void saveConfiguration(String path , DLContent content) throws PetriNetException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(path + "Configuration.conf" ), content);
		} catch (Exception e) {
			throw new PetriNetException(e.getMessage(),e);
		}
	}

}
