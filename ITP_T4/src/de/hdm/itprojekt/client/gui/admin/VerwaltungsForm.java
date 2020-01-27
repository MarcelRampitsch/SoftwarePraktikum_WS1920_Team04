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

import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author DominikThum, SerhatUlus diese Klasse ist die Basisklasse für die
 *         Klassen CinemaForm, TimeslotForm, MovieForm, PresentationForm
 *
 */

public class VerwaltungsForm extends VerticalPanel {

	// Cinema/ CinemaGroup, Movie, Timeslot und Präsentation werden einem StackPanel
	// hinzugefügt
	StackPanel panel = new StackPanel();

	private User user = null;
	int a;

	public VerwaltungsForm(User u, int a) {
		this.user = u;
		this.a = a;
	}

	public void onLoad() {
		super.onLoad();

		panel.addStyleName("StackPanel");

		// Fixierung der Größe (wird später mit CSS umgesetzt)
		panel.setPixelSize(290, 600);

		CinemaGroupForm cinemagroupform = new CinemaGroupForm(user);

		panel.add(cinemagroupform, "CinemaGroup");

		CinemaForm cinemaPanle = new CinemaForm(user);

		panel.add(cinemaPanle, "Cinema");
		// Cinema

		MovieForm moviePanel = new MovieForm(user);
		// Movie

		    
		panel.add(moviePanel, "Movie");
		    //Movie

	    
		    //Timeslot

		TimeslotForm timeslotpanel = new TimeslotForm(user);

		panel.add(timeslotpanel, "Timeslot");

		// Timeslot

		// Presentation
		PresentationForm presentationpanel = new PresentationForm(user);

		panel.add(presentationpanel, "Presentation");
		panel.showStack(a);
		this.add(panel);

	}

	class OpenVUpClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {


			VerwaltungsOpenForm gr = new VerwaltungsOpenForm();
			gr.openSpielPlanForm();

		}
	}

}
