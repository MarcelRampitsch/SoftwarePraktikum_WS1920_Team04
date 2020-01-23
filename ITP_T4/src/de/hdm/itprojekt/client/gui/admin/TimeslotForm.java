	package de.hdm.itprojekt.client.gui.admin;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;


/**
 * 
 * @author Dominik Thumm, SerhatUlus
 *
 */
public class TimeslotForm extends VerticalPanel {
	
	
	ListBox timeslotbox = new ListBox();
   // Label timeslotLabel = new Label("Timeslot");
//	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();

    
    
    Button timeslotEdit = new Button("Edit");
    Button timeslotNew = new Button("New");
    Button timeslotDelete = new Button("Delete");
    
    private Timeslot selectedtimeslot = null;
    private Vector<Timeslot> tim = null;
    
    
	private Vector<Timeslot> time = null;

    
    HorizontalPanel timeslotaddbox = new HorizontalPanel();
    HorizontalPanel buttonbox = new HorizontalPanel();
	private User user = null;
	
	private Timeslot delete = null;

	
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();


    
    public TimeslotForm(User user) {
    	this.user  = user;
    }

    
    public void onLoad() {
    	
    	super.onLoad();
    	
		//CSS Stylename Vergabe
    	
    	timeslotEdit.addStyleName("timeslotEditStyle");
    	timeslotNew.addStyleName("timeslotNewStyle");
    	timeslotDelete.addStyleName("timeslotDeleteStyle");
    	
    	//this.add(timeslotLabel);
    	

    	adminAdministration.getAllTimeslotByUserID(this.user, new AsyncCallback<Vector<Timeslot>>() {
    		
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
			}

			public void onSuccess(Vector<Timeslot> result) {
				
				for (int i = 0; i < result.size(); i++ ) {
					timeslotbox.addItem(result.elementAt(i).getTime());
					time = result;
				}
			}
		});
		
    	
    	
    	

    	this.add(timeslotbox);
  
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
			TimeSlotAddDialogBox timeslotbox = new TimeSlotAddDialogBox(user);
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
			selectedtimeslot = time.elementAt(timeslotbox.getSelectedIndex());
			EditTimeSlot timeslotedit  = new EditTimeSlot(selectedtimeslot, user);
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
			delete  = time.elementAt(timeslotbox.getSelectedIndex());
			DeleteTimeSlotDialogBox deletetimeslot = new DeleteTimeSlotDialogBox(delete, user);
			deletetimeslot.openTimeSlot();
			
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


