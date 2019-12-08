package de.hdm.itprojekt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.shared.LoginAdministration;

public class LoginAdministrationImpl  extends RemoteServiceServlet implements LoginAdministration {
	
//	private static LoginAdministrationImpl loginAdministration = null;
//	private EditorAdministrationImpl editorAdministration = null;
	

/*	public LoginAdministrationImpl() throws IllegalArgumentException {

	}

	public static LoginAdministrationImpl getLoginAdministration() {
		if (loginAdministration == null) {
			loginAdministration = new LoginAdministrationImpl();
		}
		return loginAdministration;
	}
 */
	

	
	
	
	
	public void init() throws IllegalArgumentException {
		
		EditorAdministrationImpl e = new EditorAdministrationImpl();
		e.init();
		//this.editorAdministration = e;
	}


}
