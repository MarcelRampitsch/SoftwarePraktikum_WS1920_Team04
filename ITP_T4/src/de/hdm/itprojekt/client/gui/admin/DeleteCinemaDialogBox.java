package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;

public class DeleteCinemaDialogBox extends DialogBox {
	
	VerticalPanel content = new VerticalPanel();
	
	HorizontalPanel horzcontent = new HorizontalPanel();

	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	Cinema cinema = null;
	
	Label cinemaLabel = new Label("Cinema wirklich löschen?");
	
	Button yes = new Button ("yes");
	Button no = new Button ("no");
	
	public DeleteCinemaDialogBox() {
		
	}
	
	public void onLoad() {
		super.onLoad();
		
		content.add(cinemaLabel);
		
		horzcontent.add(yes);
		horzcontent.add(no);
		content.add(horzcontent);
		no.addClickHandler(new closeCinemaForm());
		yes.addClickHandler(new deleteCinema());
		
		this.add(content);
		
		
		
	}
	
	/*
	 * Methoden zum Öffnen und Schließen der DeleteCinemaDialogBox.
	 */
	
	public void openCimemaDelete() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	public void closeCinemaForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	/*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */
	
	
	
	/**
	 * closeCinemaForm ClickHandler: Wird beim Click auf <code> no </code> Button ausgelöst.
	 * Der User bestätigt damit, dass das Cinema nicht gelöscht werden soll.
	 */
	
    private class closeCinemaForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeCinemaForm();
			
		}

	}
    
    /**
	 * deleteCinema ClickHandler: Wird beim Click auf <code> yes </code> Button ausgelöst.
	 * Der User bestätigt damit, dass das Cinema gelöscht werden soll.
	 */
    
    private class deleteCinema implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			
			Window.alert("Wir sind im ClickHandler");

			adminAdministration.deleteCinema(cinema.getId(), new DeleteCallBack());


		}

	}
    
    /**
	 * CallBack des Clickhandlers <code> deleteCinema ClickHandler </code>
	 * Bei erfolgreichem Rückruf (onSucess) wird das Cinema Objekt gelöscht. Danach wird die dazugehörige <code> DeleteCinemaDialogBox </code> geschlossen. 
	 * 
	 */
	class DeleteCallBack implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler");
			
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Erfolg");

			closeCinemaForm();

			
		}
    

    
	}

}
