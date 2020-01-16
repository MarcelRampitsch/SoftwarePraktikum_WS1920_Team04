package de.hdm.itprojekt.server;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.shared.LoginAdministration;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;

public class LoginAdministrationImpl extends RemoteServiceServlet implements LoginAdministration {

	private static LoginAdministrationImpl loginAdministration = null;
	private static EditorAdministrationImpl editorAdministration = null;

	public LoginAdministrationImpl() throws IllegalArgumentException {

	}

	public static LoginAdministrationImpl getLoginAdministration() {
		if (loginAdministration == null) {
			loginAdministration = new LoginAdministrationImpl();
		}
		return loginAdministration;
	}

	public User login(String requestUri) {
		if (editorAdministration == null) {
			init();

		}

		try {
			//System.out.println("Lade Google User");

			UserService userService = UserServiceFactory.getUserService();
			com.google.appengine.api.users.User clientApiUser = userService.getCurrentUser();

			if (clientApiUser != null) {
				User u = editorAdministration.getUserByEmail(new User(clientApiUser.getEmail()));
				if (u != null) {
					u.setIsLoggedIn(true);
					u.setURL(userService.createLogoutURL(requestUri));

					return u;
				} else {
					u = new User(clientApiUser.getNickname(), clientApiUser.getEmail());
					u = editorAdministration.createUser(u);
					u.setIsLoggedIn(true);
					u.setURL(userService.createLogoutURL(requestUri));

					return u;

				}

			} else {
				User u = new User();
				u.setIsLoggedIn(false);
				u.setURL(userService.createLoginURL(requestUri));
				return u;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	public void init() throws IllegalArgumentException {

		EditorAdministrationImpl e = new EditorAdministrationImpl();
		e.init();
		this.editorAdministration = e;
	}

}
