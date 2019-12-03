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
 * @author serhatulus
 * Klasse die das Anzeigen einer DialogBox realisiert, falls man auf den Button Add klickt in der TimeslotForm
 *
 */
public class TimeSlotDialogBox extends DialogBox {
	
	HorizontalPanel timeslotcontent = new HorizontalPanel();
	
	VerticalPanel content = new VerticalPanel();
	
	Label timeslot = new Label("Cinemagroup:");
	
	TextBox timeslotbox = new TextBox();
	
	Button close = new Button("X");
	Button safe = new Button("save");
	
	
	
	public TimeSlotDialogBox () {
		
		
	}
	
	public void onLoad() {
		
		super.onLoad();
		
		content.add(close);
		close.addClickHandler(new closehandler());
		
		content.add(timeslot);
		content.add(timeslotbox);
		
		content.add(timeslotcontent);
		
		content.add(safe);
		safe.addClickHandler(new safehandler());
		
		this.add(content);
		
	}
	
	
	
	
	/*
	 * Methode die das schließen des Fensters realisiert
	 */
	public void closetimeslot() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	

}
	
	/*
	 * Methode die das Anzeigen der DialogBox realisiert
	 */
	public void opentimeslot() {
			this.setGlassEnabled(true);
			this.setAnimationEnabled(true);
			this.center();
			this.show();
	}
	
	/*
	 * Der ClickHandler, der die Methode closetimeslot beinhaltet, das wiederum dafür führt, dass sich das Fenster schließt
	 */
	private class closehandler implements ClickHandler {
		
		public void onClick(ClickEvent event) {
			closetimeslot();
		}
		
		
	}
	/*Der ClickHandler, der zum Tragen kommt, falls der safe-Button geklickt wird
	 * 
	 */
	private class safehandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			Window.alert("EINGABE GESICHERT");
		}
	}
	
	
	

}
