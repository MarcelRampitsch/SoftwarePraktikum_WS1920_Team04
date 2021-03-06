package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import de.hdm.itprojekt.shared.bo.User;

public class DeleteCinemaDialogBox extends DialogBox {

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>adminAdministration</code>.
	 */
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	
	
	//Erstellung der notwendigen Widget sowie Attribute
	VerticalPanel content = new VerticalPanel();
	HorizontalPanel horzcontent = new HorizontalPanel();

	Cinema cinema = null;
	User currentUser = null;
	Label cinemaLabel = new Label("Cinema wirklich löschen?");
	CinemaForm cinemaform;
	Button yes = new Button("yes");
	Button no = new Button("no");

	
	
	public DeleteCinemaDialogBox(Cinema cinema, User currentUser) {
		this.cinema = cinema;
		this.currentUser = currentUser;
	}

	public void onLoad() {
		super.onLoad();

		// CSS Stylename Vergabe

		yes.addStyleName("deleteCinemaYesStyle");
		no.addStyleName("deleteCinemaNoStyle");

		content.add(cinemaLabel);

		horzcontent.add(yes);
		horzcontent.add(no);
		content.add(horzcontent);
		no.addClickHandler(new closeCinemaForm());
		yes.addClickHandler(new deleteCinema());

		this.add(content);

	}

	/*
	 * Methoden zum Öffnen und Schließen der DeleteCinemaDialogBox.
	 */

	public void openCinemaDelete() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();
	}

	public void closeCinemaForm() {
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
	 * closeCinemaForm ClickHandler: Wird beim Click auf <code> no </code> Button
	 * ausgelöst. Der User bestätigt damit, dass das Cinema nicht gelöscht werden
	 * soll.
	 */

	private class closeCinemaForm implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			closeCinemaForm();
		}

	}

	/**
	 * deleteCinema ClickHandler: Wird beim Click auf <code> yes </code> Button
	 * ausgelöst. Der User bestätigt damit, dass das Cinema gelöscht werden soll.
	 */

	private class deleteCinema implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			adminAdministration.deleteCinema(cinema, new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
					closeCinemaForm();
					RootPanel.get().clear();
					AdminForm adminform = new AdminForm(currentUser, 1);
					RootPanel.get().add(adminform);
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			});

		}

	}

}
