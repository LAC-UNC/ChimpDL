package com.lac.interpreter;

import java.net.URISyntaxException;

import com.lac.parsers.FunctionalParser;
import com.lac.petrinet.configuration.ConfigurationReader;
import com.lac.petrinet.configuration.providers.PNMLConfigurationReader;
import com.lac.petrinet.core.PetriNet;
import com.lac.petrinet.exceptions.PetriNetException;

public class Interpreter {

	private FunctionalParser parser;
	private ConfigurationReader configReader;
	private PetriNet petriNet;
	
	public void start(ChimpDL chimpReader, String pnmlPath) throws PetriNetException{
		// Get the pnml configuration 
		configReader = new PNMLConfigurationReader();
		petriNet = configReader.loadConfiguration(pnmlPath); 
		
		// create the resources and tasks that will be used
		parser = new FunctionalParser(petriNet);
		try {
			 parser.parseAndCreate( (new ChimpDLImpl()).getDescription(getJarpath() + "./configuration.conf") );
		} catch (URISyntaxException e) {
			throw new PetriNetException(e.getMessage(),e);
		}
		
		//start  the application
		petriNet.startListening();
		
	}
	
	public PetriNet readPnmlFile(String pnmlPath) throws PetriNetException{
		// Get the pnml configuration 
		configReader = new PNMLConfigurationReader();
		petriNet = configReader.loadConfiguration(pnmlPath);
		return petriNet;
		
	}
	
	public void startListening(String ConfigFileLocation, boolean isParentLocation) throws PetriNetException{
		// create the resources and tasks that will be used
		parser = new FunctionalParser(petriNet);
		try {
			if(isParentLocation)
				parser.parseAndCreate((new ChimpDLImpl()).getDescription(ConfigFileLocation + "./configuration.conf"));
			else
				parser.parseAndCreate((new ChimpDLImpl()).getDescription(ConfigFileLocation ));
				
		} catch (PetriNetException e) {
			throw new PetriNetException(e.getMessage(),e);
		}
		
		//start  the application
		petriNet.startListening();
	}
	
	private String getJarpath() throws URISyntaxException {
		final String uri;
		uri = Interpreter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		return uri;
	}
	
}
