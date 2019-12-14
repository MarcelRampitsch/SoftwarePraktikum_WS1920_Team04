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
import de.hdm.itprojekt.client.gui.admin.CinemaForm.editCinemaGroupClickHandler;
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
	
	public CinemaGroupForm(User user) {
		this.user=user;
		
	}
	
	
	
	public void onLoad() {
		super.onLoad();
		
		cinemaGroupPanel1.add(cinemaGroupBox);
		adminAdministration.getAllCinemaGroupByUserID(this.user, new AsyncCallback<Vector<CinemaGroup>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
			}

			@Override 
			public void onSuccess(Vector<CinemaGroup> result) {
				
				for (int i = 0; i < result.size(); i++ ) {
					cinemaGroupBox.addItem(result.elementAt(i).getName());
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
			CinemaGroupAddDialogBox cinemagroup = new CinemaGroupAddDialogBox();
			cinemagroup.openCinemaGroup();
			
	
	
	
	
}
		private class addCinemaGroupCallback implements AsyncCallback <CinemaGroup>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(CinemaGroup result) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		
		
	}
	
	/**
	 * ClickHandler zum löschen einer CinemaGroup
	 */
	public class deleteCinemaGroupClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			DeleteCinemaGroupDialogBox deleteCinemaGroup = new DeleteCinemaGroupDialogBox();
			deleteCinemaGroup.openCimemaGroupDelete();
		}
	}
	
	
	private class deleteCinemaGroupCallback implements AsyncCallback <CinemaGroup>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(CinemaGroup result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	/**
	 * ClickHandler zum editieren einer CinemaGroup
	 */
	public class editCinemaGroupClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			EditCinemaGroupDialogBox editcinemagroup = new EditCinemaGroupDialogBox();
			editcinemagroup.openCinemaGroupEdit();
			
		}
	}
	
	
	private class editCinemaGroupCallback implements AsyncCallback <CinemaGroup>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(CinemaGroup result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
		
