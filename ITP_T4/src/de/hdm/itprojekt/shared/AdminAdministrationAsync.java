package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Cinema;

public interface AdminAdministrationAsync {
	
	void init(AsyncCallback<Void> callback);

	// Cinema
	void addCinema(Cinema c, AsyncCallback<Cinema> callback);
	void updateCinema(Cinema upCinema, AsyncCallback<Cinema> callback);
	void deleteCinema(int cinemaID, AsyncCallback<Void> callback);
	void findAllCinemaByCinemaGroupID(int cinemaGroupID, AsyncCallback<Vector<Cinema>> callback);

	void findAllCinemaByUser(int userID, AsyncCallback<Vector<Cinema>> callback);


	//void greetServer(String string, AsyncCallback<Cinema> callback);


}
