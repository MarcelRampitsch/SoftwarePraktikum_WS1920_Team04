package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.User;

@RemoteServiceRelativePath("admin")
public interface AdminAdministration extends RemoteService {

	void init();
	
	// Cinema
	Cinema addCinema(Cinema c) throws IllegalArgumentException;
	Cinema updateCinema(Cinema upCinema) throws IllegalArgumentException;
	void deleteCinema(Cinema c) throws IllegalArgumentException;
	Vector<Cinema> findAllCinemaByCinemaGroupID(Cinema c);
	Vector<Cinema> findAllCinemaByUser(User u) throws IllegalArgumentException;
	
	// User
	User getUserbyNickname(User u) throws IllegalArgumentException;
	User getUserbyEmail(User u) throws IllegalArgumentException;
	
	// Cinemagroup
	CinemaGroup addCinemaGroup(CinemaGroup cg) throws IllegalArgumentException;
	CinemaGroup updateCinemaGroup(CinemaGroup cg) throws IllegalArgumentException;
	
//	Cinema greetServer(String input) throws IllegalArgumentException;

}
