package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
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
	
	// Movie
	
	// Timeslot
	
	// Presentation
	//void greetServer(String string, AsyncCallback<Cinema> callback);


}
