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
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author SerhatUlus
 *
 */

public class EditCinemaGroupDialogBox extends DialogBox {
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	User user = null;

	CinemaGroup cine = null; 
	
	CinemaGroup cg =null;

	
	
	VerticalPanel content = new VerticalPanel();

	Label cinemagroup = new Label("CinemaGroup");
	
	Button close = new Button ("X");
	
	TextBox cinemagroupbox = new TextBox();

	Button safe = new Button("save");


	
	public EditCinemaGroupDialogBox(CinemaGroup cine, User user) {
		this.cine=cine;
		
	}
	
	
	public void onLoad() {
		super.onLoad();
		
		content.add(close);
		content.add(cinemagroup);
		close.addClickHandler(new closehandler());
		content.add(cinemagroupbox);
		content.add(safe);
		safe.addClickHandler(new safehandler());
		cinemagroupbox.setText(cine.getName());
		
		this.add(content);
	}
	
	
	
	public void openCinemaGroupEdit() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	public void closeCinemaGroupEdit() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}


	private class closehandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			closeCinemaGroupEdit();

		}
	}

	private class safehandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			cg = new CinemaGroup(cine.getId(), cine.getCreationDate(), cinemagroupbox.getText(), cine.getUserID());
			adminAdministration.updateCinemaGroup(cg, new AsyncCallback<CinemaGroup>() {
				
				@Override
				public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
				}

				@Override
				public void onSuccess(CinemaGroup result) {
				closeCinemaGroupEdit();
				RootPanel.get().clear();
				AdminForm adminform = new AdminForm(user);
				RootPanel.get().add(adminform);
				}});
		}
	}
}


