package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.User;


public class EditMovieDialogBox extends DialogBox {
	
VerticalPanel content = new VerticalPanel();
	
	Movie movie = null; 
	User user = null;
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	
	Button close = new Button ("X");
	
	Label cinema = new Label("Movie");
	
	TextBox box = new TextBox();
	
	
	
	
	Button safe = new Button("save");
	
	
	public EditMovieDialogBox(Movie movie, User user) {
		this.movie = movie;
		this.user = user;		
	}
	
	public void onLoad() {
		super.onLoad();
		
		content.add(close);
		content.add(cinema);
		content.add(box);
		
		close.addClickHandler(new closeMovieEditForm());
		content.add(safe);
		safe.addClickHandler(new safeCMovieEditForm());
		

		
		this.add(content);
	}
	
	public void openMovieEdit() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	public void closeMovieEditForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	private class closeMovieEditForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeMovieEditForm();
			
		}

	
		
	}
	
	private class safeCMovieEditForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			Window.alert("EINGABE GESICHERT");
			
		}
		
		
	}
	

}
