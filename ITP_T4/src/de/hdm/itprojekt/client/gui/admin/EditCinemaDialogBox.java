package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class EditCinemaDialogBox extends DialogBox {
	
VerticalPanel content = new VerticalPanel();
	
	
	Button close = new Button ("X");
	
	Label cinema = new Label("Cinema");
	
	TextBox box = new TextBox();
	
	TextBox locationBox = new TextBox();
	
	Label location = new Label("Location");
	
	
	Button safe = new Button("save");
	
	
	public EditCinemaDialogBox() {
		
	}
	
	public void onLoad() {
		super.onLoad();
		
		content.add(close);
		content.add(cinema);
		content.add(box);
		content.add(location);
		content.add(locationBox);
		close.addClickHandler(new closeCinemaEditForm());
		content.add(safe);
		safe.addClickHandler(new safeCinemaEditForm());
		

		
		this.add(content);
	}
	
	public void openCinemaEdit() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	public void closeCinemaEditForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	private class closeCinemaEditForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeCinemaEditForm();
			
		}

	
		
	}
	
	private class safeCinemaEditForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			Window.alert("EINGABE GESICHERT");
			
		}
		
		
	}
	
	

}
