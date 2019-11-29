package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * 
 * @author Dominik Thumm, SerhatUlus
 *
 */
public class TimeslotForm extends VerticalPanel {
	
	
	
	
	TextBox timeslotbox = new TextBox();
    Label timeslotLabel = new Label("Timeslot");
    
    Button timeslotEdit = new Button("Edit");
    Button timeslotNew = new Button("New");
    Button timeslotDelete = new Button("Delete");
    
    Button timeslotAdd = new Button("+");
    
    HorizontalPanel timeslotaddbox = new HorizontalPanel();
    HorizontalPanel buttonbox = new HorizontalPanel();

    
    public TimeslotForm() {
    	
    }

    
    public void onLoad() {
    	
    	super.onLoad();
    	
    	this.add(timeslotLabel);
    	
    	timeslotaddbox.add(timeslotbox);
    	timeslotaddbox.add(timeslotAdd);
    	
    	buttonbox.add(timeslotEdit);
    	buttonbox.add(timeslotNew);
    	buttonbox.add(timeslotDelete);
    	
    	this.add(timeslotaddbox);
    	this.add(buttonbox);
     	
    	
    	
    }

}


