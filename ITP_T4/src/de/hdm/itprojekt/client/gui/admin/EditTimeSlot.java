package de.hdm.itprojekt.client.gui.admin;

import java.sql.Timestamp;

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
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author SerhatUlus Klasse, die das Anzeigen einer DialogBox mitsamt ihrem
 *         Inahlt realisiert. Klasse kommt zum Tragen, wenn man auf den Button
 *         "Edit" klickt
 *
 */
public class EditTimeSlot extends DialogBox {


	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>adminAdministration</code>.
	 */
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	
	//Erstellung der notwendigen Widget sowie Attribute
	User user = null;
	Timeslot time = null;
	Timeslot ts = null;
	VerticalPanel content = new VerticalPanel();
	Label timeslot = new Label("TimeSlot");
	Button close = new Button("X");
	TextBox timeslotbox = new TextBox();
	Button safe = new Button("save");

	public EditTimeSlot(Timeslot time, User user) {
		this.time = time;
		this.user = user;
	}

	public void onLoad() {

		// CSS StyleNamenVergabe
		close.addStyleName("timeSlotEditClose");
		safe.addStyleName("timeSlotEditSafe");

		super.onLoad();

		content.add(close);
		close.addClickHandler(new closehandler());

		content.add(timeslot);

		content.add(timeslotbox);
		timeslotbox.setText(time.getTime());
		content.add(safe);

		safe.addClickHandler(new safehandler());

		this.add(content);

	}

	/*
	 * Methode die das Anzeigen der DialogBox realisiert
	 */
	public void openEditTimeSlot() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();
	}

	/*
	 * Methode die das schließen des Fensters realisiert
	 */
	public void closeEditTimeSlot() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}

	/*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */

	/*
	 * Dieser ClickHandler kommt zum Tragen, wenn auf den closeButton geklickt wird
	 */
	private class closehandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			closeEditTimeSlot();

		}

	}

	/*
	 * Diser ClickHandler kommt zum Tragen, falls auf den saveButton geklickt wird
	 */
	private class safehandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			if (isValidSymbol1() == 1) {
				ts = new Timeslot(timeslotbox.getText(), time.getUserID(), time.getId(), time.getCreationDate());
				adminAdministration.updateTimeslot(ts, new AsyncCallback<Timeslot>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("was ist falsch geloffen");
					}

					@Override
					public void onSuccess(Timeslot result) {
						closeEditTimeSlot();
						RootPanel.get().clear();
						AdminForm adminform = new AdminForm(user, 3);
						RootPanel.get().add(adminform);
					}
				});
			} else
				Window.alert("Muss 18:00 Uhr entsprechen");
		}
	}

	private int isValidSymbol1() {

		final String symbol = timeslotbox.getText();

		// Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
		if (symbol.matches("^[0-2]{1}[0-9]{1}[:]{1}[0-5]{1}[0-9]{1}")) {
			return 1;
		} else
			return 0;
	}
}
