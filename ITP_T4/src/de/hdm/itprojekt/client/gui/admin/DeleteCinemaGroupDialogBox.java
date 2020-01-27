package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.User;

public class DeleteCinemaGroupDialogBox extends DialogBox {

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>adminAdministration</code>.
	 */
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	
	VerticalPanel content = new VerticalPanel();
	HorizontalPanel horzcontent = new HorizontalPanel();
	User user = null;
	CinemaGroup cinemaGroup = null;
	Label cinemaLabel = new Label("CinemaGroup wirklich löschen?");
	Button yes = new Button("yes");
	Button no = new Button("no");
	

	public DeleteCinemaGroupDialogBox(CinemaGroup cinemaGroup, User user) {
		this.cinemaGroup = cinemaGroup;
		this.user = user;
	}

	public void onLoad() {
		super.onLoad();

		// CSS Stylename Vergabe

		yes.addStyleName("deleteCinemaGroupYesStyle");
		no.addStyleName("deleteCinemaGroupNoStyle");

		content.add(cinemaLabel);

		horzcontent.add(yes);
		horzcontent.add(no);
		content.add(horzcontent);
		no.addClickHandler(new closeCinemaGroupForm());
		yes.addClickHandler(new deleteCinemaGroup());
		this.add(content);
	}

	/*
	 * Methoden zum Öffnen und Schließen der DeleteCinemaDialogBox.
	 */

	public void openCimemaGroupDelete() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();
	}

	public void closeCinemaGroupForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}

	/*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */

	/**
	 * closeCinemaGroupForm ClickHandler: Wird beim Click auf <code> no </code>
	 * Button ausgelöst. Der User bestätigt damit, dass die CinemaGroup nicht
	 * gelöscht werden soll.
	 */

	private class closeCinemaGroupForm implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			closeCinemaGroupForm();
		}
	}

	/**
	 * deleteCinemaGroup ClickHandler: Wird beim Click auf <code> yes </code> Button
	 * ausgelöst. Der User bestätigt damit, dass die CinemaGroup gelöscht werden
	 * soll.
	 */

	private class deleteCinemaGroup implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			adminAdministration.deleteCinemaGroup(cinemaGroup, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
					closeCinemaGroupForm();
					RootPanel.get().clear();
					AdminForm adminform = new AdminForm(user, 0);
					RootPanel.get().add(adminform);

				}
			});

		}

	}

}
