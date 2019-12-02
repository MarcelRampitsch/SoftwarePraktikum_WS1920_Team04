package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CinemaGroupDialogbox extends DialogBox {
	
	HorizontalPanel cinemagroupcontent = new HorizontalPanel();
	
	VerticalPanel content = new VerticalPanel();
	
	Label cinemagroup = new Label("Cinemagroup:");
	
	TextBox cinemagroupbox = new TextBox();
	
	Button close = new Button("X");
	Button add = new Button("+");
	Button safe = new Button("safe");
	
	
	public CinemaGroupDialogbox() {
		
		
	
	}

	
	public void onLoad() {
		super.onLoad();
		
		content.add(close);
		close.addClickHandler(new closeHandler());
		
		content.add(cinemagroup);
		
		content.add(cinemagroupcontent);
		
		cinemagroupcontent.add(cinemagroupbox);
		cinemagroupcontent.add(add);
		
		content.add(safe);
		
		this.add(content);

		
		
	}
	
	public void CloseCinemaGroup() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	

}
	
	public void openCinemaGroup() {
			this.setGlassEnabled(true);
			this.setAnimationEnabled(true);
			this.center();
			this.show();
	}
	
	
	
	
	private class closeHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			CloseCinemaGroup();
			
		}
		
	}
}
