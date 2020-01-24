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
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.User;


public class DeleteMovieDialogBox extends DialogBox {
	
    VerticalPanel content = new VerticalPanel();
	
	HorizontalPanel horzcontent = new HorizontalPanel();

	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	Movie movie = null;
	User user = null; 
	
	Label movieLabel = new Label("Movie wirklich löschen?");
	
	Button yes = new Button ("yes");
	Button no = new Button ("no");
	
	public DeleteMovieDialogBox(Movie movie, User user) {
		this.movie = movie;
		this.user = user;
	}
	
	public void onLoad() {
		super.onLoad();
		
		//CSS SytleNamenVergabe
		
		yes.addStyleName("deleteMovieYesBox");
		no.addStyleName("deleteMovieNoBox");
		
		content.add(movieLabel);
		
		horzcontent.add(yes);
		yes.addClickHandler(new deleteMovie());
		horzcontent.add(no);
		content.add(horzcontent);
		no.addClickHandler(new closeMovieForm());
		
		this.add(content);
		
		
		
	}
	
	/*
	 * Methoden zum Öffnen und Schließen der DeleteCinemaDialogBox.
	 */
	
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
	
	/*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */
	
	// ClickHandler der das Schließen der DialogBox ermöglicht
    private class closeMovieForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeMovieForm();
			
		}

	}
	// ClickHandler der das löschen deines Movie ermöglicht
    private class deleteMovie implements ClickHandler{
		
	
			@Override
			public void onClick(ClickEvent event) {
				
				adminAdministration.deleteMovie(movie, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						closeMovieForm();
						RootPanel.get().clear();
						AdminForm adminform = new AdminForm(user,2);
						RootPanel.get().add(adminform);
						
					}
				});
			
		}

	}
    
	
	

}
