package de.hdm.itprojekt.client.gui;

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
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
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

public class UserEdit extends DialogBox {

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>adminAdministration</code>.
	 */
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();

	User user = new User();
	VerticalPanel content = new VerticalPanel();

	Label usereditor = new Label("User Editor");
	Label name = new Label("Username:");
	TextBox box = new TextBox();
	Button save = new Button("save");
	Button delete = new Button("Delete User");

	public UserEdit(User user) {
		this.user = user;
	}

	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Panel, dem Browser hinzugefügt
	 * wurde. Die dieser Klasse dazugehörigen grafischen Elemente werden dem Panel
	 * hinzugefügt.
	 */

	public void onLoad() {
		super.onLoad();
		content.add(usereditor);
		content.add(delete);
		delete.addClickHandler(new deleteClickHandler());
		save.addClickHandler(new saveClickHandler());
		content.add(name);
		content.add(box);
		box.setText(user.getNickname());
		content.add(save);
		this.add(content);
	}

	// Zum Öffnen der CinemaAddDialogBox
	public void openUser() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();
	}

	// Zum Schließen der CinemaAddDialogBox
	public void closeUserForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}

	// ClickHandler der das Schließen der DialogBox ermöglicht

	private class deleteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			editorAdministration.deleteUser(user, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
					Window.Location.replace("/ITP_T4.html");
					closeUserForm();
				}
			});

		}

	}
	// ClickHandler der das Speichern eines Cinemas/Location ermöglicht

	private class saveClickHandler implements ClickHandler {
		
		public void onClick(ClickEvent event) {
			if(isValidSymbol()==0 && box.getText()!=("")) {
				User temp = new User(user.getId(),box.getText(),user.getEmail(),null);
				editorAdministration.updateUser(temp, new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(User result) {
						// TODO Auto-generated method stub
					}
				});
				closeUserForm();
			}
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
}