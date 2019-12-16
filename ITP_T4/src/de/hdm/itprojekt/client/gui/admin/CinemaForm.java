package de.hdm.itprojekt.client.gui.admin;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sun.java.swing.plaf.windows.resources.windows;


import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author DominikThumm, VanDuyHo, SerhatUlus
 * 
 */

public class CinemaForm extends VerticalPanel {

	//private Label cinemaGroup = new Label("CinemaGroup");
	private Label cinema = new Label("Cinema");
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	


	
	//ListBox cinemaGroupBox = new ListBox();
	ListBox cinemaBox = new ListBox();
	
//	Button editCinemaGroup = new Button("Edit");
//	Button newCinemaGroup = new Button("New");
//	Button deleteCinemaGroup = new Button("Delete");
	Button editCinema = new Button("Edit");
	Button newCinema = new Button("New");
	Button deleteCinema = new Button("Delete");
	
//	HorizontalPanel cinemaGroupPanel1 = new HorizontalPanel();
//	HorizontalPanel cinemaGroupPanel2 = new HorizontalPanel();
	
	HorizontalPanel cinemaPanel1 = new HorizontalPanel();
	HorizontalPanel cinemaPanel2 = new HorizontalPanel();
	private User user = null;
	private Cinema selectedCinema = null;
	private Vector<Cinema> cine = null;
	private Cinema delete = null;
	
	
	public CinemaForm(User user) {
		this.user = user;
		
	}
	
	
	public void onLoad() {
		
		super.onLoad();
		
		//this.add(cinemaGroup);
		
//		cinemaGroupPanel1.add(cinemaGroupBox);
//		cinemaGroupBox.addItem("kette1");
//		cinemaGroupBox.addItem("kette2");
//		cinemaGroupBox.addItem("kette3");

		
//		cinemaGroupPanel2.add(editCinemaGroup);
//		editCinemaGroup.addClickHandler(new editCinemaGroupClickHandler());
//		cinemaGroupPanel2.add(newCinemaGroup);
//		newCinemaGroup.addClickHandler(new addCinemaGroupClickHandler());
		newCinema.addClickHandler(new addCinemaClickHandler() );
		editCinema.addClickHandler(new editCinemaClickHandler());
		deleteCinema.addClickHandler(new deleteCinemaClickHandler());
//		deleteCinemaGroup.addClickHandler(new deleteCinemaGroupClickHandler());
//		
//		cinemaGroupPanel2.add(deleteCinemaGroup);
//		
//		this.add(cinemaGroupPanel1);
//		this.add(cinemaGroupPanel2);
		
		this.add(cinema);
		
		cinemaPanel1.add(cinemaBox);
		
		
		adminAdministration.findAllCinemaByUser(this.user, new AsyncCallback<Vector<Cinema>>() {
		
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("was ist falsch geloffen");
			}

			@Override 
			public void onSuccess(Vector<Cinema> result) {
				
				for (int i = 0; i < result.size(); i++ ) {
					cinemaBox.addItem(result.elementAt(i).getName());
					cine = result;
				}
			}
		});
		
		
		cinemaPanel2.add(editCinema);
		cinemaPanel2.add(newCinema);
		cinemaPanel2.add(deleteCinema);
		

		this.add(cinemaPanel1);
		this.add(cinemaPanel2);
		
	}
	

	/*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */
	
	/**
	 * ClickHandler für Erstellung einer CinemaGroup
	 */
//	public class addCinemaGroupClickHandler implements ClickHandler{
//		
//		public void onClick(ClickEvent event) {
//			CinemaGroupAddDialogBox cinemagroup = new CinemaGroupAddDialogBox();
//			cinemagroup.openCinemaGroup();
//			
//		}
//			
	

	
    public class addCinemaClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			CinemaAddDialogBox cinema = new CinemaAddDialogBox(user);
			
			cinema.openCinema();
			
		}
			
	}
    
   public class editCinemaClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			selectedCinema = cine.elementAt(cinemaBox.getSelectedIndex());
			EditCinemaDialogBox edit = new EditCinemaDialogBox(selectedCinema, user);
			edit.openCinemaEdit();
			
		}
	}
   
   /**
	 * ClickHandler zum löschen eines Cinema
	 */
	public class deleteCinemaClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			delete = cine.elementAt(cinemaBox.getSelectedIndex());
			DeleteCinemaDialogBox edit = new DeleteCinemaDialogBox(delete, user);
			edit.openCinemaDelete();
											
		}
	}
	
//	/**
//	 * ClickHandler zum löschen einer CinemaGroup
//	 */
//	public class deleteCinemaGroupClickHandler implements ClickHandler{
//		
//		public void onClick(ClickEvent event) {
//			DeleteCinemaGroupDialogBox deleteCinemaGroup = new DeleteCinemaGroupDialogBox();
//			deleteCinemaGroup.openCimemaGroupDelete();
//		}
//	}
	
	
	
	
	
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
	
	/**
	 * ClickHandler zum editieren einer CinemaGroup
	 */
	public class editCinemaGroupClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			EditCinemaGroupDialogBox editcinemagroup = new EditCinemaGroupDialogBox();
			editcinemagroup.openCinemaGroupEdit();
			
		}
	}
	
	
	
//	private class editCinemaGroupCallback implements AsyncCallback <CinemaGroup>{
//
//		@Override
//		public void onFailure(Throwable caught) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void onSuccess(CinemaGroup result) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	}
	
	
	
	
//	private class deleteCinemaGroupCallback implements AsyncCallback <CinemaGroup>{
//
//		@Override
//		public void onFailure(Throwable caught) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void onSuccess(CinemaGroup result) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	}
	
	/**
	 * ClickHandler für Erstellung eines Cinema
	 */
    
	
	private class addCinemaCallback implements AsyncCallback <Cinema>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Cinema result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * ClickHandler zum editieren eines Cinema
	 */
	
	
	private class editCinemaCallback implements AsyncCallback <Cinema>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Cinema result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	private class deleteCinemaCallback implements AsyncCallback <Cinema>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Cinema result) {
			// TODO Auto-generated method stub
			
		}
		
		
		
	
}
}