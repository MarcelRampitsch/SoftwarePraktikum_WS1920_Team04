package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


public class DeleteCinemaGroupDialogBox extends DialogBox {

    VerticalPanel content = new VerticalPanel();
	
	HorizontalPanel horzcontent = new HorizontalPanel();

	
	
	
	Label cinemaLabel = new Label("CinemaGroup wirklich löschen?");
	
	Button yes = new Button ("yes");
	Button no = new Button ("no");
	
	public DeleteCinemaGroupDialogBox() {
		
	}
	
	public void onLoad() {
		super.onLoad();
		
		content.add(cinemaLabel);
		
		horzcontent.add(yes);
		horzcontent.add(no);
		content.add(horzcontent);
		no.addClickHandler(new closeCinemaForm());
		
		this.add(content);
		
		
		
	}
	
	public void openCimemaGroupDelete() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	public void closeCinemaGroupForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	
    private class closeCinemaForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeCinemaGroupForm();
			
		}

	}
    
 private class deleteCinema implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			
		}

	}
    
}
