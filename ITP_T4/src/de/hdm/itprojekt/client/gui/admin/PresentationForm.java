package de.hdm.itprojekt.client.gui.admin;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	    
	    Button presentationAdd = new Button("Edit");
	    ListBox presentationbox = new ListBox();
	    Label presentationLabel = new Label("Presentation");
	    Button presentationNew = new Button("New");
	    Button presentationDelete = new Button("Delete");
	    
	    Button search = new Button("Search");
	    
		private Vector<Cinema> cine = null;
		private Vector<Movie> movie = null;
		private Vector<Timeslot> time = null;
		private Vector<Presentation> pres = null;
		private Presentation presentation = null;

	    
	    HorizontalPanel presentationadder = new HorizontalPanel();
	    HorizontalPanel buttonbox  = new HorizontalPanel();
		private User user = null;
	    
	    
	    
       public PresentationForm(User user) {
			this.user  = user;
		}


	public void Presentation() {
	    	
	   }
	    
	    
	    public void onLoad() {
	    	
	    	super.onLoad();
	    	
			//CSS Stylename Vergabe
	    	
	    	presentationAdd.addStyleName("presentationEditStyle");
	    	presentationNew.addStyleName("presentationNewStyle");
	    	presentationDelete.addStyleName("presentationDeleteStyle");
	    	search.addStyleName("searchStyle");

	    	
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
	    	
	    	adminAdministration.getAllTimeslotByUserID(this.user, new AsyncCallback<Vector<Timeslot>>() {
	    		
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("was ist falsch geloffen");
				}

				public void onSuccess(Vector<Timeslot> result) {
					
					for (int i = 0; i < result.size(); i++ ) {
						timeslotDrop.addItem(result.elementAt(i).getTime());
						time = result;
					}
				}
			});
	    	
	    	
		    this.add(dateLabel);
		    this.add(datePicker);
		    presentationadder.add(search );
		    search.addClickHandler(new searchClickHandler());
		    this.add(presentationadder);
		    this.add(presentationLabel);

		    this.add(presentationbox);
		    adminAdministration.getAllPresentationsByUser(this.user, new AsyncCallback<Vector<Presentation>>() {
	    		
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("was ist falsch geloffen");
				}

				public void onSuccess(Vector<Presentation> result) {
					
					for (int i = 0; i < result.size(); i++ ) {
						presentationbox.addItem(result.elementAt(i).getName());
						pres = result;
					}
				}
			});
		    
		    
//		    presentationbox.addItem("erste");
//		    presentationbox.addItem("zweite");
//		    presentationbox.addItem("dritte");
		    
		    buttonbox.add(presentationAdd);
		    presentationAdd.addClickHandler(new editPresentationClickHandler());
		    buttonbox.add(presentationNew);
		    presentationNew.addClickHandler(new newPresentationClickHandler());
		    buttonbox.add(presentationDelete);
		    presentationDelete.addClickHandler(new deletePresentationClickHandler());
		    this.add(buttonbox);
	    
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
			Timeslot t = time.elementAt(timeslotDrop.getSelectedIndex());
			Date d = datePicker.getValue();
			Presentation p = new Presentation();
			}
		   
	   }
	    
	   public class editPresentationClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				presentation = pres.elementAt(presentationbox.getSelectedIndex());
				PresentationAddDialogBox presentationadd = new PresentationAddDialogBox(presentation, user);
				presentationadd.openPresentation();
			}
	   }
	   
	   public class newPresentationClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				PresentationNewDialogBox presentationnew = new PresentationNewDialogBox(user);
				presentationnew.openPresentation();
			}
	   }
	   
	   public class deletePresentationClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				presentation = pres.elementAt(presentationbox.getSelectedIndex());
				PresentationDeleteDialogBox presentationdelete = new PresentationDeleteDialogBox(presentation, user);
				presentationdelete.openPresentation();
			}
	   }
	   
	   private  class PresentationCallback implements AsyncCallback<Presentation>{

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


