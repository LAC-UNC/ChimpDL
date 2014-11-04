package com.lac.interpreter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.lac.activities.DLContents.DLContent;
import com.lac.petrinet.exceptions.PetriNetException;

public class ChimpDLImpl implements ChimpDL {

	@Override
	public String getDescription(String path) throws PetriNetException {
		try {
			return FileUtils.readFileToString(new File(path ));
		} catch (IOException e) {
			throw new PetriNetException(e.getMessage(), e);
		}
	}

	@Override
	public void saveConfiguration(String path , DLContent content) throws PetriNetException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			if(! ( path.endsWith("\\") || path.endsWith("/"))){
				path = path.concat("/");
			}
			mapper.writeValue(new File(path + "Configuration.conf" ), content);
		} catch (Exception e) {
			throw new PetriNetException(e.getMessage(),e);
		}
	}
	
//	private String getJarpath() throws URISyntaxException {
//		String uri;
//		uri = ChimpDLImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
//		while(uri.contains(".jar")){
//			uri = uri.substring(0,  uri.lastIndexOf("/"));
//		}
//		if(! uri.endsWith("/") || ! uri.endsWith("\\")){
//			uri = uri+"/";
//		}
//		return uri;
//	}

}
