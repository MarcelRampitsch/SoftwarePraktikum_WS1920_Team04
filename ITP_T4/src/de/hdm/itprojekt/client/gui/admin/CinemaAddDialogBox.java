package de.hdm.itprojekt.client.gui.admin;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
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

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author DominikThumm 
 * Diese Klasse ermöglicht das Anzeigen einer DialogBox,
 *         wenn der Nutzer auf den "new" Button klickt in der
 *         <code>CinemaForm</code>
 *
 */

public class CinemaAddDialogBox extends DialogBox {

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>adminAdministration</code>.
	 */
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	// Erstellung notwendiger Widgets sowie Attribute
	String name = null;
	User user = null;
	Vector<CinemaGroup> cine = null;
	VerticalPanel content = new VerticalPanel();

	Label cinemaGroup = new Label("CinemaGroup");
	ListBox cinemaGroupBox = new ListBox();
	Button close = new Button("X");
	Label cinema = new Label("Cinema");
	TextBox box = new TextBox();
	TextBox locationBox = new TextBox();
	Label location = new Label("Location");
	Button safe = new Button("save");

	public CinemaAddDialogBox(User user) {
		this.user = user;
	}

	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Panel, dem Browser hinzugefügt
	 * wurde. Die dieser Klasse dazugehörigen grafischen Elemente werden dem Panel
	 * hinzugefügt.
	 */

	public void onLoad() {
		super.onLoad();

		// CSS Stylename Vergabe

		safe.addStyleName("cinemaAddSafeStyle");
		close.addStyleName("cinemaAddCloseStyle");

		content.add(close);
		content.add(cinemaGroup);
		content.add(cinemaGroupBox);
		content.add(cinema);
		content.add(box);
		content.add(location);
		content.add(locationBox);
		close.addClickHandler(new closeCinemaForm());
		content.add(safe);
		safe.addClickHandler(new safeCinemaForm());

		adminAdministration.getAllCinemaGroupByUserID(this.user, new AsyncCallback<Vector<CinemaGroup>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Laden der CinemaGroup!");
			}

			@Override
			public void onSuccess(Vector<CinemaGroup> result) {
				cinemaGroupBox.addItem("Keine Kinogruppe");
				for (int i = 0; i < result.size(); i++) {
					cinemaGroupBox.addItem(result.elementAt(i).getName());
					cine = result;
				}
			}
		});

		this.add(content);
	}

	// Zum Öffnen der CinemaAddDialogBox
	public void openCinema() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();
	}

	// Zum Schließen der CinemaAddDialogBox
	public void closeCinemaForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}

	// ClickHandler der das Schließen der DialogBox ermöglicht

	private class closeCinemaForm implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			closeCinemaForm();

		}

	}
	// ClickHandler der das Speichern eines Cinemas/Location ermöglicht

	private class safeCinemaForm implements ClickHandler {
		Cinema c = null;

		@Override
		public void onClick(ClickEvent event) {
			int cinemaGroupID = cinemaGroupBox.getSelectedIndex();
			if (isValidSymbol() == 0 && isValidSymbol2() == 0) {
				if (cinemaGroupBox.getSelectedItemText() == "Keine Kinogruppe") {
					c = new Cinema(locationBox.getText(), box.getText(), user.getId(), 0);
				} else {
					c = new Cinema(locationBox.getText(), box.getText(), user.getId(),
							cine.elementAt(cinemaGroupID - 1).getId());
				}
				adminAdministration.addCinema(c, new AsyncCallback<Cinema>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("was ist falsch geloffen");
					}

					@Override
					public void onSuccess(Cinema result) {
						closeCinemaForm();
						RootPanel.get().clear();
						AdminForm adminform = new AdminForm(user, 1);
						RootPanel.get().add(adminform);
					}
				});
			} else
				Window.alert("Prüfen sie ihre Texteingaben");
		}
	}

	// überprüfen ob es sich bei der Eingabe um ein zulässiges Symbol handelt (noch
	// anpassen)
	private int isValidSymbol() {

		final String symbol = box.getText().toUpperCase().trim();
		box.setFocus(true);

		// Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
		if (!symbol.matches("^[0-9A-Z\\\\.]{1,20}$")) {
			box.selectAll();
			return 1;
		} else
			return 0;
	}

	private int isValidSymbol2() {

		final String symbol = box.getText().toUpperCase().trim();
		box.setFocus(true);

		// Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
		if (!symbol.matches("^[0-9A-Z\\\\.]{1,20}$")) {
			locationBox.selectAll();
			return 1;
		} else
			return 0;
	}
}