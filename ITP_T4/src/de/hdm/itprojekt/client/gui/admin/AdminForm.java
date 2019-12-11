package de.hdm.itprojekt.client.gui.admin;


import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministration;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;


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
	
	/**
	 * 
	 * <code>main</code>: Zentraler Bestandteil. Umschließt alle anderen Panels
	 */
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	
	private User currentUser;
	
	/*
	VerticalPanel main = new VerticalPanel();
	Button cinemaButton = new Button("Cinema");
	Button movieButton = new Button("Movie");
	Button timeslotButton = new Button("Timeslot");
	Button presentationButton = new Button("Presentation");  */
	
	private VerticalPanel main = new VerticalPanel();

	
	/**
	 * 
	 * @param currentUser 
	 * Der aktuelle Nutzer (Admin) wird der AdminForm übergeben. So können alle anderen Formen diesen bei Bedarf verwenden.
	 * 
	 */
	public AdminForm(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Panel, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Panel hinzugefügt.
	 */
	public void onLoad() {
		
		super.onLoad();
		/*
		 * CSS-StyleName-Vergabe, um Panels direkt anzusprechen.
		 */

		this.setStylePrimaryName("AdminForm");
		
		adminAdministration.getUserbyEmail(currentUser, new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {	
			}

			@Override
			public void onSuccess(User result) {
			currentUser = result;
				
			}
		});
		
	//	main.addStyleName("AdminMain");
		VerwaltungsForm verwaltungsForm = new VerwaltungsForm(currentUser);
		
		Image logo = new Image("Offical_Logo.png");
	
	//	main.add(cinemaButton);
	//	main.add(movieButton);
	//	main.add(timeslotButton);
	//	main.add(presentationButton);

	//	this.add(main);
	
	//	RootPanel.get("container").add(main);
		

		//Button, dessen ClickEvent zum Admin Mode führt.		
		Button toBesucher = new Button("EditorModus", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						Window.Location.replace("/ITP_T4.html");
					}
				});
		
		main.add(toBesucher);
		main.add(verwaltungsForm);


		this.add(main);

		
				
		


	}

	private class CinemaHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			

			
		}
		
	}
	
	
}
