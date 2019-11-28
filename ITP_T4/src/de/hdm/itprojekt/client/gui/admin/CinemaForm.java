package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author DominikThumm, VanDuyHo, SerhatUlus
 * 
 */

public class CinemaForm extends VerticalPanel {

	Label cinemaGroup = new Label("CinemaGroup");
	Label cinema = new Label("Cinema");
	
	TextBox cinemaGroupBox = new TextBox();
	TextBox cinemaBox = new TextBox();
	
	Button editCinemaGroup = new Button("Edit");
	Button newCinemaGroup = new Button("New");
	Button deleteCinemaGroup = new Button("Delete");
	Button editCinema = new Button("Edit");
	Button newCinema = new Button("New");
	Button deleteCinema = new Button("Delete");
	Button addCinemaGroup = new Button("+");
	Button addCinema = new Button("+");
	
	HorizontalPanel cinemaGroupContent1 = new HorizontalPanel();
	HorizontalPanel cinemaGroupContent2 = new HorizontalPanel();
	HorizontalPanel cinemaContent1 = new HorizontalPanel();
	HorizontalPanel cinemaContent2 = new HorizontalPanel();
	
	
	public CinemaForm() {
		
		
	}
	
	
	public void onLoad() {
		
		super.onLoad();
		
		this.add(cinemaGroup);
		
		cinemaGroupContent1.add(cinemaGroupBox);
		cinemaGroupContent1.add(addCinemaGroup);
		cinemaGroupContent1.add(addCinema);
		cinemaGroupContent2.add(editCinemaGroup);
		cinemaGroupContent2.add(newCinemaGroup);
		cinemaGroupContent2.add(deleteCinemaGroup);
		
		this.add(cinemaGroupContent1);
		this.add(cinemaGroupContent2);
		
		this.add(cinema);
		
		cinemaContent1.add(cinemaBox);
		cinemaContent1.add(addCinema);
		cinemaContent2.add(editCinema);
		cinemaContent2.add(newCinema);
		cinemaContent2.add(deleteCinema);
		

		this.add(cinemaContent1);
		this.add(cinemaContent2);
		
	}
	
	
}
