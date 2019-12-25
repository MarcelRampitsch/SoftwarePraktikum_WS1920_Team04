package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NicknameForm extends VerticalPanel{
	
	HorizontalPanel content = new HorizontalPanel()	;
	
	Label nickname = new Label("Nickname");
	
	SuggestBox suche = new SuggestBox();
	
	TextBox box = new TextBox();
	
	Button add = new Button("Add");

	
	
	public void onLoad() {
		super.onLoad();
		
		this.add(nickname);
		
		content.add(suche);
		content.add(add);
		add.addClickHandler(new addclickhandler());
		
		this.add(content);
		
		this.add(box);
		
		
	
}
	
	private class addclickhandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Window.alert("Nickname wurde hinzugefügt");
			
		}
		
		
	}

}
