package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author Dominik Thumm, SerhatUlus
 *
 */

public class MovieForm  extends VerticalPanel{
	
	
	ListBox moviebox = new ListBox();
    Label movieLabel = new Label("Movie");
//	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();

    
    Button movieEdit = new Button("Edit");
    Button movieNew = new Button("New");
    Button movieDelete = new Button("Delete");
    
    
    HorizontalPanel movieaddbox = new HorizontalPanel();
    HorizontalPanel buttonbox = new HorizontalPanel();
	private User user = null;

    
    public MovieForm(User user) {
    	this.user  = user;
    }

    
    public void onLoad() {
    	
    	super.onLoad();
    	
    	this.add(movieLabel);
    	
    	movieaddbox.add(moviebox);
    	
    	movieEdit.addClickHandler(new editMovieClickHandler());
    	movieNew.addClickHandler(new addMovieClickHandler());
    	movieDelete.addClickHandler(new deleteMovieClickHandler());
    	
    	moviebox.addItem("spidermann");
    	moviebox.addItem("Der böse Film");
    	moviebox.addItem("Spiderman 3");

    	
    	
    	buttonbox.add(movieEdit);
    	buttonbox.add(movieNew);
    	buttonbox.add(movieDelete);
    	
    	this.add(movieaddbox);
    	this.add(buttonbox);
     	
    }
    
    /*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */
	
	/**
	 * ClickHandler für Erstellung eines Movie
	 */

    public class addMovieClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			MovieAddDialogbox cinemagroup = new MovieAddDialogbox();
			cinemagroup.openMovieAdd();
			
			
		}
		
	}
    
    /**
	 * ClickHandler zum editieren eines Movie
	 */
	public class editMovieClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {

			EditMovieDialogBox editMovie = new EditMovieDialogBox();
			editMovie.openMovieEdit();
			
		}
	}
	
	/**
	 * ClickHandler zum löschen eines Movie
	 */
	public class deleteMovieClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			DeleteMovieDialogBox deleteMovie = new DeleteMovieDialogBox();
			deleteMovie.openMovieDelete();
					
			
		}
	}
	
	private class addCinemaGroupCallback implements AsyncCallback <Movie>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Movie result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	private class editMovieCallback implements AsyncCallback <Movie>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Movie result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	private class deleteMovieCallback implements AsyncCallback <Movie>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Movie result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
