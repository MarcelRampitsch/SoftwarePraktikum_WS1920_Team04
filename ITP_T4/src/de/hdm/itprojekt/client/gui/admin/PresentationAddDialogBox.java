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
import com.google.gwt.user.client.ui.RootPanel;
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

public class PresentationAddDialogBox extends DialogBox {

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>adminAdministration</code>.
	 */
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	
	//Erstellung aller notwendigen Widgets sowie Attribute
	VerticalPanel content = new VerticalPanel();
	HorizontalPanel buttoncontent = new HorizontalPanel();

	int index = 0;
	User user = null;
	Presentation p = null;
	Vector<Cinema> cine = null;
	Vector<Movie> movie = null;
	Vector<Timeslot> timeslot = null;

	Button yes = new Button("save");
	Button no = new Button("X");

	TextBox name = new TextBox();
	ListBox cinemaBox = new ListBox();
	ListBox movieBox = new ListBox();
	ListBox timeslotBox = new ListBox();
	DatePicker datePicker = new DatePicker();

	Label nameLabel = new Label("Name");
	Label pcinemaLabel = new Label("Cinema");
	Label pmovieLabel = new Label("Movie");
	Label ptimeslotLabel = new Label("Timeslot");
	Label dateLabel = new Label("Date");

	public PresentationAddDialogBox(Presentation p, User user) {
		this.p = p;
		this.user = user;
	}

	public void onLoad() {
		super.onLoad();

		yes.addStyleName("editpressafe");
		no.addStyleName("editpresclose");
		buttoncontent.add(no);
		no.addClickHandler(new close());
		content.add(nameLabel);
		content.add(name);
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
		datePicker.setValue(p.getDate());
		name.setText(p.getName());
		adminAdministration.findAllCinemaByUser(this.user, new AsyncCallback<Vector<Cinema>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
			}

			@Override
			public void onSuccess(Vector<Cinema> result) {
				cine = result;
				for (int i = 0; i < result.size(); i++) {
					cinemaBox.addItem(result.elementAt(i).getName());
					if (p.getCinemaID() == result.elementAt(i).getId()) {
						index = i;
					}
				}
				cinemaBox.setSelectedIndex(index);
			}
		});

		adminAdministration.getAllMovieByUserID(this.user, new AsyncCallback<Vector<Movie>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
			}

			@Override
			public void onSuccess(Vector<Movie> result) {
				movie = result;
				for (int i = 0; i < result.size(); i++) {
					movieBox.addItem(result.elementAt(i).getName());
					if (p.getMovieID() == result.elementAt(i).getId()) {
						index = i;
					}
				}
				movieBox.setSelectedIndex(index);
			}
		});

		adminAdministration.getAllTimeslotByUserID(this.user, new AsyncCallback<Vector<Timeslot>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
			}

			public void onSuccess(Vector<Timeslot> result) {
				timeslot = result;
				for (int i = 0; i < result.size(); i++) {
					timeslotBox.addItem(result.elementAt(i).getTime());
					if (p.getTimeslotID() == result.elementAt(i).getId()) {
						index = i;
					}
				}
				timeslotBox.setSelectedIndex(index);
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

	private class close implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			closePresentation();
		}
	}

	private class save implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Date date = new java.sql.Date(datePicker.getValue().getTime());
			date.after(date);
			Presentation pres = new Presentation(name.getText(), cine.elementAt(cinemaBox.getSelectedIndex()).getId(),
					movie.elementAt(movieBox.getSelectedIndex()).getId(), user.getId(),
					timeslot.elementAt(timeslotBox.getSelectedIndex()).getId(), date, p.getId(), p.getCreationDate());

			adminAdministration.updatePresentation(pres, new AsyncCallback<Presentation>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(Presentation result) {
					RootPanel.get().clear();
					AdminForm adminform = new AdminForm(user, 4);
					RootPanel.get().add(adminform);
					closePresentation();
				}
			});

		}
	}
}