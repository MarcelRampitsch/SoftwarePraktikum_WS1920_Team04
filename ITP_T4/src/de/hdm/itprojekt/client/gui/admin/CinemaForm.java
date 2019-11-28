package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CinemaForm extends VerticalPanel {

	Label cinemaGroup = new Label("CinemaGroup");
	Label cinema = new Label("Cinema");
	
	ListBox cinemaGroupBox = new ListBox();
	ListBox cinemaBox = new ListBox();
	
	Button editCinemaGroup = new Button("Edit");
	Button newCinemaGroup = new Button("New");
	Button deleteCinemaGroup = new Button("Delete");
	Button editCinema = new Button("Edit");
	Button newCinema = new Button("New");
	Button deleteCinema = new Button("Del");
	Button addCinemaGroup = new Button("+");
	Button addCinema = new Button("+");
	
	HorizontalPanel cinemaGroupContent1 = new HorizontalPanel();
	HorizontalPanel cinemaGroupContent2 = new HorizontalPanel();
	HorizontalPanel cinemaContent1 = new HorizontalPanel();
	HorizontalPanel cinemaContent2 = new HorizontalPanel();
	
	public CinemaForm() {
		
		
	}
	
}
