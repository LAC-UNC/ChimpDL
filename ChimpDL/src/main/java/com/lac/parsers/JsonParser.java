package com.lac.parsers;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.lac.activities.DLContents.DLContent;
import com.lac.petrinet.exceptions.PetriNetException;

public class JsonParser {
	public DLContent parse(String jsonContent) throws PetriNetException {
		// get all the description of the resources and tasks that will be used. 
		DLContent objectDescription ;
		try{
			objectDescription = getObjectsDescription(jsonContent);
		}catch(Exception e){
			throw new PetriNetException(e.getMessage(),e);
		}
		
		return objectDescription;
	}
	
	public DLContent getObjectsDescription(File jsonFile) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonFile, DLContent.class);
	}
	
	public DLContent getObjectsDescription(String jsonFileContent) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonFileContent, DLContent.class);
	}
}
