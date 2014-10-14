package com.lac.interpreter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;

import com.lac.petrinet.configuration.ConfigurationReader;
import com.lac.petrinet.configuration.providers.PNMLConfigurationReader;
import com.lac.petrinet.core.PetriNet;
import com.lac.petrinet.exceptions.PetriNetException;

public class Interpreter {

	private Parser parser;
	private ConfigurationReader configReader;
	private PetriNet petriNet;
	
	public void start(ChimpDL chimpReader, String pnmlPath) throws PetriNetException{
		// Get the pnml configuration 
		configReader = new PNMLConfigurationReader();
		petriNet = configReader.loadConfiguration(pnmlPath);
		
		// create the resources and tasks that will be used
		parser = new Parser(petriNet);
		try {
			 parser.parseAndCreateObjects(FileUtils.readFileToString(new File(getJarpath() + "./configuration.conf")));
		} catch (IOException | URISyntaxException e) {
			throw new PetriNetException(e.getMessage(),e);
		}
		
		//start  the application
		petriNet.startListening();
		
	}
	
	public void readPnmlFile(String pnmlPath) throws PetriNetException{
		// Get the pnml configuration 
		configReader = new PNMLConfigurationReader();
		petriNet = configReader.loadConfiguration(pnmlPath);
		
		// create the resources and tasks that will be used
		parser = new Parser(petriNet);
		try {
			 parser.parseAndCreateObjects(FileUtils.readFileToString(new File(getJarpath() + "./configuration.conf")));
		} catch (IOException | URISyntaxException e) {
			throw new PetriNetException(e.getMessage(),e);
		}
	}
	
	public void startListening(){
		//start  the application
		petriNet.startListening();
	}
	
	private String getJarpath() throws URISyntaxException {
		final String uri;
		uri = ParserTest.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		return uri;
	}
	
}
