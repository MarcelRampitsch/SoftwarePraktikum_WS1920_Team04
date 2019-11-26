package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;




/**
 * 
 * @author DominikThumm
 * AdminForm vereint alle grafischen Elemente dieses Packages, zur kompletten Anzeige
 * des Admin-Client
 */

public class AdminForm extends VerticalPanel {
	
	/* 
	 * currentUser speichert den aktuellen Nutzer
	 */
	//protected Administrator adminUser = null;
	
	/**
	 * <code>header</code>: Oberer Teil des Fensters. Erstreckt sich über die ganze Länge der Anwendung
	 * <code>main</code>: Zentraler Bestandteil. Umschließt alle anderen Panels, außer <code>header</code>
	 * <code>west</code>: Linke Seite der Anwendung. Enhält die Formen <code>VerwaltungsForm</code>
	 */
	
	HorizontalPanel header = new HorizontalPanel();
	HorizontalPanel main = new HorizontalPanel();
	
	VerticalPanel west = new VerticalPanel();
	
	/**
	 * 
	 * @param currentUser 
	 * Der aktuelle Nutzer (Admin) wird der AdminForm übergeben. So können alle anderen Formen diesen bei Bedarf verwenden.
	 * 
	 */
	
	//public AdminForm(Administrator adminUser) {
	//	this.adminUser = adminUser;
	//}
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Panel, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Panel hinzugefügt.
	 */
	public void onLoad() {
		super.onLoad();
		
		/*
		 * CSS-StyleName-Vergabe, um Panels direkt anzusprechen.
		 */
	//	Button b1 = new Button("sdv");

		this.addStyleName("AdminForm");
		header.addStyleName("AdminHeader");
		main.addStyleName("AdminMain");
		west.addStyleName("AdminWest");
		
		/**
		 *  Jede Form wird demjeweiligen Panel hinzugefügt.
		 */
		
		VerwaltungsForm verwaltungsForm = new VerwaltungsForm();

		
		west.add(verwaltungsForm);
		
	//	header.add(logo);
		
		//Button, dessen ClickEvent zum Admin Mode führt.
		Button toBesucher = new Button("Kinobesucher", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.Location.replace("/Kinofix_Test.html");
			}
		});
		west.add(toBesucher);
		
		//Die zwei Hauptpanels werden der AdminForm hinzugefügt.
		this.add(header);
		this.add(main);
		this.add(west);
	}
	
	

}
