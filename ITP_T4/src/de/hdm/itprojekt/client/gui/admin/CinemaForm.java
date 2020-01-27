package de.hdm.itprojekt.client.gui.admin;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author DominikThumm, VanDuyHo, SerhatUlus
 * 
 */

public class CinemaForm extends VerticalPanel {

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>adminAdministration</code>.
	 */
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	//Erstellung der Widgets sowie Attribute
	ListBox cinemaBox = new ListBox();
	Button editCinema = new Button("Edit");
	Button newCinema = new Button("New");
	Button deleteCinema = new Button("Delete");

	HorizontalPanel cinemaPanel1 = new HorizontalPanel();
	HorizontalPanel cinemaPanel2 = new HorizontalPanel();
	private User user = null;
	private Cinema selectedCinema = null;
	private Vector<Cinema> cine = null;
	private Cinema delete = null;
	int cinemagroupindex;

	public CinemaForm(User user) {
		this.user = user;

	}

	public void onLoad() {

		super.onLoad();

		// CSS Stylename Vergabe

		editCinema.addStyleName("editCinemaStyle");
		newCinema.addStyleName("newCinemaStyle");
		deleteCinema.addStyleName("deleteCinemaStyle");

		newCinema.addClickHandler(new addCinemaClickHandler());
		editCinema.addClickHandler(new editCinemaClickHandler());
		deleteCinema.addClickHandler(new deleteCinemaClickHandler());

		cinemaPanel1.add(cinemaBox);

		adminAdministration.findAllCinemaByUser(this.user, new AsyncCallback<Vector<Cinema>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
			}

			@Override
			public void onSuccess(Vector<Cinema> result) {

				for (int i = 0; i < result.size(); i++) {
					cinemaBox.addItem(result.elementAt(i).getName());
					cine = result;
				}
			}
		});

		cinemaPanel2.add(editCinema);
		cinemaPanel2.add(newCinema);
		cinemaPanel2.add(deleteCinema);

		this.add(cinemaPanel1);
		this.add(cinemaPanel2);

	}

	/*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */

	/**
	 * ClickHandler zum anlegen eines neuen Cinema
	 */
	public class addCinemaClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			CinemaAddDialogBox cinema = new CinemaAddDialogBox(user);

			cinema.openCinema();

		}

	}

	/**
	 * ClickHandler zum editieren eines Cinema
	 */
	public class editCinemaClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			selectedCinema = cine.elementAt(cinemaBox.getSelectedIndex());
			EditCinemaDialogBox edit = new EditCinemaDialogBox(selectedCinema, user);
			edit.openCinemaEdit();

		}
	}

	/**
	 * ClickHandler zum löschen eines Cinema
	 */
	public class deleteCinemaClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			delete = cine.elementAt(cinemaBox.getSelectedIndex());
			DeleteCinemaDialogBox edit = new DeleteCinemaDialogBox(delete, user);
			edit.openCinemaDelete();

		}
	}

}
