package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DeleteTimeSlotDialogBox extends DialogBox {

	VerticalPanel content = new VerticalPanel();
	
	HorizontalPanel horzcontent = new HorizontalPanel();

	
	Label timeslot = new Label("Timeslot wirklich löschen?");
	
	Button yes = new Button ("yes");
	Button no = new Button ("no");
	
	
	public DeleteTimeSlotDialogBox() {
		
	}
	
	
	public void onLoad() {
		super.onLoad();
		
		
		content.add(timeslot);
		
		horzcontent.add(yes);
		horzcontent.add(no);
		content.add(horzcontent);

		no.addClickHandler(new closetimeslot());
		
	
		
		this.add(content);
		
	}
	
	
	
	
	public void open() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	public void close() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	
    private class closetimeslot implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			close();
			
	

		}
	    

}
}
