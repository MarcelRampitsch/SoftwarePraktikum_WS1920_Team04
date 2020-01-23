package de.hdm.itprojekt.client.gui.admin;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.gui.admin.CinemaForm.addCinemaClickHandler;
import de.hdm.itprojekt.client.gui.admin.CinemaForm.deleteCinemaClickHandler;
import de.hdm.itprojekt.client.gui.admin.CinemaForm.editCinemaClickHandler;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author DominikThumm, SerhatUlus, VanDuyHo
 *
 */

public class MovieForm  extends VerticalPanel{
	
    //private Label movieLabel = new Label("Movie");
	
    AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

    private ListBox movieBox = new ListBox();
    
    Button editMovie = new Button("Edit");
    Button newMovie = new Button("New");
    Button deleteMovie = new Button("Delete");
    
	HorizontalPanel moviePanel1 = new HorizontalPanel();
	HorizontalPanel moviePanel2 = new HorizontalPanel();
	
    private User user = null;
	private Movie selectedMovie = null;
	private Vector<Movie> movie = null;
	private Movie delete = null;

    
    public MovieForm(User user) {
    	this.user  = user;
    }

    
    public void onLoad() {
    	
    	super.onLoad();
    	
		//CSS Stylename Vergabe

    	editMovie.addStyleName("editMovieStyle");
    	newMovie.addStyleName("newMovieStyle");
    	deleteMovie.addStyleName("deleteMovieStyle");
    	
    	newMovie.addClickHandler(new addMovieClickHandler());
		editMovie.addClickHandler(new editMovieClickHandler());
		deleteMovie.addClickHandler(new deleteMovieClickHandler());
    	
    	//this.add(movieLabel);    	
    	moviePanel1.add(movieBox);
    	
    	adminAdministration.getAllMovieByUserID(this.user, new AsyncCallback<Vector<Movie>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
			}

			@Override 
			public void onSuccess(Vector<Movie> result) {
				
				for (int i = 0; i < result.size(); i++ ) {
					movieBox.addItem(result.elementAt(i).getName());
					movie = result;
				}
			}
		});
    	
    	moviePanel2.add(editMovie);
		moviePanel2.add(newMovie);
		moviePanel2.add(deleteMovie);
		

		this.add(moviePanel1);
		this.add(moviePanel2);
     	
    }
    
    /*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */
	
	/**
	 * ClickHandler für Erstellung eines Movie
	 */

    public class addMovieClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			
			MovieAddDialogbox movie = new MovieAddDialogbox(user);
			movie.openMovieAdd();
			
		}
		
	}
    
    /**
	 * ClickHandler zum editieren eines Movie
	 */
	public class editMovieClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
	
			selectedMovie = movie.elementAt(movieBox.getSelectedIndex());
			EditMovieDialogBox edit = new EditMovieDialogBox(selectedMovie, user);
			edit.openMovieEdit();
			
		}
	}
	
	/**
	 * ClickHandler zum löschen eines Movie
	 */
	public class deleteMovieClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
					
			delete = movie.elementAt(movieBox.getSelectedIndex());
			DeleteMovieDialogBox edit = new DeleteMovieDialogBox(delete, user);
			edit.openMovieDelete();
			
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
