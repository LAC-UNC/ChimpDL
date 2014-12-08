package com.lac.interpreter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import com.lac.activities.DLContents.DLContent;
import com.lac.interpreter.mock.SimpleJsonContainer;
import com.lac.interpreter.mock.SimpleJsonContainerInnerClass;
import com.lac.parsers.JsonParser;
import com.lac.petrinet.exceptions.PetriNetException;

public class ParserTest {
	
	

	@Test
	public void validParseTest() throws IOException, URISyntaxException, PetriNetException{
		String content = FileUtils.readFileToString(new File(getJarpath()+"/JsonFiles/config json example.txt"));
		JsonParser parser = new JsonParser();
		parser.parse(content);
	}
	
	@Test//(expectedExceptions = PetriNetException.class)
	public void invalidParseTest() throws  PetriNetException, IOException, URISyntaxException{
		String content = FileUtils.readFileToString(new File(getJarpath()+"/JsonFiles/config json example Invalid.txt"));
		JsonParser parser = new JsonParser();
		parser.parse(content);
	}
	
	@Test public void rightContentJsonMapping() throws JsonParseException, JsonMappingException, IOException, URISyntaxException{
		ObjectMapper mapper = new ObjectMapper();
		DLContent result = mapper.readValue(new File(getJarpath()+"/JsonFiles/config json example.txt"),
				DLContent.class);
		Assert.assertEquals("implName0", result.getResources().get(0).getImplementationName());
		Assert.assertEquals("com.lac.interpreter.mock.Class0", result.getResources().get(0).getClassName());
		Assert.assertEquals("implName1", result.getResources().get(1).getImplementationName());
		Assert.assertEquals("com.lac.interpreter.mock.Class1", result.getResources().get(1).getClassName());
		
		//tasks
		// index 0 
		Assert.assertEquals("T1", result.getTasks().get(0).getInputTransitionName().get(0));
		Assert.assertEquals("T2", result.getTasks().get(0).getOutputTransitionName());
			// activity 0
			Assert.assertEquals("implName0", result.getTasks().get(0).getActivities().get(0).getObj());
			Assert.assertEquals("method0", result.getTasks().get(0).getActivities().get(0).getMethod());
			// activity 1
			Assert.assertEquals("implName0", result.getTasks().get(0).getActivities().get(1).getObj());
			Assert.assertEquals("method1", result.getTasks().get(0).getActivities().get(1).getMethod());
		
		// index 1
		Assert.assertEquals("T3", result.getTasks().get(1).getInputTransitionName().get(0));
		Assert.assertEquals("T4", result.getTasks().get(1).getOutputTransitionName());
			// activity 0
			Assert.assertEquals("implName1", result.getTasks().get(1).getActivities().get(0).getObj());
			Assert.assertEquals("method0", result.getTasks().get(1).getActivities().get(0).getMethod());
			// activity 1
			Assert.assertEquals("implName1", result.getTasks().get(1).getActivities().get(1).getObj());
			Assert.assertEquals("method1", result.getTasks().get(1).getActivities().get(1).getMethod());
	}
	
	
	/*@Test
	public void validMethodExecution() throws Exception{
		
		PNMLConfigurationReader configReader = new PNMLConfigurationReader();
		PetriNet petriNet = configReader.loadConfiguration("target/classes/pnml/validMethodExecution.pnml");
		
		ObjectMapper mapper = new ObjectMapper();
		DLContent result = mapper.readValue(new File(getJarpath()+"/JsonFiles/ParserTest.conf"),
				DLContent.class);
		
		
		
		String content = FileUtils.readFileToString(new File(getJarpath()+"/JsonFiles/ParserTest.conf"));
		FunctionalParser parser = new FunctionalParser(petriNet);
		ResourcesTasksContainer container = parser.parseAndCreate(content);
		
		for(TasksContent taskContent : result.getTasks()){
			Task task = container.getTask(taskContent.getName());
			task.call();
		}

		for(ResourcesContent resContent : result.getResources()){
			Object  object = container.getResource(resContent.getImplementationName());
			if(object instanceof Class0){
				Assert.assertTrue(((Class0)object).isMethod0Executed());
				Assert.assertTrue(((Class0)object).isMethod1Executed());
			}
			else if(object instanceof Class1){
				Assert.assertTrue(((Class1)object).isMethod0Executed());
				Assert.assertTrue(((Class1)object).isMethod1Executed());
			}
		}
		
	}*/
	
	
	
	
	
	
	
	@Test
	public void simpleJson1() throws JsonParseException, JsonMappingException, IOException, URISyntaxException{
		ObjectMapper mapper = new ObjectMapper();
		SimpleJsonContainer result = mapper.readValue(new File(getJarpath()+"/jsonFiles/json simple example"), SimpleJsonContainer.class);
		Assert.assertEquals("value0", result.level1.get(0));
		Assert.assertEquals("value1", result.level1.get(1));
	}
	
	@Test
	public void simpleJsonInnerClass() throws JsonParseException, JsonMappingException, IOException, URISyntaxException{
		ObjectMapper mapper = new ObjectMapper();
		SimpleJsonContainerInnerClass result = mapper.readValue(new File(getJarpath()+"/jsonFiles/json simple example inner class"),
				SimpleJsonContainerInnerClass.class);
		Assert.assertEquals("implName0", result.getResources().get(0).getImplementationName());
		Assert.assertEquals("class0", result.getResources().get(0).getClassName());
		Assert.assertEquals("implName1", result.getResources().get(1).getImplementationName());
		Assert.assertEquals("class1", result.getResources().get(1).getClassName());
		
		//tasks
		// index 0 
		Assert.assertEquals("T1", result.getTasks().get(0).getInputTransitionName().get(0));
		Assert.assertEquals("T2", result.getTasks().get(0).getOutputTransitionName());
			// activity 0
			Assert.assertEquals("implName0", result.getTasks().get(0).getActivities().get(0).getObj());
			Assert.assertEquals("method0", result.getTasks().get(0).getActivities().get(0).getMethod());
			// activity 1
			Assert.assertEquals("implName0", result.getTasks().get(0).getActivities().get(1).getObj());
			Assert.assertEquals("method1", result.getTasks().get(0).getActivities().get(1).getMethod());
		
		// index 1
		Assert.assertEquals("T3", result.getTasks().get(1).getInputTransitionName().get(0));
		Assert.assertEquals("T4", result.getTasks().get(1).getOutputTransitionName());
			// activity 0
			Assert.assertEquals("implName1", result.getTasks().get(1).getActivities().get(0).getObj());
			Assert.assertEquals("method0", result.getTasks().get(1).getActivities().get(0).getMethod());
			// activity 1
			Assert.assertEquals("implName1", result.getTasks().get(1).getActivities().get(1).getObj());
			Assert.assertEquals("method1", result.getTasks().get(1).getActivities().get(1).getMethod());
	}	

	private String getJarpath() throws URISyntaxException {
		final String uri;
		uri = ParserTest.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		return uri;
	}
	
}


