package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * 
 * @author Dominik Thumm, SerhatUlus
 *
 */

public class PresentationForm extends VerticalPanel{
	
	
	 	Label pcinemaLabel = new Label("Cinema");
	    Label pmovieLabel = new Label("Movie");
	    Label ptimeslotLabel = new Label("Timeslot");

	    ListBox cinemaDrop = new ListBox();
	    ListBox movieDrop = new ListBox();
	    ListBox timeslotDrop = new ListBox();
	    
	    Label dateLabel = new Label("Date");
	    
	    DatePicker datePicker = new DatePicker();
	    
	    Button search = new Button("Search");

	    TextBox presentationbox = new TextBox();
	    Label presentationLabel = new Label("Presentation");
	    
	    Button presentationEdit = new Button("Edit");
	    Button presentationNew = new Button("New");
	    Button presentationDelete = new Button("Delete");
	    
	    Button presentationAdd = new Button("add");
	    
	    
	    
	    HorizontalPanel presentationadder = new HorizontalPanel();
	    VerticalPanel buttonbox  = new VerticalPanel();
	    
	    
	    
       public void Presentation() {
	    	
	   }
	    
	    
	    public void onLoad() {
	    	super.onLoad();
	    	
	    	this.add(pcinemaLabel);
	    	this.add(cinemaDrop);
	    	 cinemaDrop.addItem("UFA- Palast");
			 cinemaDrop.addItem("Traumpalast");
			 cinemaDrop.addItem("Central und union");
	    	
	    	
	    	this.add(pmovieLabel);
	    	this.add(movieDrop);
	    	 movieDrop.addItem("Spiderman");
			 movieDrop.addItem("Der böse Film");
			 movieDrop.addItem("Spiderman 3");
			    
	    	
	    	this.add(ptimeslotLabel);
	    	this.add(timeslotDrop);
	    	timeslotDrop.addItem("18:00");
		    timeslotDrop.addItem("18:30");
		    timeslotDrop.addItem("19:00");
		    timeslotDrop.addItem("19:30");
		    timeslotDrop.addItem("20:00");
		    timeslotDrop.addItem("20:30");
		    timeslotDrop.addItem("21:00");
		    timeslotDrop.addItem("21:30");
		    timeslotDrop.addItem("22:00");
		    timeslotDrop.addItem("22:30");
		    timeslotDrop.addItem("23:00");
		    timeslotDrop.addItem("23:30");
		    timeslotDrop.addItem("00:00");
		    
		    this.add(datePicker);
		    
		    this.add(presentationLabel);

		    presentationadder.add(presentationbox);
		    presentationadder.add(presentationAdd);
		    
		    this.add(presentationadder);
		    
		    this.add(search);
		    
		    
		   
		    
		    buttonbox.add(presentationEdit);
		    buttonbox.add(presentationNew);
		    buttonbox.add(presentationDelete);
		    
	    
	    }
	    

}


