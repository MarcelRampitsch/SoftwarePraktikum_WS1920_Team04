package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.User;


public class DeleteMovieDialogBox extends DialogBox {
	
    VerticalPanel content = new VerticalPanel();
	
	HorizontalPanel horzcontent = new HorizontalPanel();

	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	Movie movie = null;
	User currentUser = null; 
	
	Label movieLabel = new Label("Movie wirklich löschen?");
	
	Button yes = new Button ("yes");
	Button no = new Button ("no");
	
	public DeleteMovieDialogBox(Movie movie, User currentUser) {
		this.movie = movie;
		this.currentUser = currentUser;
	}
	
	public void onLoad() {
		super.onLoad();
		
		content.add(movieLabel);
		
		horzcontent.add(yes);
		horzcontent.add(no);
		content.add(horzcontent);
		no.addClickHandler(new closeMovieForm());
		
		this.add(content);
		
		
		
	}
	
	public void openMovieDelete() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	public void closeMovieForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	
    private class closeMovieForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeMovieForm();
			
		}

	}
    
 private class deleteMovie implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			
		}

	}
    
	
	

}
