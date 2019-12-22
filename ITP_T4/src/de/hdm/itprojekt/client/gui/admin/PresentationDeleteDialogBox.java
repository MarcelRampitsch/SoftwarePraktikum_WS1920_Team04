package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.User;

public class PresentationDeleteDialogBox extends DialogBox {

	VerticalPanel content = new VerticalPanel();	
	HorizontalPanel horzcontent = new HorizontalPanel();
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	User currentUser = null; 
	Presentation presentation = null;
	Label presentationLabel = new Label("Präsentation wirklich löschen?");
	Button yes = new Button ("yes");
	Button no = new Button ("no");
	
	public PresentationDeleteDialogBox(Presentation presentation, User currentUser) {
		this.presentation = presentation;
		this.currentUser = currentUser;
	}
	
	public void onLoad() {
		super.onLoad();
		
		content.add(presentationLabel);
		
		horzcontent.add(yes);
		horzcontent.add(no);
		content.add(horzcontent);
		no.addClickHandler(new closePresentationForm());
		yes.addClickHandler(new deletePresentation());
		
		this.add(content);
		
		
		
	}
	
	/*
	 * Methoden zum Öffnen und Schließen der DeleteCinemaDialogBox.
	 */
	
	public void openPresentationDelete() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	public void closePresentationForm() {
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
	
    private class closePresentationForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closePresentationForm();
		}

	}
    
    /**
	 * deleteCinema ClickHandler: Wird beim Click auf <code> yes </code> Button ausgelöst.
	 * Der User bestätigt damit, dass das Cinema gelöscht werden soll.
	 */
    
    private class deletePresentation implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			

			adminAdministration.deletePresentation(presentation, new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
					closePresentationForm();
					RootPanel.get().clear();
					AdminForm adminform = new AdminForm(currentUser,1);
					RootPanel.get().add(adminform);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			});

		}

	}
}
