package de.hdm.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.itprojekt.client.gui.admin.AdminForm;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.User;


public class Admin implements EntryPoint {
	
	
	
	protected User currentUser = new User("a");
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	
	@Override
	public void onModuleLoad() {
		
		RootPanel.get().add(new AdminForm(currentUser,0));
	} 
}
