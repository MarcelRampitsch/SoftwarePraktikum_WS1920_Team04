package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;

public interface AdminAdministrationAsync {
	
	void init(AsyncCallback<Void> callback);

	// User
	void getUserbyNickname(User u, AsyncCallback<User> callback);
	void getUserbyEmail(User u, AsyncCallback<User> callback);
	
	// Cinema
	void addCinema(Cinema c, AsyncCallback<Cinema> callback);
	void updateCinema(Cinema upCinema, AsyncCallback<Cinema> callback);
	void deleteCinema(Cinema c, AsyncCallback<Void> callback);
	void findAllCinemaByCinemaGroupID(Cinema c, AsyncCallback<Vector<Cinema>> callback);
	void findAllCinemaByUser(User u, AsyncCallback<Vector<Cinema>> callback);
	
	// CinemaGroup
	void addCinemaGroup(CinemaGroup cg, AsyncCallback<CinemaGroup> callback);
	void updateCinemaGroup(CinemaGroup cg, AsyncCallback<CinemaGroup> callback);
	void deleteCinemaGroup(CinemaGroup cg, AsyncCallback<Void> callback);
	void getAllCinemaGroupByUserID(User u, AsyncCallback<Vector<CinemaGroup>> callback);

	// Movie
	void addMovie(Movie m, AsyncCallback<Movie> callback);
	void updateMovie(Movie m, AsyncCallback<Movie> callback);
	void deleteMovie(Movie m, AsyncCallback<Void> callback);
	void getAllMovieByUserID(User u, AsyncCallback<Vector<Movie>> callback);

	//Timeslot
	void deleteAllTimeslotByMovieID(Movie m, AsyncCallback<Void> callback);
	void deleteAllTimeslotByUserID(User u, AsyncCallback<Void> callback);
	void deleteByTimeslotID(Timeslot t, AsyncCallback<Void> callback);
	void findAllTimeslotByUserID(User u, AsyncCallback<Vector<Timeslot>> callback);
	void findByTime(Timeslot t, AsyncCallback<Timeslot> callback);
	void findByTimeslotID(Timeslot t, AsyncCallback<Timeslot> callback);
	void addTimeslot(Timeslot t, AsyncCallback<Timeslot> callback);
	void updateTimeslot(Timeslot t, AsyncCallback<Timeslot> callback);
	
	// Movie
	
	// Timeslot
	
	
	// Presentation
	//void greetServer(String string, AsyncCallback<Cinema> callback);


}
