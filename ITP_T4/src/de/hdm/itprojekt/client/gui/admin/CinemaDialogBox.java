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


public class CinemaDialogBox extends DialogBox {
	
	VerticalPanel content = new VerticalPanel();
	
	HorizontalPanel horzcontent = new HorizontalPanel();
	
	Button close = new Button ("X");
	
	Label cinema = new Label("Cinema");
	
	TextBox box = new TextBox();
	
	
	Button safe = new Button("safe");
	
	
	public CinemaDialogBox() {
		
	}
	
	public void onLoad() {
		super.onLoad();
		
		content.add(close);
		content.add(cinema);


		
		horzcontent.add(box);
		content.add(horzcontent);
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
	
	private class closeCinemaForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeCinemaForm();
			
		}

	
		
	}
	
	private class safeCinemaForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			Window.alert("EINGABE GESICHERT");
			
		}
		
		
	}

}
