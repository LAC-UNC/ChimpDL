package com.lac.annotations;

import java.util.Set;

import org.reflections.Reflections;

public class ResourcesFinder {

	static Set<Class<?>> resources ;
	
	public synchronized static Set<Class<?>> getResources(){
		if(resources != null){
			return resources;
		}
		
		 Reflections reflections = new Reflections();
		 
		 resources = reflections.getTypesAnnotatedWith(Resource.class);
		 
		 for(Class<?> resource : resources){
			 System.out.println(resource.getName());
		 }
		 
		 return resources;
	}
	
}
