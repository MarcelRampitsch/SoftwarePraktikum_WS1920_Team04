package de.hdm.itprojekt.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.CommonSettings;
import de.hdm.itprojekt.shared.EditorAdministration;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;



/**
* Klasse mit Eigenschaften und Diensten, die für alle Client-seitigen Klassen
* relevant sind.
* 
* @author thies
* @version 1.0
* @since 28.02.2012
* 
*/

public class ClientSideSettings extends CommonSettings {
/*	
	private static EditorAdministrationAsync editorAdministration = null;
	private static AdminAdministrationAsync adminAdministration = null;
//	private static LoginAdministrationAsync loginAdministration = null;
	
	public static EditorAdministrationAsync getEditorAdministration() {

		if (editorAdministration == null) {
			editorAdministration = GWT.create(EditorAdministration.class);
			editorAdministration.init(new ClientSideSettings().new InitCallback());
		}

		return editorAdministration;
	}
	
	
	public static AdminAdministrationAsync getAdminAdministration() {
		
		if (adminAdministration == null) { 
			adminAdministration= GWT.create(AdminAdministrationAsync.class); 
			adminAdministration.init(new ClientSideSettings().new InitCallback()); 
			  }
			
		return adminAdministration; 
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

	} */

	

}
