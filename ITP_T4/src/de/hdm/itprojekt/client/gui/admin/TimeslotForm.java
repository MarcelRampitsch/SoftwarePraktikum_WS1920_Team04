package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Timeslot;


/**
 * 
 * @author Dominik Thumm, SerhatUlus
 *
 */
public class TimeslotForm extends VerticalPanel {
	
	
	ListBox timeslotbox = new ListBox();
    Label timeslotLabel = new Label("Timeslot");
//	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();

    
    Button timeslotEdit = new Button("Edit");
    Button timeslotNew = new Button("New");
    Button timeslotDelete = new Button("Delete");
    
    
    HorizontalPanel timeslotaddbox = new HorizontalPanel();
    HorizontalPanel buttonbox = new HorizontalPanel();

    
    public TimeslotForm() {
    	
    }

    
    public void onLoad() {
    	
    	super.onLoad();
    	
    	this.add(timeslotLabel);
    	
    	timeslotaddbox.add(timeslotbox);
    	timeslotbox.addItem("18:00uhr");
    	timeslotbox.addItem("18:30uhr");
    	timeslotbox.addItem("19:00uhr");
    	timeslotbox.addItem("19:30uhr");
    	timeslotbox.addItem("20:00uhr");
    	timeslotbox.addItem("20:30uhr");
    	timeslotbox.addItem("21:00uhr");
    	timeslotbox.addItem("21:30uhr");
    	timeslotbox.addItem("22:00uhr");
    	timeslotbox.addItem("22:30uhr");
    	timeslotbox.addItem("23:00uhr");
    	timeslotbox.addItem("23:30uhr");
    	timeslotbox.addItem("00:00uhr");





    	
    	
    	buttonbox.add(timeslotEdit);
    	timeslotEdit.addClickHandler(new editTimeslotClickHandler());
    	buttonbox.add(timeslotNew);
    	timeslotNew.addClickHandler(new addTimeslotClickHandler());
    	buttonbox.add(timeslotDelete);
    	timeslotDelete.addClickHandler(new deleteTimeslotClickHandler());
    	
    	this.add(timeslotaddbox);
    	this.add(buttonbox);
     	
    }
    
    /*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */
	
	/**
	 * ClickHandler für Erstellung eines Timeslot
	 */

    public class addTimeslotClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			TimeSlotDialogBox timeslotbox = new TimeSlotDialogBox();
			timeslotbox.opentimeslot();
			
		}
		
	}
	
	private class addTimeslotCallback implements AsyncCallback <Timeslot>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Timeslot result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * ClickHandler zum editieren eines Timeslot
	 */
	public class editTimeslotClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			EditTimeSlot timeslotedit  = new EditTimeSlot();
			timeslotedit.openEditTimeSlot();
			
		}
	}
	
	private class editTimeslotCallback implements AsyncCallback <Timeslot>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Timeslot result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * ClickHandler zum löschen eines Timeslot
	 */
	public class deleteTimeslotClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			DeleteTimeSlotDialogBox deletetimeslot = new DeleteTimeSlotDialogBox();
			deletetimeslot.open();
			
		}
	}
	
	private class deleteTimeslotCallback implements AsyncCallback <Timeslot>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Timeslot result) {
			// TODO Auto-generated method stub
			
		}
		
	}

}


