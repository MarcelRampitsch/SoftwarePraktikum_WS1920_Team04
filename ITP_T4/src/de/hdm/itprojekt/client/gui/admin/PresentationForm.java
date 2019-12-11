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
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author Dominik Thumm, SerhatUlus
 *
 */

public class PresentationForm extends VerticalPanel{
	
	
	 	Label pcinemaLabel = new Label("Cinema");
	    Label pmovieLabel = new Label("Movie");
	    Label ptimeslotLabel = new Label("Timeslot");
//		EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();


	    ListBox cinemaDrop = new ListBox();
	    ListBox movieDrop = new ListBox();
	    ListBox timeslotDrop = new ListBox();
	    
	    Label dateLabel = new Label("Date");
	    
	    DatePicker datePicker = new DatePicker();
	    
	    Button search = new Button("Search");

	    ListBox presentationbox = new ListBox();
	    Label presentationLabel = new Label("Presentation");
	    
	    Button presentationEdit = new Button("Edit");
	    Button presentationNew = new Button("New");
	    Button presentationDelete = new Button("Delete");
	    
	    Button presentationAdd = new Button("add");
	    
	    
	    
	    HorizontalPanel presentationadder = new HorizontalPanel();
	    VerticalPanel buttonbox  = new VerticalPanel();
		private User user = null;
	    
	    
	    
       public PresentationForm(User user) {
			this.user  = user;
		}


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
		    presentationbox.addItem("erste");
		    presentationbox.addItem("zweite");
		    presentationbox.addItem("dritte");

		    
		    presentationadder.add(presentationAdd);
		    presentationAdd.addClickHandler(new addPresentationClickHandler());
		    
		    this.add(presentationadder);
		    
		    this.add(search);
		    
		    
		   
		    
		    buttonbox.add(presentationEdit);
		    buttonbox.add(presentationNew);
		    buttonbox.add(presentationDelete);
		    
	    
	    }

	    
	    /*
		 * Ab hier folgen die CLICKHANDLER und CALLBACKS dieser Klasse!
		 */
		
		/**ClickHandler für die Erstellung einer Presentation
		 * 
		 */
	    
	   public class addPresentationClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				PresentationAddDialogBox presentationadd = new PresentationAddDialogBox();
				presentationadd.openPresentation();
			}
		   
	   }
	   
	   private  class addPresentationCallback implements AsyncCallback<Presentation>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Presentation result) {
			// TODO Auto-generated method stub
			
		}
	   
}
}


