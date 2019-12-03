package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
* 
* @author DominikThumm
* Diese Klasse ermöglicht das Anzeigen einer DialogBox, wenn der Nutzer auf den "new" Button klickt in der <code>CinemaForm</code>
*
*/


public class CinemaDialogBox extends DialogBox {
	
	VerticalPanel content = new VerticalPanel();
	
	
	Button close = new Button ("X");
	
	Label cinema = new Label("Cinema");
	
	TextBox box = new TextBox();
	
	TextBox locationBox = new TextBox();
	
	Label location = new Label("Location");
	
	
	Button safe = new Button("save");
	
	
	public CinemaDialogBox() {
		
	}
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Panel, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Panel hinzugefügt.
	 */
	
	public void onLoad() {
		super.onLoad();
		
		content.add(close);
		content.add(cinema);
		content.add(box);
		content.add(location);
		content.add(locationBox);
		close.addClickHandler(new closeCinemaForm());
		content.add(safe);
		safe.addClickHandler(new safeCinemaForm());
		

		
		this.add(content);
	}
	
	public void openCinema() {
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
	
	// ClickHandler der das Schließen der DialogBox ermöglicht
	
	private class closeCinemaForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeCinemaForm();
			
		}

	
		
	}
	// ClickHandler der das Speichern eines Cinemas/Location ermöglicht

	
	private class safeCinemaForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			Window.alert("EINGABE GESICHERT");
			
		}
		
		
	}

}
