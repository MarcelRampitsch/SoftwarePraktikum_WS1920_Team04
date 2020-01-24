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
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.User;


public class EditMovieDialogBox extends DialogBox {
	
VerticalPanel content = new VerticalPanel();
	
	Movie movie = null; 
	User user = null;
	Movie m = null;
	
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
		
		//CSS StyleNamenVergabe

		close.addStyleName("MovieEditCloseBox");
		safe.addStyleName("MovieEditSafeBox");
		
		content.add(close);
		content.add(cinema);
		content.add(box);
		box.setText(movie.getName());
		close.addClickHandler(new closeMovieEditForm());
		content.add(safe);
		safe.addClickHandler(new safeCMovieEditForm());
		

		
		this.add(content);
	}
	
	/*
	 * Methoden zum Öffnen und Schließen der DeleteCinemaDialogBox.
	 */
	
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
	
	
	/*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */
	
	// ClickHandler der das Schließen der DialogBox ermöglicht
	private class closeMovieEditForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeMovieEditForm();
			
		}

	}

	// ClickHandler der das Speichern der ermöglicht
	private class safeCMovieEditForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			if(isValidSymbol()==0) {
			m = new Movie (movie.getId(), movie.getCreationDate(),box.getText() ,user.getId());
			adminAdministration.updateMovie(m, new AsyncCallback<Movie>() {
				
				@Override
				public void onSuccess(Movie result) {
				closeMovieEditForm();
				RootPanel.get().clear();
				AdminForm adminform = new AdminForm(user,2);
				RootPanel.get().add(adminform);
				}
				
				@Override
				public void onFailure(Throwable caught) {
				Window.alert("Movie konnte nicht editiert werden");
				}
			});
			}else Window.alert("Ihre Texteingabe hat ungültige Symbole");
		}
	}
	
	// auf zulässige Zeichen überprüfen
	private int isValidSymbol() {

		 final String symbol = box.getText().toUpperCase().trim();
		 box.setFocus(true);

	     // Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
	     if (!symbol.matches("^[0-9A-Z\\\\.]{1,10}$")) {
	       box.selectAll();
	       return 1;
	     }
	     else return 0;
	}
}
