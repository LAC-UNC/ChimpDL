package com.lac.parsers;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.lac.activities.DLContents.DLContent;

public class JsonParser {

	public DLContent parse(File jsonFile) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonFile, DLContent.class);
	}
	
	public DLContent parse(String jsonFileContent) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonFileContent, DLContent.class);
	}
}
