package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.User;

public class RegistrierungsForm extends VerticalPanel {
	
	User user = new User();
	Label name = new Label("Bitte Nickname eingeben");
	Button speichern = new Button("safe");
	TextBox box = new TextBox();
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	
	public RegistrierungsForm(User user) {
		this.user = user;
	}
	
	
	public void onLoad() {
		super.onLoad();
		
		this.add(name);
		this.add(box);
		this.add(speichern);
		speichern.addClickHandler(new safeHandler());
	}
	
	
	public class safeHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if(box.getText() != "") {
				
			User temp = new User(user.getId(),box.getText(), user.getEmail(),null); 
			editorAdministration.updateUser(temp, new AsyncCallback<User>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert("Fehler safehandler");
				}

				@Override
				public void onSuccess(User result) {
					EditorForm editForm = new EditorForm(result);
					RootPanel.get().clear();
					RootPanel.get().add(editForm);
					
				}
			});
		
		}
			
		}
	}
	
	
	
	
	

}
