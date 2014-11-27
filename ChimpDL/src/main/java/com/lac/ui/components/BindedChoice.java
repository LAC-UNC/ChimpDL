package com.lac.ui.components;

import java.awt.Choice;
import java.util.ArrayList;
import java.util.List;

public class BindedChoice<k> extends Choice {
	
	
	List<k> choicesList;
	
	/**
	 * Allow to add a list of choices to this component from a List. 
	 * the displayed item will be calling the method toString() of each entry of the list. 
	 * If 2 elements has the same result of the toString element, this will be shown twice
	 * @param optionList
	 */
	public void bindList(List<k> optionList){
		removeAll();
		choicesList = new ArrayList<k>(optionList);
		for(k option : optionList){
			this.add(option.toString());
		}
	}
	
	
	public void removeBindedOptions(){
		if(choicesList == null)
			return;
		choicesList = null;
		this.removeAll();
	}
	
	/**
	 * Will remove the 1st entry from the component and from the list binded to the component that are equals to the one
	 * passed by parameter.  
	 * @param entry
	 */
	public void removeOption(k entry){
		this.remove(entry.toString());
		for(int i = 0 ; i < choicesList.size() ; i++){
			if( choicesList.get(i).equals(entry) ){
				choicesList.remove(i);
				break;
			}
		}
	}
	
	/**
	 * Return the 1st object associated to the string of the option displayed. 
	 * @param option
	 * @return
	 */
	public k getObjectFromOptionString(String option){
		for(k listEntry : choicesList){
			if( listEntry.toString().equals(option) ){
				return listEntry; 
			}
		}
		return null;
	}
	

}
