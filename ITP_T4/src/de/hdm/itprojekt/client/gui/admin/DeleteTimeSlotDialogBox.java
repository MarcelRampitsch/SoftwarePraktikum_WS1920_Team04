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
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;

/*
 * 
 * @author SerhatUlus
 * Klasse, die das Anzeigen einer DialogBox mitsamt ihrem inhalt realisiert
 *
 */
public class DeleteTimeSlotDialogBox extends DialogBox {

	VerticalPanel content = new VerticalPanel();
	
	HorizontalPanel horzcontent = new HorizontalPanel();

	Timeslot time = null;
	
	Label timeslot = new Label("Timeslot wirklich löschen?");
	
	Button yes = new Button ("yes");
	Button no = new Button ("no");
	
	
	
	User user = null;
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	
	public DeleteTimeSlotDialogBox(Timeslot timeslot,User user) {
		this.time=timeslot;
		this.user=user;
	}
	
	
	public void onLoad() {
		super.onLoad();
		
		//CSS StyleNamenVergabe
		
		yes.addStyleName("timeSlotDeleteYes");
		no.addStyleName("timeSlotDeleteNo");
		
		
		content.add(timeslot);
		
		horzcontent.add(yes);
		horzcontent.add(no);
		content.add(horzcontent);

		no.addClickHandler(new closetimeslot());
		
		yes.addClickHandler(new deleteTimeslot());
		
		this.add(content);
				
	}
	
	
	
	/*
	 * Methode die das Anzeigen der DialogBox realisiert
	 */
	public void openTimeSlot() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	/*
	 * Methode die das schließen des Fensters realisiert
	 */
	public void closeTimeSlot() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	/*
	 * Der ClickHandler der zum Tragen kommt, falls auf den no-button geklickt wird
	 */
    private class closetimeslot implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			closeTimeSlot();
			

			
	

		}
    }
    private class deleteTimeslot implements ClickHandler{

		
	
	public void onClick(ClickEvent event) {
		

		adminAdministration.deleteTimeslot(time, new AsyncCallback<Void>() {
			
			@Override
			public void onFailure(Throwable caught) {
					Window.alert("????");
			}
			
			public void onSuccess(Void result) {
				closeTimeSlot();
				RootPanel.get().clear();
				AdminForm adminform = new AdminForm(user,3);
				RootPanel.get().add(adminform);
			}
		});

	}

}
}
