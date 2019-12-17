package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author SerhatUlus
 *
 */
public class CinemaGroupAddDialogBox extends DialogBox {
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration() ;

	
	User user = null;

	
	HorizontalPanel cinemagroupcontent = new HorizontalPanel();
	
	VerticalPanel content = new VerticalPanel();
	
	Label cinemagroup = new Label("Cinemagroup:");
	
	TextBox cinemagroupbox = new TextBox();
	
	Button close = new Button("X");
	Button safe = new Button("save");
	
	
	public CinemaGroupAddDialogBox(User user) {
		this.user= user;
		
	
	}

	
	public void onLoad() {
		super.onLoad();
		
		content.add(close);
		close.addClickHandler(new closeHandler());
		
		content.add(cinemagroup);
		
		content.add(cinemagroupcontent);
		
		cinemagroupcontent.add(cinemagroupbox);
		
		content.add(safe);
		safe.addClickHandler(new safeCinemaGroupForm());
		
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
	
	private class safeCinemaGroupForm implements ClickHandler{
		CinemaGroup cinemagroup = null;

		@Override
		public void onClick(ClickEvent event) {
			cinemagroup = new CinemaGroup(cinemagroupbox.getText(), user.getId());
			
			adminAdministration.addCinemaGroup(cinemagroup, new AsyncCallback<CinemaGroup>() {
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("was ist falsch geloffen");
				}

				@Override
				public void onSuccess(CinemaGroup result) {
				CloseCinemaGroup();
				RootPanel.get().clear();
				AdminForm adminform = new AdminForm(user,1);
				RootPanel.get().add(adminform);	
				
				}});
				
		}
			
			
			
			
			
			
			
		}
		
		
	}

