package de.hdm.itprojekt.client.gui.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;

public class PresentationEditDialogBox extends DialogBox {
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
    
	VerticalPanel content = new VerticalPanel();
    HorizontalPanel buttoncontent  = new HorizontalPanel();
    
    User user = null;
    Presentation presentation = null;
    Vector <Cinema> cine = null;
    Vector <Movie> movie = null;
    Vector <Timeslot> timeslot = null;
    
    Button yes = new Button("save");
    Button no = new Button("X");
    
    ListBox cinemaBox = new ListBox();
    ListBox movieBox = new ListBox();
    ListBox timeslotBox = new ListBox();
    DatePicker datePicker = new DatePicker();
    
    Label name = new Label("Name");
    TextBox nameBox = new TextBox();
    Label pcinemaLabel = new Label("Cinema");
    Label pmovieLabel = new Label("Movie");
    Label ptimeslotLabel = new Label("Timeslot");
    Label dateLabel = new Label("Date");
    
    public PresentationEditDialogBox (Presentation presentation, User user) {
    	this.user = user;
    	this.presentation = presentation;
    }
    
    public void onLoad() {
    	super.onLoad();
    	
    	content.add(no);
    	no.addClickHandler(new close());
    	content.add(name);
    	content.add(nameBox);
    	content.add(pcinemaLabel);
    	content.add(cinemaBox);
    	content.add(pmovieLabel);
    	content.add(movieBox);
    	content.add(dateLabel);
    	content.add(datePicker);
    	content.add(ptimeslotLabel);
    	content.add(timeslotBox);
    	buttoncontent.add(yes);
    	yes.addClickHandler(new save());
    	content.add(buttoncontent);
    	this.add(content);
    	
    	adminAdministration.findAllCinemaByUser(this.user, new AsyncCallback<Vector<Cinema>>() {
    		
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
			}

			@Override 
			public void onSuccess(Vector<Cinema> result) {
				
				for (int i = 0; i < result.size(); i++ ) {
					cinemaBox.addItem(result.elementAt(i).getName());
					cine = result;
				}
			}
		});
    	
    	adminAdministration.getAllMovieByUserID(this.user, new AsyncCallback<Vector<Movie>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
			}

			@Override 
			public void onSuccess(Vector<Movie> result) {
				
				for (int i = 0; i < result.size(); i++ ) {
					movieBox.addItem(result.elementAt(i).getName());
					movie = result;
				}
			}
		});
    	
    	adminAdministration.getAllTimeslotByUserID(this.user, new AsyncCallback<Vector<Timeslot>>() {
    		
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
			}

			public void onSuccess(Vector<Timeslot> result) {
				
				for (int i = 0; i < result.size(); i++ ) {
					timeslotBox.addItem(result.elementAt(i).getTime());
					timeslot = result;
				}
			}
		});
    }

	/*
	 * Methode die das Anzeigen der DialogBox realisiert
	 */
	public void openPresentation() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	/*
	 * Methode die das schließen des Fensters realisiert
	 */
	public void closePresentation() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
    
    private class close implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			closePresentation();
		}
    }
    
    private class save implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
		closePresentation();
		Cinema c = cine.elementAt(cinemaBox.getSelectedIndex());
		Movie m = movie.elementAt(movieBox.getSelectedIndex());
		Timeslot t = timeslot.elementAt(timeslotBox.getSelectedIndex());
		Window.alert(c.getName()+m.getName()+t.getTime());
		}
    }
}