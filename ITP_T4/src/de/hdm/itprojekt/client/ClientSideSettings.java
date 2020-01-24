package de.hdm.itprojekt.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.ClientSideSettings.InitCallback;
import de.hdm.itprojekt.shared.AdminAdministration;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.CommonSettings;
import de.hdm.itprojekt.shared.EditorAdministration;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.LoginAdministration;
import de.hdm.itprojekt.shared.LoginAdministrationAsync;



/**
* Klasse mit Eigenschaften und Diensten, die für alle Client-seitigen Klassen
* relevant sind.
*/

public class ClientSideSettings extends CommonSettings {
	
	/**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>AdminAdministration</code>.
	   */
	
	private static AdminAdministrationAsync adminAdministration = null;	
	
	/**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>EditorAdministration</code>.
	   */
	
	private static EditorAdministrationAsync editorAdministration = null;
	
	private static LoginAdministrationAsync loginAdministration = null;
	
	public static EditorAdministrationAsync getEditorAdministration() {
	    // Gab es bislang noch keine EditorAdministration-Instanz, dann...
		if (editorAdministration == null) {
		      // Zunächst instantiieren wir EditorAdministration
			editorAdministration = GWT.create(EditorAdministration.class);
			editorAdministration.init(new ClientSideSettings().new InitCallback());
		}
	    // Rückgabe von EditorAdministration.
		return editorAdministration;
	}
	
	
	public static AdminAdministrationAsync getAdminAdministration() {
	    // Gab es bislang noch keine AdminAdministration-Instanz, dann...
		if (adminAdministration == null) { 
		      // Zunächst instantiieren wir AdminAdministration
			adminAdministration = GWT.create(AdminAdministration.class); 
			adminAdministration.init(new ClientSideSettings().new InitCallback()); 
			  }
	    // Rückgabe von AdminAdministration.
		return adminAdministration; 
		}
	
	public static LoginAdministrationAsync getLoginAdministration() {

		if (loginAdministration == null) {
			loginAdministration = GWT.create(LoginAdministration.class);
			loginAdministration.init(new ClientSideSettings().new InitCallback());
		}

		return loginAdministration;
	} 

	
	class InitCallback implements AsyncCallback<Void> {


		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub

			// Nothing happens!
		}
	} 
}
