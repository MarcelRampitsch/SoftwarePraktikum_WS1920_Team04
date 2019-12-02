package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class EditMovieDialogBox extends DialogBox {
	
VerticalPanel content = new VerticalPanel();
	
	
	Button close = new Button ("X");
	
	Label cinema = new Label("Movie");
	
	TextBox box = new TextBox();
	
	
	
	
	Button safe = new Button("save");
	
	
	public EditMovieDialogBox() {
		
	}
	
	public void onLoad() {
		super.onLoad();
		
		content.add(close);
		content.add(cinema);
		content.add(box);
		
		close.addClickHandler(new closeMovieEditForm());
		content.add(safe);
		safe.addClickHandler(new safeCMovieEditForm());
		

		
		this.add(content);
	}
	
	public void openMovieEdit() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	
	public void closeMovieEditForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	private class closeMovieEditForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeMovieEditForm();
			
		}

	
		
	}
	
	private class safeCMovieEditForm implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			Window.alert("EINGABE GESICHERT");
			
		}
		
		
	}
	

}
