package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Cinema;

@RemoteServiceRelativePath("admin")
public interface AdminAdministration extends RemoteService {

	void init();
	
	
	Cinema addCinema(Cinema c) throws IllegalArgumentException;
	Cinema updateCinema(Cinema upCinema) throws IllegalArgumentException;
	void deleteCinema(int cinemaID) throws IllegalArgumentException;
	Vector<Cinema> findAllCinemaByCinemaGroupID(int cinemaGroupID) throws IllegalArgumentException;
	Vector<Cinema> findAllCinemaByUser(int userID) throws IllegalArgumentException;
	
	
//	Cinema greetServer(String input) throws IllegalArgumentException;

}
