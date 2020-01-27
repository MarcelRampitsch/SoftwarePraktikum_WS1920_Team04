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
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.User;

/*
 * 
 * @author SerhatUlus
 * Klasse, die das Anzeigen einer DialogBox mitsamt ihrem inhalt realisiert
 *
 */
public class PresentationDeleteDialogBox extends DialogBox {

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>adminAdministration</code>.
	 */
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	
	//Erstellung der Widget + Attribute
	VerticalPanel content = new VerticalPanel();
	HorizontalPanel horzcontent = new HorizontalPanel();
	Presentation presentation = null;
	Label pres = new Label("Präsentation wirklich löschen?");
	Button yes = new Button("yes");
	Button no = new Button("no");
	User user = null;

	
	public PresentationDeleteDialogBox(Presentation presentation, User user) {
		this.presentation = presentation;
		this.user = user;
	}

	public void onLoad() {
		super.onLoad();

		// CSS StyleNamenVergabe
		yes.addStyleName("presentationDeleteSaveBox");
		no.addStyleName("presentationDeleteCancelBox");

		content.add(pres);

		horzcontent.add(yes);
		horzcontent.add(no);
		content.add(horzcontent);

		no.addClickHandler(new closePresentation());

		yes.addClickHandler(new deleteTimeslot());

		this.add(content);

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
	public void closepresentation() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}

	/*
	 * Der ClickHandler der zum Tragen kommt, falls auf den no-button geklickt wird
	 */
	private class closePresentation implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			closepresentation();

		}
	}

	private class deleteTimeslot implements ClickHandler {

		public void onClick(ClickEvent event) {

			adminAdministration.deletePresentation(presentation, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("????");
				}

				public void onSuccess(Void result) {
					closepresentation();
					RootPanel.get().clear();
					AdminForm adminform = new AdminForm(user, 4);
					RootPanel.get().add(adminform);
				}
			});

		}

	}
}
