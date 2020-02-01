package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
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
 * @author SerhatUlus
 *
 */
public class CinemaGroupAddDialogBox extends DialogBox {

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>adminAdministration</code>.
	 */
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	
	//Erstellung der notwendigen Widget sowie Attribute
	User user = null;
	HorizontalPanel cinemagroupcontent = new HorizontalPanel();
	VerticalPanel content = new VerticalPanel();
	Label cinemagroup = new Label("Cinemagroup:");
	TextBox cinemagroupbox = new TextBox();

	Button close = new Button("X");
	Button safe = new Button("save");
	
	

	public CinemaGroupAddDialogBox(User user) {
		this.user = user;

	}

	public void onLoad() {
		super.onLoad();

		// CSS Stylename Vergabe

		safe.addStyleName("cinemaGroupAddSafeStyle");
		close.addStyleName("cinemaGroupAddCloseStyle");

		content.add(close);
		close.addClickHandler(new closeHandler());

		content.add(cinemagroup);

		content.add(cinemagroupcontent);

		cinemagroupcontent.add(cinemagroupbox);

		content.add(safe);
		safe.addClickHandler(new safeCinemaGroupForm());

		this.add(content);

	}

	// Zum schließen der CinemaGroupAddDialogBox
	public void CloseCinemaGroup() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);

	}

	// Zum Öffnen der CinemaGroupAddDialogBox
	public void openCinemaGroup() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();
	}

	// ClickHandler der das Schließen der DialogBox ermöglicht
	private class closeHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			CloseCinemaGroup();

		}

	}

	// ClickHandler der das Speichern einer CinemaGroup ermöglicht
	private class safeCinemaGroupForm implements ClickHandler {
		CinemaGroup cinemagroup = null;

		@Override
		public void onClick(ClickEvent event) {
			cinemagroup = new CinemaGroup(cinemagroupbox.getText(), user.getId());
			if (isValidSymbol() == 0) {
				adminAdministration.addCinemaGroup(cinemagroup, new AsyncCallback<CinemaGroup>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("was ist falsch geloffen");
					}

					@Override
					public void onSuccess(CinemaGroup result) {
						CloseCinemaGroup();
						RootPanel.get().clear();
						AdminForm adminform = new AdminForm(user, 0);
						RootPanel.get().add(adminform);
						isValidSymbol();
					}
				});
			} else
				Window.alert("Prüfen sie ihre Texteingaben");
		}

	}

	// überprüfen ob es sich bei der Eingabe um ein zulässiges Symbol handelt (noch
	// anpassen)
	private int isValidSymbol() {

		final String symbol = cinemagroupbox.getText().toUpperCase().trim();
		cinemagroupbox.setFocus(true);

		// Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
		if (!symbol.matches("^[0-9A-Z\\\\.]{1,20}$")) {
			cinemagroupbox.selectAll();
			return 1;
		} else
			return 0;
	}

}
