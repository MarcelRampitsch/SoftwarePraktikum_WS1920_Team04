package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author DominikThumm, VanDuyHo Diese Klasse ermöglicht das Anzeigen einer
 *         DialogBox, wenn der Nutzer auf den "new" Button klickt in der
 *         <code>MovieForm</code>
 *
 */

public class MovieAddDialogbox extends DialogBox {

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>adminAdministration</code>.
	 */
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	// Erstellung der Widget + Attribute
	User user = null;
	VerticalPanel content = new VerticalPanel();
	Label movie = new Label("Movie");
	TextBox moviebox = new TextBox();
	Button close = new Button("X");
	Button safe = new Button("save");

	public MovieAddDialogbox(User user) {

		this.user = user;

	}

	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Panel, dem Browser hinzugefügt
	 * wurde. Die dieser Klasse dazugehörigen grafischen Elemente werden dem Panel
	 * hinzugefügt.
	 */

	public void onLoad() {
		super.onLoad();

		// CSS StyleNamenVergabe
		close.addStyleName("movieCloseBox");
		safe.addStyleName("movieSafeBox");

		content.add(close);
		close.addClickHandler(new closeHandler());

		content.add(movie);
		content.add(moviebox);
		content.add(safe);
		safe.addClickHandler(new safeHandler());
		this.add(content);

	}

	/*
	 * Methode die das Schließen der DialogBox realisiert
	 */
	public void CloseMovieGroup() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);

	}

	/*
	 * Methode die das Öffnen der DialogBox realisiert
	 */

	public void openMovieAdd() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();
	}

	/*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */

	// ClickHandler der das Schließen der DialogBox ermöglicht

	private class closeHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			CloseMovieGroup();

		}

	}
	// ClickHandler der das Speichern eines Cinemas/Location ermöglicht

	private class safeHandler implements ClickHandler {

		Movie m = null;

		@Override
		public void onClick(ClickEvent event) {
			if (isValidSymbol() == 0) {
				m = new Movie(moviebox.getText(), user.getId());

				adminAdministration.addMovie(m, new AsyncCallback<Movie>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("was ist falsch geloffen");
					}

					@Override
					public void onSuccess(Movie result) {
						CloseMovieGroup();
						RootPanel.get().clear();
						AdminForm adminform = new AdminForm(user, 2);
						RootPanel.get().add(adminform);
						isValidSymbol();
					}
				});
			} else
				Window.alert("Ihre Texteingabe hat ungültige Symbole");
		}
	}

	private int isValidSymbol() {

		final String symbol = moviebox.getText().toUpperCase().trim();
		moviebox.setFocus(true);

		// Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
		if (!symbol.matches("^[0-9A-Z\\\\.]{1,10}$")) {
			moviebox.selectAll();
			return 1;
		} else
			return 0;
	}
}
