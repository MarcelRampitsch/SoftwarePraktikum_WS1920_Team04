package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.itprojekt.shared.bo.Cinema;


public interface AdminAdministration extends RemoteService {

	void init();
	
	Cinema addCinema(Cinema c) throws IllegalArgumentException;
	Cinema updateCinema(Cinema upCinema) throws IllegalArgumentException;
	void deleteCinema(int cinemaID) throws IllegalArgumentException;
	Vector<Cinema> findAllCinemaByCinemaGroupID(int cinemaGroupID) throws IllegalArgumentException;
	
}
