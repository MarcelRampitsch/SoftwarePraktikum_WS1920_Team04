package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * 
 * @author DominikThum, SerhatUlus
 * diese Klasse ist die Basisklasse für die Klassen CinemaForm, TimeslotForm, MovieForm, PresentationForm
 *
 */

public class VerwaltungsForm extends VerticalPanel {
	
	
//	Button sp1 = new Button("Spielplan verwalten");
	
//	HorizontalPanel test = new HorizontalPanel();
	
	
	// Cinema/ CinemaGroup, Movie, Timeslot und Präsentation werden einem StackPanel hinzugefügt
    StackPanel panel = new StackPanel();
 
//    //Cinema/ CinemaGroup
//    TextBox cinemaGroupBox = new TextBox();
//    TextBox cinemaBox = new TextBox();
//    
//    Label cinemaGroupLabel = new Label("CinemaGroup");
//    Label cinemaLabel = new Label("Cinema");
//    
//    Button cinemaGroupEdit = new Button("Edit");
//    Button cinemaGroupNew = new Button("New");
//    Button cinemaGroupDelete = new Button("Delete");
//    
//    Button cinemaEdit = new Button("Edit");
//    Button cinemaNew = new Button("New");
//    Button cinemaDelete = new Button("Delete");
//    
//    Button cinemaGroupAdd = new Button("add");
//    Button cinemaAdd = new Button("add");
//    
//    
//    //Movie
//    TextBox moviebox = new TextBox();
//    Label movieLabel = new Label("Movie");
//    
//    Button movieEdit = new Button("Edit");
//    Button movieNew = new Button("New");
//    Button movieDelete = new Button("Delete");
//    
//    Button movieAdd = new Button("add");
//    
//    //Timeslot
//    TextBox timeslotbox = new TextBox();
//    Label timeslotLabel = new Label("Movie");
//    
//    Button timeslotEdit = new Button("Edit");
//    Button timeslotNew = new Button("New");
//    Button timeslotDelete = new Button("Delete");
//    
//    Button timeslotAdd = new Button("add");
//    
//    //Presentation
//    Label pcinemaLabel = new Label("Cinema");
//    Label pmovieLabel = new Label("Movie");
//    Label ptimeslotLabel = new Label("Timeslot");
//
//    ListBox cinemaDrop = new ListBox();
//    ListBox movieDrop = new ListBox();
//    ListBox timeslotDrop = new ListBox();
//    
//    Label dateLabel = new Label("Date");
//    
//    DatePicker datePicker = new DatePicker();
//    
//    Button search = new Button("Search");
//
//    TextBox presentationbox = new TextBox();
//    Label presentationLabel = new Label("Movie");
//    
//    Button presentationEdit = new Button("Edit");
//    Button presentationNew = new Button("New");
//    Button presentationDelete = new Button("Delete");
//    
//    Button presentationAdd = new Button("add");



	
	VerwaltungsForm(){
		
	}
	
	//VerwaltungForm(Administrator a){
	//	this.adminUser=a;
//	}
	
	public void onLoad() {
		super.onLoad();
		
		
		// Fixierung der Größe (wird später mit CSS umgesetzt)
		 panel.setPixelSize(290,600);
		    

//		sp1.addStyleName("verwaltung");
		
//		test.add(sp1);
		
//		this.add(test);
		
//		sp1.addClickHandler(new OpenVUpClickHandler());
		 
		 // Cinema Group
//		    HorizontalPanel cinemaGroupPanel1 = new HorizontalPanel();
		    
//		    cinemaGroupPanel1.add(cinemaGroupBox);
//		    cinemaGroupPanel1.add(cinemaGroupAdd);
		    
		 	CinemaForm cinemaPanle = new CinemaForm();
		    
//		    
//		    HorizontalPanel cinemaGroupPanel2 = new HorizontalPanel();
//		    cinemaGroupPanel2.add(cinemaGroupEdit);
//		    cinemaGroupPanel2.add(cinemaGroupNew);
//		    cinemaGroupPanel2.add(cinemaGroupDelete);
//		    
//		    
//		    //Cinema
//		    HorizontalPanel cinemaPanel1 = new HorizontalPanel();
//		    cinemaPanel1.add(cinemaBox);
//		    cinemaPanel1.add(cinemaAdd);
//		    
//		    HorizontalPanel cinemaPanel2 = new HorizontalPanel();
//		    cinemaPanel2.add(cinemaEdit);
//		    cinemaPanel2.add(cinemaNew);
//		    cinemaPanel2.add(cinemaDelete);
//		    
//		    
//		    // Hinzufügen im Haupt Vertical Panel
//		    VerticalPanel CinemaGes = new VerticalPanel();
//		    CinemaGes.add(cinemaGroupLabel);
//		    CinemaGes.add(cinemaGroupPanel1);
//		    CinemaGes.add(cinemaGroupPanel2);
//		    
//		    CinemaGes.add(cinemaLabel);
//		    CinemaGes.add(cinemaPanel1);
//		    CinemaGes.add(cinemaPanel2);
		 	
		 	

		    
		    panel.add(cinemaPanle, "Cinema/ CinemaGroup");
		    //Cinema
		    
		    
		    
		    
		    
		    
		    MovieForm moviePanel = new MovieForm();
		    // Movie
//		    HorizontalPanel movieboxaddHorizont = new HorizontalPanel();
//		    movieboxaddHorizont.add(moviebox);
//		    movieboxaddHorizont.add(movieAdd);
//		    
//		    HorizontalPanel movieCrud = new HorizontalPanel();
//		    movieCrud.add(movieEdit);
//		    movieCrud.add(movieNew);
//		    movieCrud.add(movieDelete);
//
//		   
//		    // Hinzufügen im Haupt Vertical Panel
//		    VerticalPanel movieGes = new VerticalPanel();
//		    movieGes.add(movieLabel);
//		    movieGes.add(movieboxaddHorizont);
//		    movieGes.add(movieCrud);
		    
		    
//		    
//		    
		    panel.add(moviePanel, "Movie");
//		    //Movie
		    
		    
		    
		    
		    
//		    
//		    //Timeslot
		    
		    TimeslotForm timeslotpanel = new TimeslotForm();
		    
		    
//		    HorizontalPanel timeslotboxaddHorizont = new HorizontalPanel();
//		    timeslotboxaddHorizont.add(timeslotbox);
//		    timeslotboxaddHorizont.add(timeslotAdd);
//		    
//		    HorizontalPanel timeslotCrud = new HorizontalPanel();
//		    timeslotCrud.add(timeslotEdit);
//		    timeslotCrud.add(timeslotNew);
//		    timeslotCrud.add(timeslotDelete);
//		    
//		    VerticalPanel timeslotGes = new VerticalPanel();
//		    timeslotGes.add(timeslotLabel);
//		    timeslotGes.add(timeslotboxaddHorizont);
//		    timeslotGes.add(timeslotCrud);

		    panel.add(timeslotpanel, "Timeslot");
		    
		    //Timeslot
		    
		    
		  //Presentation
		    PresentationForm presentationpanel =new PresentationForm();
		    
		    
		    // Beispielhafte Datenhinzufügung (Später aus der Datenbank)
//		    cinemaDrop.addItem("UFA- Palast");
//		    cinemaDrop.addItem("Traumpalast");
//		    cinemaDrop.addItem("Central und union");
//		    
//		    movieDrop.addItem("Spiderman");
//		    movieDrop.addItem("Der böse Film");
//		    movieDrop.addItem("Spiderman 3");
//		    
//		    timeslotDrop.addItem("18:00");
//		    timeslotDrop.addItem("18:30");
//		    timeslotDrop.addItem("19:00");
//		    timeslotDrop.addItem("19:30");
//		    timeslotDrop.addItem("20:00");
//		    timeslotDrop.addItem("20:30");
//		    timeslotDrop.addItem("21:00");
//		    timeslotDrop.addItem("21:30");
//		    timeslotDrop.addItem("22:00");
//		    timeslotDrop.addItem("22:30");
//		    timeslotDrop.addItem("23:00");
//		    timeslotDrop.addItem("23:30");
//		    timeslotDrop.addItem("00:00");
//
//
//		    
//		    VerticalPanel presentationGes = new VerticalPanel();
//		    presentationGes.add(pcinemaLabel);
//		    presentationGes.add(cinemaDrop);
//		    presentationGes.add(pmovieLabel);
//		    presentationGes.add(movieDrop);
//		    presentationGes.add(ptimeslotLabel);
//		    presentationGes.add(timeslotDrop);
//		    presentationGes.add(dateLabel);
//		    presentationGes.add(datePicker);
//		    presentationGes.add(search);
//		    
//		    
//		    
//		    HorizontalPanel presentationboxaddHorizont = new HorizontalPanel();
//		    presentationboxaddHorizont.add(presentationbox);
//		    presentationboxaddHorizont.add(presentationAdd);
//		    
//		    HorizontalPanel presentationCrud = new HorizontalPanel();
//		    presentationCrud.add(presentationEdit);
//		    presentationCrud.add(presentationNew);
//		    presentationCrud.add(presentationDelete);
//		    
//
//		    VerticalPanel PresentationGes2 = new VerticalPanel();
//		    PresentationGes2.add(presentationGes);
//		    PresentationGes2.add(presentationLabel);
//		    PresentationGes2.add(presentationboxaddHorizont);
//		    PresentationGes2.add(presentationCrud);

		    
		    panel.add(presentationpanel, "Presentation");
		    
		    this.add(panel);


		    
		
	}
	
	
	
	
	
	
	
	 class OpenVUpClickHandler implements ClickHandler {
	    	
		    
	    	
			public void onClick(ClickEvent event) {
				
			//	greetingService.greetServer(schmeckt.getText(), new AsyncCallback<String>() {

				
				VerwaltungsOpenForm gr = new VerwaltungsOpenForm();
				gr.openSpielPlanForm();

	    }}
	
	
	
	

}
