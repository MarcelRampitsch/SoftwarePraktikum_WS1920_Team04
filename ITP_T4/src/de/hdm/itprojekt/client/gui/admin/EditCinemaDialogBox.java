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
import de.hdm.itprojekt.shared.bo.User;


public class EditCinemaDialogBox extends DialogBox {
	
	Cinema cine = null; 
	User user = null;
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	Cinema c = null;
	VerticalPanel content = new VerticalPanel();
	Button close = new Button ("X");
	Label cinema = new Label("Cinema");
	TextBox box = new TextBox();
	TextBox locationBox = new TextBox();
	Label location = new Label("Location");
	Button safe = new Button("save");
	
	
	public EditCinemaDialogBox(Cinema cine, User user) {
		this.cine = cine;
	}
	
	public void onLoad() {
		super.onLoad();
		
		content.add(close);
		content.add(cinema);
		content.add(box);
		content.add(location);
		content.add(locationBox);
		close.addClickHandler(new closeCinemaEditForm());
		content.add(safe);
		safe.addClickHandler(new safeCinemaEditForm());
		box.setText(cine.getName());
		locationBox.setText(cine.getLocation());

		
		this.add(content);
	}
	
	public void openCinemaEdit() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	public void closeCinemaEditForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	private class closeCinemaEditForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeCinemaEditForm();
			
		}

	
		
	}
	
	private class safeCinemaEditForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			c = new Cinema(cine.getId(), cine.getCreationDate(), locationBox.getText(), box.getText(), cine.getCinemaGroupID(), cine.getUserID());
			adminAdministration.updateCinema(c, new AsyncCallback<Cinema>() {
				
				@Override
				public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
				}

				@Override
				public void onSuccess(Cinema result) {
				closeCinemaEditForm();
				RootPanel.get().clear();
				AdminForm adminform = new AdminForm(user);
				RootPanel.get().add(adminform);
				}});
		}
	}
}
