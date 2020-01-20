package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.User;

public interface LoginAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void login(String url, AsyncCallback<User> callback);

}