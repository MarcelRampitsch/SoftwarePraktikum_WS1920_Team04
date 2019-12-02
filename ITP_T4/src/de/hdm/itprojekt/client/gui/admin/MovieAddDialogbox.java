package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class MovieAddDialogbox extends DialogBox {
	
    VerticalPanel content = new VerticalPanel();
	
	Label movie = new Label("Movie");
	
	TextBox moviebox = new TextBox();
	
	Button close = new Button("X");
	Button safe = new Button("save");
	
	
	public MovieAddDialogbox() {
		
		
	
	}

	
	public void onLoad() {
		super.onLoad();
		
		content.add(close);
		close.addClickHandler(new closeHandler());

		content.add(movie);
		content.add(moviebox);
		content.add(safe);
		safe.addClickHandler(new safeHandler());
		this.add(content);
	
	}
	public void CloseMovieGroup() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	

}
	
	public void openMovieAdd() {
			this.setGlassEnabled(true);
			this.setAnimationEnabled(true);
			this.center();
			this.show();
	}
	
	
	
	
	private class closeHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			CloseMovieGroup();
			
		}
		
	}
	
	private class safeHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
		}
		
	}
	

}
