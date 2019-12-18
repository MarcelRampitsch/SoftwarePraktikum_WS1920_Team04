package de.hdm.itprojekt.client.gui.admin;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.User;

public class CinemaGroupForm extends VerticalPanel{
	
	private Label cinemaGroup = new Label("CinemaGroup");
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	
	ListBox cinemaGroupBox = new ListBox();
	
	Button editCinemaGroup = new Button("Edit");
	Button newCinemaGroup = new Button("New");
	Button deleteCinemaGroup = new Button("Delete");
	
	HorizontalPanel cinemaGroupPanel1 = new HorizontalPanel();
	HorizontalPanel cinemaGroupPanel2 = new HorizontalPanel();

	private User user = null;
	private CinemaGroup delete =null;
	private Vector<CinemaGroup> cine = null;
	private CinemaGroup selectedCinemaGroup = null;
	private int cinemagroupindex;

	
	public CinemaGroupForm(User user) {
		this.user=user;
		
	}
	
	
	
	public void onLoad() {
		super.onLoad();
		
		
		// Laden der CinemaGroups aus der Datenbank
		cinemaGroupPanel1.add(cinemaGroupBox);
		adminAdministration.getAllCinemaGroupByUserID(this.user, new AsyncCallback<Vector<CinemaGroup>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Laden der CinemaGroup!");
			}

			@Override 
			public void onSuccess(Vector<CinemaGroup> result) {
				
				for (int i = 0; i < result.size(); i++ ) {
					cinemaGroupBox.addItem(result.elementAt(i).getName());
					cine = result;
				}
			}
		});
		
		
		cinemaGroupPanel2.add(editCinemaGroup);
		editCinemaGroup.addClickHandler(new editCinemaGroupClickHandler());
		cinemaGroupPanel2.add(newCinemaGroup);
		newCinemaGroup.addClickHandler(new addCinemaGroupClickHandler());
		
		deleteCinemaGroup.addClickHandler(new deleteCinemaGroupClickHandler());
		
		cinemaGroupPanel2.add(deleteCinemaGroup);
		
		this.add(cinemaGroupPanel1);
		this.add(cinemaGroupPanel2);
		
		
		
		
	}

	
	/**
	 * ClickHandler für Erstellung einer CinemaGroup
	 */
	public class addCinemaGroupClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			CinemaGroupAddDialogBox cinemagroup = new CinemaGroupAddDialogBox(user);
			cinemagroup.openCinemaGroup();
			
	
       }
			
	}
	
	/**
	 * ClickHandler zum löschen einer CinemaGroup
	 */
	public class deleteCinemaGroupClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			delete = cine.elementAt(cinemaGroupBox.getSelectedIndex());
			DeleteCinemaGroupDialogBox deleteCinemaGroup = new DeleteCinemaGroupDialogBox(delete,user);
			deleteCinemaGroup.openCimemaGroupDelete();
		}
	}
	
	
	
	/**
	 * ClickHandler zum editieren einer CinemaGroup
	 */
	public class editCinemaGroupClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			selectedCinemaGroup = cine.elementAt(cinemaGroupBox.getSelectedIndex());
			cinemagroupindex = cinemaGroupBox.getSelectedIndex();
			EditCinemaGroupDialogBox editcinemagroup = new EditCinemaGroupDialogBox(selectedCinemaGroup, user);
			editcinemagroup.openCinemaGroupEdit();
			
		}
	}
	
	
	
	
}
		
