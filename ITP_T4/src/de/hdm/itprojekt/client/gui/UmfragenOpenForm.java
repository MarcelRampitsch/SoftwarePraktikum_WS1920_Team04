package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


/**
 * 
 * @author DominikThumm
 * DialogBox, die angezeigt wird, wenn der Nutzer eine Umfrage erstellen möchte.
 * Die Klasse enthält entsprechende ClickHandler & Methoden zum Bestätigen oder Abbrechen der Aktion.
 */


public class UmfragenOpenForm extends DialogBox {
	
	
	
	private Label umfrageErstellung = new Label("Umfrageerstellung:");
	private Button closeButton = new Button("X");
	private TextBox namenBox = new TextBox();
	private Label changeLabel = new Label("Umfragenname:");
	private Label datum = new Label("Datum wählen:");
	private Label film = new Label ("Film wählen:");
	private final ListBox dropBoxFilm = new ListBox();
	private Label kino = new Label("Kino wählen:");
	private final ListBox dropBoxKino = new ListBox();
	private Label spielzeit = new Label("Spielzeit wählen");
	private final ListBox dropBoxSpielzeit = new ListBox();
	private Button safeUmfrage = new Button("Umfrage speichern");
 //   private ArrayList<String> umfagen = new ArrayList<String>();


    private CloseUmfrageOpenFormClickHandler closeHandler;
    private safehandler umfageSpeichern;
    

	
    private DatePicker datePicker = new DatePicker();



	
    private VerticalPanel content = new VerticalPanel();
	
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Widget, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Widget hinzugefügt.
	 * Den Buttons werden deren Funktion entsprechend ClickHandler zugewiesen. 
	 */
	
	public void onLoad() {
		super.onLoad();
		closeHandler = new CloseUmfrageOpenFormClickHandler();
		umfageSpeichern = new safehandler();
		
		closeButton.addStyleName("UmfrageCloser");
		safeUmfrage.addStyleName("UmfrageSafer");
		
		safeUmfrage.addClickHandler(umfageSpeichern);
		content.add(closeButton);
		closeButton.addClickHandler(closeHandler);
		content.add(umfrageErstellung);
		content.add(changeLabel);
		content.add(namenBox);
		content.add(datum);
		content.add(datePicker);
		content.add(film);
		dropBoxFilm.addItem("Beispiel 1");
		dropBoxFilm.addItem("Beispiel 2");
		dropBoxFilm.addItem("Beispiel 3");
		content.add(dropBoxFilm);
		content.add(kino);
		dropBoxKino.addItem("Central und Union");
		dropBoxKino.addItem("UFA Palast");
		dropBoxKino.addItem("Massetheater");
		content.add(dropBoxKino);
		content.add(spielzeit);
		dropBoxSpielzeit.addItem("19:00");
		dropBoxSpielzeit.addItem("19:30");
		dropBoxSpielzeit.addItem("20:00");
		dropBoxSpielzeit.addItem("21:00");
		dropBoxSpielzeit.addItem("22:00");
		dropBoxSpielzeit.addItem("23:00");
		content.add(dropBoxSpielzeit);
		content.add(safeUmfrage);
		closeButton.addClickHandler(closeHandler);
		
		
		this.add(content);
		
		namenBox.getElement().setPropertyString("placeholder", "Umfragenname ...");

	   
		
	}
	
	public void umfrageEditForm() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();
	}
	
	public void closeUmfrageOpenForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
	
	/*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */
    
    	
	
	/**
	 * CloseUmfrageOpenFormClickHandler: Wird beim Click auf <code> closeButton </code> ausgelöst.
	 * Der User kann damit das Umfragefenster schließen.
	 */

	class CloseUmfrageOpenFormClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			closeUmfrageOpenForm();
			
		}
		
		
		
		
	}
	
	//Bisher nur Testweise Window Alert
	
	/**
	 * safehandler: Wird beim Click auf <code> safeUmfrage </code> Button ausgelöst.
	 * Der User kann damit die Umfrage speichern.
	 */
	private class safehandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
				Window.alert("Neue Umfrage erstellt!");
		}
		

	}

}
