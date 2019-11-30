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

	Label cinemaGroup = new Label("CinemaGroup");
	Label cinema = new Label("Cinema");
	
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();

	
	ListBox cinemaGroupBox = new ListBox();
	ListBox cinemaBox = new ListBox();
	
	Button editCinemaGroup = new Button("Edit");
	Button newCinemaGroup = new Button("New");
	Button deleteCinemaGroup = new Button("Delete");
	Button editCinema = new Button("Edit");
	Button newCinema = new Button("New");
	Button deleteCinema = new Button("Delete");
	Button addCinemaGroup = new Button("+");
	Button addCinema = new Button("+");
	
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

		cinemaGroupPanel1.add(addCinemaGroup);
		cinemaGroupPanel1.add(addCinema);
		
		cinemaGroupPanel2.add(editCinemaGroup);
		cinemaGroupPanel2.add(newCinemaGroup);
		cinemaGroupPanel2.add(deleteCinemaGroup);
		
		this.add(cinemaGroupPanel1);
		this.add(cinemaGroupPanel2);
		
		this.add(cinema);
		
		cinemaPanel1.add(cinemaBox);
		cinemaBox.addItem("ufa-palast");
		cinemaBox.addItem("traumpalast");
		cinemaBox.addItem("central und union");

		
		cinemaPanel1.add(addCinema);
		
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
     public class addCinemaClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			
		}
		
	}
	
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
	public class editCinemaClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			
		}
	}
	
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
	
	/**
	 * ClickHandler zum löschen eines Cinema
	 */
	public class deleteCinemaClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			
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
