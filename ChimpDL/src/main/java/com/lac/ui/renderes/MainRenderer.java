package com.lac.ui.renderes;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.util.ArrayList;

import com.lac.ui.components.BindedChoice;

public class MainRenderer extends Frame{

	public static void main(String[] args) {
		renderMainWindow();
	}

	public void init(){
		
		ArrayList<Integer> intList = new ArrayList<Integer>();
		intList.add(1);
		intList.add(99);
		intList.add(123);
		intList.add(1990);
		
		
		Panel p;
		// 1st panel
		p = new Panel();
		p.add(new TextArea());
		add("Center", p);
		
		//2nd panel
		p = new Panel();
		p.add(new Button("One"));
		p.add(new Button("Two"));
		Choice c = new Choice();
		c.addItem("one");
		c.addItem("two");
		c.addItem("three");
		p.add(c);
		
		BindedChoice<Integer> bindedChoice = new BindedChoice<Integer>();
		bindedChoice.bindList(intList);
		
		p.add(bindedChoice);
		add("South", p);
	}
	
	
	public static void renderMainWindow(){
		MainRenderer renderer  = new MainRenderer();
		renderer.init();
		renderer.pack();
		renderer.setVisible(true);
		
		
		
	}
}
