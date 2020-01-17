package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.User;

@RemoteServiceRelativePath("login")
public interface LoginAdministration  extends RemoteService {
	
	void init();

	User login(String url);

}
