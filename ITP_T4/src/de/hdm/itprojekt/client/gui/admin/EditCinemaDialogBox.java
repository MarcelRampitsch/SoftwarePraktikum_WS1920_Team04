package de.hdm.itprojekt.client.gui.admin;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.User;

public class EditCinemaDialogBox extends DialogBox {
	
	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>adminAdministration</code>.
	 */
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	//Erstellung aller notwendigen Widgets sowie Attribute
	Label cinemaGroup = new Label("CinemaGroup");
	ListBox cinemaGroupBox = new ListBox();
	Vector<CinemaGroup> cinemag;
	Cinema cine = null;
	User user = null;
	Cinema c = null;
	VerticalPanel content = new VerticalPanel();
	Button close = new Button("X");
	Label cinema = new Label("Cinema");
	TextBox box = new TextBox();
	TextBox locationBox = new TextBox();
	Label location = new Label("Location");
	Button safe = new Button("save");

	public EditCinemaDialogBox(Cinema cine, User user) {
		this.cine = cine;
		this.user = user;
	}

	public void onLoad() {
		super.onLoad();

		// CSS Stylename Vergabe

		safe.addStyleName("CinemaSafeStyle");
		close.addStyleName("cinemaCloseStyle");

		content.add(close);
		content.add(cinemaGroup);
		content.add(cinemaGroupBox);
		content.add(cinema);
		content.add(box);
		content.add(location);
		content.add(locationBox);
		close.addClickHandler(new closeCinemaEditForm());
		content.add(safe);
		safe.addClickHandler(new safeCinemaEditForm());
		box.setText(cine.getName());
		locationBox.setText(cine.getLocation());
		adminAdministration.getAllCinemaGroupByUserID(this.user, new AsyncCallback<Vector<CinemaGroup>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Laden der CinemaGroup!");
			}

			@Override
			public void onSuccess(Vector<CinemaGroup> result) {
				int d = 0;
				cinemaGroupBox.addItem("Keine Kinogruppe");
				cinemag = result;
				for (int i = 0; i < result.size(); i++) {
					cinemaGroupBox.addItem(result.elementAt(i).getName());
					if (cine.getCinemaGroupID() == result.elementAt(i).getId()) {
						d = i + 1;
					}
				}
				cinemaGroupBox.setSelectedIndex(d);
			}
		});
		this.add(content);
	}

	/*
	 * Methoden zum Öffnen und Schließen der DeleteCinemaDialogBox.
	 */

	public void openCinemaEdit() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();
	}

	public void closeCinemaEditForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}

	/*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */

	// ClickHandler der das Schließen der DialogBox ermöglicht
	private class closeCinemaEditForm implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			closeCinemaEditForm();
		}

	}

	// ClickHandler der das Speichern der DialogBox ermöglicht
	private class safeCinemaEditForm implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (isValidSymbol() == 0 && isValidSymbol2() == 0) {
				if (cinemaGroupBox.getSelectedIndex() == 0) {
					c = new Cinema(cine.getId(), cine.getCreationDate(), locationBox.getText().trim(),
							box.getText().trim(), 0, cine.getUserID());
				} else {
					c = new Cinema(cine.getId(), cine.getCreationDate(), locationBox.getText().trim(),
							box.getText().trim(), cinemag.elementAt(cinemaGroupBox.getSelectedIndex() - 1).getId(),
							cine.getUserID());
				}
				adminAdministration.updateCinema(c, new AsyncCallback<Cinema>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("was ist falsch geloffen");
					}

					@Override
					public void onSuccess(Cinema result) {
						closeCinemaEditForm();
						RootPanel.get().clear();
						AdminForm adminform = new AdminForm(user, 1);
						RootPanel.get().add(adminform);
					}
				});
			} else
				Window.alert("Prüfen sie ihre Texteingabe");
		}
	}

	// Überprüft ob die Symbole zulässig sind
	private int isValidSymbol() {

		final String symbol = box.getText().toUpperCase().trim();
		box.setFocus(true);

		// Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
		if (!symbol.matches("^[0-9A-Z\\\\.]{1,10}$")) {
			box.selectAll();
			return 1;
		} else
			return 0;
	}

	private int isValidSymbol2() {

		final String symbol = locationBox.getText().toUpperCase().trim();
		box.setFocus(true);

		// Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
		if (!symbol.matches("^[0-9A-Z\\\\.]{1,10}$")) {
			locationBox.selectAll();
			return 1;
		} else
			return 0;
	}
}
