package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class FooterForm extends HorizontalPanel {

	public void onLoad() FooterForm() {
		
		Label footerTxt = new Label("IT-Projekt WS19/20 Team04 Kinofix © Copyright 2020");
		
		this.setHorizontalAlignment(ALIGN_CENTER);
		this.setVerticalAlignment(ALIGN_MIDDLE);
		
		this.addStyleName("footer");
		this.add(footerTxt);
		
	}
	
}
