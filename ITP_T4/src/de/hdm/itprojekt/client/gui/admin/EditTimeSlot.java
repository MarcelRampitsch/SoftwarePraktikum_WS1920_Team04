package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

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
	
	
	public void openEditTimeSlot() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	public void closeEditTimeSlot() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	
	private class closehandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			closeEditTimeSlot();
			
			
		}
		
		
	}
	private class safehandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			Window.alert("EINGABE GESICHERT");
		}
		
	}
	
	
	
	
}
