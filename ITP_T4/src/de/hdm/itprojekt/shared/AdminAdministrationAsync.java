package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Cinema;

public interface AdminAdministrationAsync {
	
	void init(AsyncCallback<Void> callback);

	void addCinema(Cinema c, AsyncCallback<Cinema> callback);


}
