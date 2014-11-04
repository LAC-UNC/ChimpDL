package com.lac.parsers;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.lac.activities.DLContents.DLContent;
import com.lac.interpreter.Interpreter;
import com.lac.petrinet.exceptions.PetriNetException;
import com.lac.ui.mainscreens.ErrorDialog;
import com.lac.ui.mainscreens.MainFrame;


public class SessionLoader {

	public void startLoadedSession(String path, MainFrame frame ) throws PetriNetException, JsonGenerationException, JsonMappingException, IOException{
		Interpreter interpreter = frame.getInterpreter();
		try {
			interpreter.startListening(path, false);
		} catch (PetriNetException e) {
			e.printStackTrace();
			new ErrorDialog(e.getMessage());
			return;
		}
		
//		File confFile = new File(path);
//		if(! confFile.exists()){
//			throw new PetriNetException("File " + path + "doesn't exists." );
//		}
//		ObjectMapper mapper = new ObjectMapper();
//		DLContent content = new DLContent();
//		mapper.writeValue(new File(path + "Configuration.conf" ), content);
		
	}
	
	private void createFields(MainFrame frame, DLContent content){
//		TaskPanel taskP = new TaskPanel();
//		frame.getTaskAssocPanel();
//		List<ResourceInstances> resourcesinstances = new ArrayList<ResourceInstances>();
//		
//		for(ResourcesContent  resource : content.getResources()){
//			resourcesinstances.add(new ResourceInstances(resource.getImplementationName(), resource.getClassName()));
//		}
//		
//		
//		for(TasksContent taskCont : content.getTasks()){
//			for(ActivityContent actCont : taskCont.getActivities()){
//				ActivityPanel actPanel = new ActivityPanel();
//				actPanel.addResources(new ResourceInstances(name, clazz));
//				content.getResources()
//			}
//		}
	}

}
