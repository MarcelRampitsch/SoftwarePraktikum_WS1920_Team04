package de.hdm.itprojekt.client.gui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;


/**
 * 
 * @author serhatulus, DominikThumm
 * DialogBox, die angezeigt wird, wenn der Nutzer eine Gruppe erstellen möchte.
 * Die Klasse enthält entsprechende ClickHandler & Methoden zum Bestätigen oder Abbrechen der Aktion.
 */
 	
	public class GruppenOpenForm extends DialogBox{
		
		/**
		   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
		   * namens <code>EditorAdministration</code>.
		   */
		  EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
		  
		  //Initalisierung relevanter Variablen, Widgets und ListDataProvider	
		  ListDataProvider <Group> dataProvider;	
		  VerticalPanel inhalt = new VerticalPanel();
		  Label gruppenerstellung = new Label("Group creation:");
		  Label gruppenname = new Label("Groupname");
		  Label nickname = new Label("Nickname");
		  Button edit = new Button("edit");
		  Button close  = new Button("X");
		  TextBox gruppennamebox =new TextBox();
		  TextBox nicknamebox = new TextBox();
		  Button speichern = new Button("save");
		  GruppenForm gruppenForm = null;
		  User user;
		
		  //Erstellung der GroupViewForm constructor
		  public GruppenOpenForm() {
		  }
		
		/*
		 * onLoad-Methode: Wird ausgeführt, wenn das Widget, dem Browser hinzugefügt wurde. 
		 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Widget hinzugefügt.
		 * Den Buttons werden deren Funktion entsprechend ClickHandler zugewiesen. 
		 */
		
		public void onLoad() {
			super.onLoad();
			
			//Aufbau des Widgets
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
		
		/*
		 * Dialog-spezifische Methode mit der sich eine DialogBox öffnen lässt
		 */
		public void openGruppenForm() {
			this.setGlassEnabled(true);
			this.setAnimationEnabled(true);
			this.center();
			this.show();	
		}
		
		
		/*
		 * Dialog-spezifische Methode mit der sich eine DialogBox schließen lässt
		 */
		public void closeGruppenForm() {
			this.hide();
			this.clear();
			this.removeFromParent();
			this.setAnimationEnabled(false);
			this.setGlassEnabled(false);
		}
		
		
		//ClickHandler mit der sich ein DialogFenster schließen lässt
		private class closegruppenform implements ClickHandler{
	
			@Override
			public void onClick(ClickEvent event) {
				closeGruppenForm();	
			}
		}
		
		//ClickHandler der beim Anklicken eine neue Gruppe erstellt
		private class sichernhandler implements ClickHandler{
			
			@Override			
			public void onClick(ClickEvent event) {
				//Gruppe wird erstellt
				Group group1 = new Group(1,gruppennamebox.getText());
				//Erstellt Gruppe in Datenbank speichern
				editorAdministration.createGroup(group1, new AsyncCallback<Group>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Group result) {
						
						List <Group> liste = dataProvider.getList();
						liste.add(group1);
						Window.alert("EINGABE GESICHERT");
						
						
					}
					
				});
				
			}
			
	}

		
	}



