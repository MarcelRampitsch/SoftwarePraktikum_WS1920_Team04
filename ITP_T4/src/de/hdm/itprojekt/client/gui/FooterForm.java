package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/*
 * 
 * @Author VanDuyHo
 * 
 * Footer der auf jeder Seite am unteren Rand angezeigt wird
 * 
 */
public class FooterForm extends HorizontalPanel {
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Widget, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Widget hinzugefügt.
	 * 
	 */

	public void onLoad() {
		
		//Label welches angezeigt wird, wenn man sich im BenutzerClient befindet.
		Label footerTxt = new Label("IT-Project WS19/20 Team04 Kinofix © Copyright 2020");
		
		this.setHorizontalAlignment(ALIGN_CENTER);
		this.setVerticalAlignment(ALIGN_MIDDLE);
		
		this.addStyleName("footer");
		
		//Lable wird dem Panel(VerticalPanel) hinzugefügt, damit es zum anzeigen gebracht wird
		this.add(footerTxt);
		
	}
	
}
