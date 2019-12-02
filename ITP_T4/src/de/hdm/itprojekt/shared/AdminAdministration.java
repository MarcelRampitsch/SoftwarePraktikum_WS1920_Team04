package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.itprojekt.shared.bo.Cinema;


public interface AdminAdministration extends RemoteService {

	void init();
	
	Cinema addCinema(Cinema c) throws IllegalArgumentException;

}
