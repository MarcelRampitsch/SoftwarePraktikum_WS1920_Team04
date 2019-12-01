package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author serhatulus
 * DialogBox, die angezeigt wird, wenn der Nutzer eine Gruppe erstellen möchte.
 * Die Klasse enthält entsprechende ClickHandler & Methoden zum Bestätigen oder Abbrechen der Aktion.
 */
 	
	public class GruppenOpenForm extends DialogBox{
		

		 private VerticalPanel inhalt = new VerticalPanel();
		
		
		 private Label gruppenerstellung = new Label("Gruppenerstellung:");
		 private Label gruppenname = new Label("Gruppenname");
		 private Label nickname = new Label("Nickname");
		
		 private Button edit = new Button("editieren");
		 private Button close  = new Button("X");
		
		 private TextBox gruppennamebox =new TextBox();
		 private TextBox nicknamebox = new TextBox();
		
		 private Button speichern = new Button("sichern");
		
		//TextArea ta = new TextArea();

		
		
		public GruppenOpenForm() {
			
		}
		
		
		/*
		 * onLoad-Methode: Wird ausgeführt, wenn das Widget, dem Browser hinzugefügt wurde. 
		 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Widget hinzugefügt.
		 * Den Buttons werden deren Funktion entsprechend ClickHandler zugewiesen. 
		 */
		
		public void onLoad() {
			super.onLoad();
			
			inhalt.add(close);
			close.addClickHandler(new closegruppenform());
			inhalt.add(gruppenerstellung);
			gruppenerstellung.addStyleName("Überschrift");
			inhalt.add(gruppenname);
			inhalt.add(gruppennamebox);
			
			inhalt.add(nickname);
			inhalt.add(nicknamebox);
			
			inhalt.add(speichern);
			inhalt.add(edit);
						
			
			speichern.addClickHandler(new sichernhandler());
			this.add(inhalt);

			
	}
		
		
		public void openGruppenForm() {
			this.setGlassEnabled(true);
			this.setAnimationEnabled(true);
			this.center();
			this.show();	
		}
		
		
		public void closeGruppenForm() {
			this.hide();
			this.clear();
			this.removeFromParent();
			this.setAnimationEnabled(false);
			this.setGlassEnabled(false);
		}

	
		
		
		private class closegruppenform implements ClickHandler{
	
			@Override
			public void onClick(ClickEvent event) {
				closeGruppenForm();
				
			}
	
		
			
		}
		
		private class sichernhandler implements ClickHandler{
	
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("EINGABE GESICHERT");
			}
			
	}
	}

		




