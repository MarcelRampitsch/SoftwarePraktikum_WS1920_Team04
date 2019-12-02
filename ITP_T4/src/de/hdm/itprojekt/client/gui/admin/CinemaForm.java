package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;

/**
 * 
 * @author DominikThumm, VanDuyHo, SerhatUlus
 * 
 */

public class CinemaForm extends VerticalPanel {

	private Label cinemaGroup = new Label("CinemaGroup");
	private Label cinema = new Label("Cinema");
	
//	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();

	
	ListBox cinemaGroupBox = new ListBox();
	ListBox cinemaBox = new ListBox();
	
	Button editCinemaGroup = new Button("Edit");
	Button newCinemaGroup = new Button("New");
	Button deleteCinemaGroup = new Button("Delete");
	Button editCinema = new Button("Edit");
	Button newCinema = new Button("New");
	Button deleteCinema = new Button("Delete");
	
	HorizontalPanel cinemaGroupPanel1 = new HorizontalPanel();
	HorizontalPanel cinemaGroupPanel2 = new HorizontalPanel();
	
	HorizontalPanel cinemaPanel1 = new HorizontalPanel();
	HorizontalPanel cinemaPanel2 = new HorizontalPanel();
	
	
	public CinemaForm() {
		
		
	}
	
	
	public void onLoad() {
		
		super.onLoad();
		
		this.add(cinemaGroup);
		
		cinemaGroupPanel1.add(cinemaGroupBox);
		cinemaGroupBox.addItem("kette1");
		cinemaGroupBox.addItem("kette2");
		cinemaGroupBox.addItem("kette3");

		
		cinemaGroupPanel2.add(editCinemaGroup);
		editCinemaGroup.addClickHandler(new editCinemaGroupClickHandler());
		cinemaGroupPanel2.add(newCinemaGroup);
		newCinemaGroup.addClickHandler(new addCinemaGroupClickHandler());
		newCinema.addClickHandler(new addCinemaClickHandler() );
		editCinema.addClickHandler(new editCinemaClickHandler());
		deleteCinema.addClickHandler(new deleteCinemaClickHandler());
		
		cinemaGroupPanel2.add(deleteCinemaGroup);
		
		this.add(cinemaGroupPanel1);
		this.add(cinemaGroupPanel2);
		
		this.add(cinema);
		
		cinemaPanel1.add(cinemaBox);
		cinemaBox.addItem("ufa-palast");
		cinemaBox.addItem("traumpalast");
		cinemaBox.addItem("central und union");

		
		
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
	public class addCinemaGroupClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			CinemaGroupDialogbox cinemagroup = new CinemaGroupDialogbox();
			cinemagroup.openCinemaGroup();
			
		}
			
	}
	
    public class addCinemaClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			CinemaDialogBox cinema = new CinemaDialogBox();
			cinema.openCinema();
			
		}
			
	}
    
   public class editCinemaClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			
			EditCinemaDialogBox edit = new EditCinemaDialogBox();
			edit.openCinemaEdit();
			
		}
	}
   
   /**
	 * ClickHandler zum löschen eines Cinema
	 */
	public class deleteCinemaClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			DeleteCinemaDialogBox deleteCinema = new DeleteCinemaDialogBox();
			deleteCinema.openCimemaDelete();
					
			
		}
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
	
	
	/**
	 * ClickHandler zum löschen einer CinemaGroup
	 */
	public class deleteCinemaGroupClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			
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
