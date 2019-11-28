package de.hdm.itprojekt.client.gui.admin;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * 
 * @author DominikThumm, VanDuyHo
 * AdminForm vereint alle grafischen Elemente dieses Packages, zur kompletten Anzeige
 * des Admin-Client
 * 
 */

public class AdminForm extends VerticalPanel{
	
	/* 
	 * currentUser speichert den aktuellen Nutzer
	 */
	//protected Administrator adminUser = null;
	
	/**
	 * 
	 * <code>main</code>: Zentraler Bestandteil. Umschließt alle anderen Panels
	 */
	
	VerticalPanel main = new VerticalPanel();
	
	Button cinemaButton = new Button("Cinema");
	Button movieButton = new Button("Movie");
	Button timeslotButton = new Button("Timeslot");
	Button presentationButton = new Button("Presentation");

	
	/**
	 * 
	 * @param currentUser 
	 * Der aktuelle Nutzer (Admin) wird der AdminForm übergeben. So können alle anderen Formen diesen bei Bedarf verwenden.
	 * 
	 */
//	public AdminForm(Administrator adminUser) {
//		this.adminUser = adminUser;
//	}
	
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Panel, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Panel hinzugefügt.
	 */
	public void onLoad() {
		
		super.onLoad();

		/*
		 * CSS-StyleName-Vergabe, um Panels direkt anzusprechen.
		 */

		this.addStyleName("AdminForm");
		
		main.addStyleName("AdminMain");
		
		Image logo = new Image("Offical_Logo.png");
		VerwaltungsForm verwaltungsForm = new VerwaltungsForm();
	
		main.add(cinemaButton);
		main.add(movieButton);
		main.add(timeslotButton);
		main.add(presentationButton);

		this.add(main);
	
		RootPanel.get("container").add(main);

	}

	private class CinemaHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			

			
		}
		
	}
	
	
}
