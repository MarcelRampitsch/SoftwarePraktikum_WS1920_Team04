package de.hdm.itprojekt.client.gui;

import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;

import de.hdm.itprojekt.client.ClientSideSettings;

import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author serhatulus, DominikThumm
 * BasisKlasse für GruppenOpenForm. Diese Klasse wird in dieser Klasse instanziiert. 
 */

public class GruppenForm extends VerticalPanel {
	
	

	
	  EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	
	
	  ListDataProvider <Group> dataProvider;	

	  VerticalPanel inhalt = new VerticalPanel();
	  
	  List <Group> Gruppen;
	
	  Label gruppenerstellung = new Label("Gruppenerstellung:");
	  Label gruppenname = new Label("Gruppenname");
	  Label nickname = new Label("Nickname");
	
	  Button edit = new Button("editieren");
	  Button back  = new Button("<--");
	
	  TextBox gruppennamebox =new TextBox();
	  TextBox nicknamebox = new TextBox();
	
	  Button speichern = new Button("sichern");
	
	  GruppenForm gruppenForm = null;

	  User user;


	
	
	public GruppenForm(User user) {
		this.user=user;
	}
	
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Widget, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Widget hinzugefügt.
	 * Den Buttons werden deren Funktion entsprechend ClickHandler zugewiesen. 
	 */
	
	public void onLoad() {
		super.onLoad();
		

		
		inhalt.add(back);
		back.addClickHandler(new backButtonHandler());
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

	



	
	
	private class backButtonHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			RootPanel.get().clear();
			EditorForm editform = new EditorForm(user, Gruppen);
			RootPanel.get().add(editform);
		}
	}
			
			
		
	
	private class sichernhandler implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {	
			RootPanel.get().clear();
			
			Group group1 = new Group(1,gruppennamebox.getText());
			editorAdministration.createGroup(group1, new AsyncCallback<Group>(){

				@Override
				public void onFailure(Throwable caught) {
						Window.alert("was ist schief geloffen");
				}

				@Override
				public void onSuccess(Group result) {
					
					EditorForm editform = new EditorForm(user, Gruppen);
					RootPanel.get().add(editform);

					List <Group> liste = dataProvider.getList();
					liste.add(group1);

					
					Window.alert("EINGABE GESICHERT");
					
					
				}
				
			});
			
		
		
}
	}
	}


	


