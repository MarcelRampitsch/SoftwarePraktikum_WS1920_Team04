package de.hdm.itprojekt.client.gui.admin;

import java.util.Date;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * 
 * @author Dominik Thumm, SerhatUlus, VanDuyHo
 *
 */

public class PresentationForm extends VerticalPanel{
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	
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
	    
		private Vector<Cinema> cine = null;
		
		private Vector<Movie> movie =null;

	    
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
	    	
	    	
	    	
	    	// Create a basic date picker
	        DatePicker datePicker = new DatePicker();
	        final Label text = new Label();

	        // Set the value in the text box when the user selects a date
	        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
	           @Override
	           public void onValueChange(ValueChangeEvent<Date> event) {
	              Date date = event.getValue();
	              String dateString = 
	              DateTimeFormat.getFormat("MM/dd/yyyy").format(date);
	              text.setText(dateString);				
	           }
	        });
	        
	        // Set the default value
	        datePicker.setValue(new Date(), true);

	        // Create a DateBox
	        DateTimeFormat dateFormat = DateTimeFormat.getFormat("MM/dd/yyyy");
	        DateBox dateBox = new DateBox();
	        dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));

	        Label selectLabel = new Label("Permanent DatePicker:");
	        Label selectLabel2 = new Label("DateBox with popup DatePicker:");
	        
	        // Add widgets to the root panel.
	        VerticalPanel vPanel = new VerticalPanel();
	        vPanel.setSpacing(10);
	        vPanel.add(selectLabel);
	        vPanel.add(text);
	        vPanel.add(datePicker);
	        vPanel.add(selectLabel2);
	        vPanel.add(dateBox);

	        RootPanel.get("gwtContainer").add(vPanel);
	    	
	    	
	        
	    	this.add(pcinemaLabel);
	    	this.add(cinemaDrop);
	    	
	    	adminAdministration.findAllCinemaByUser(this.user, new AsyncCallback<Vector<Cinema>>() {
	    		
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("was ist falsch geloffen");
				}

				@Override 
				public void onSuccess(Vector<Cinema> result) {
					
					for (int i = 0; i < result.size(); i++ ) {
						cinemaDrop.addItem(result.elementAt(i).getName());
						cine = result;
					}
				}
			});
	    	
	/*    	 cinemaDrop.addItem("UFA- Palast");
			 cinemaDrop.addItem("Traumpalast");
			 cinemaDrop.addItem("Central und union");   */
	    	
	    	
	    	this.add(pmovieLabel);
	    	this.add(movieDrop);
	    	
            adminAdministration.getAllMovieByUserID(this.user, new AsyncCallback<Vector<Movie>>() {
	    		
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("was ist falsch geloffen");
				}

				@Override 
				public void onSuccess(Vector<Movie> result) {
					
					for (int i = 0; i < result.size(); i++ ) {
						movieDrop.addItem(result.elementAt(i).getName());
						movie = result;
					}
				}
			});
	    	
	    
	/*    	 movieDrop.addItem("Spiderman");
			 movieDrop.addItem("Der böse Film");
			 movieDrop.addItem("Spiderman 3");       */
			    
	    	
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
		    this.add(dateLabel);
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
		    search.addClickHandler(new searchClickHandler());
		    
		    
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
	    
	    public class searchClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
			Cinema c = cine.elementAt(cinemaDrop.getSelectedIndex());
			Movie m = movie.elementAt(movieDrop.getSelectedIndex());
			Timeslot t = null;
			Date date = datePicker.getHighlightedDate();
			Presentation p = new Presentation();
			
			}
		   
	   }
	    
	   public class addPresentationClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				PresentationAddDialogBox presentationadd = new PresentationAddDialogBox(user);
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


