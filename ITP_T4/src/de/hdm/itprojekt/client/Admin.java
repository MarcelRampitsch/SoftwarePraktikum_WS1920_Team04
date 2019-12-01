package de.hdm.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.itprojekt.client.gui.admin.AdminForm;


public class Admin implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		RootPanel.get().add(new AdminForm());

		
	} 

}
