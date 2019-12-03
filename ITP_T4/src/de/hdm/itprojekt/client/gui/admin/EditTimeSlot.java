package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author SerhatUlus
 * Klasse, die das Anzeigen einer DialogBox mitsamt ihrem Inahlt realisiert. 
 * Klasse kommt zum Tragen, wenn man auf den Button "Edit" klickt
 *
 */
public class EditTimeSlot extends DialogBox	 {
	
	VerticalPanel content = new VerticalPanel();

	Label timeslot = new Label("TimeSlot");
	
	Button close = new Button ("X");
	
	TextBox timeslotbox = new TextBox();

	Button safe = new Button("save");

	
	
	public EditTimeSlot() {
		
	}
	
	
	
	public void onLoad() {
		
		super.onLoad();
		
		content.add(close);
		close.addClickHandler(new closehandler());
		
		content.add(timeslot);

		content.add(timeslotbox);
		content.add(safe);
		
		safe.addClickHandler(new safehandler());

		this.add(content);
		
		
	}
	
	/*
	 * Methode die das Anzeigen der DialogBox realisiert
	 */
	public void openEditTimeSlot() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	
	/*
	 * Methode die das schließen des Fensters realisiert
	 */
	public void closeEditTimeSlot() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	
/*
 * Dieser ClickHandler kommt zum Tragen, wenn auf den closeButton geklickt wird
 */
	private class closehandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			closeEditTimeSlot();
			
			
		}
		
		
	}
	
	/*
	 * Diser ClickHandler kommt zum Tragen, falls auf den saveButton geklickt wird
	 */
	private class safehandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			Window.alert("EINGABE GESICHERT");
		}
		
	}
	
	
	
	
}
