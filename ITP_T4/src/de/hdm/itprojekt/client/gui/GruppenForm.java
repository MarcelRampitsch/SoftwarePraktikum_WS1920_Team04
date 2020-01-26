package de.hdm.itprojekt.client.gui;

import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.client.ClientSideSettings;

import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author serhatulus, DominikThumm
 * BasisKlasse für GruppenOpenForm. Diese Klasse wird in dieser Klasse instanziiert. 
 */

public class GruppenForm extends VerticalPanel {
	
	

	/**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>EditorAdministration</code>.
	   */
	  EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	  
	  
	  //Initalisierung relevanter Variablen, Widgets und ListDataProvider
	  User user = null;
	  ListDataProvider <Group> dataProvider;	
	  List <Group> Gruppen;
	  private Vector<User> groupMember = new Vector<User>(); 
	  String gruppname;
	  
	  VerticalPanel inhalt = new VerticalPanel();
	  HorizontalPanel suche = new HorizontalPanel();
	  HorizontalPanel liste = new HorizontalPanel();
	  
	  
	
	  Label gruppenerstellung = new Label("Gruppenerstellung:");
	  Label gruppenname = new Label("Gruppenname");
	  Label nickname = new Label("Nickname");
	  Label gruppenmitglieder = new Label("Gruppenmitglieder:");
	
	  Button back  = new Button("<--");
	
	  TextBox gruppennamebox = new TextBox();
	  TextBox nicknamebox = new TextBox();
	  
	  ListBox mitglieder = new ListBox();
	  
	  Button hinzufuegen = new Button("+");
	  Button loeschen = new Button("X");
	  Button speichern = new Button("sichern");
	
	  GruppenForm gruppenForm = null;
	
	  //Erstellung der GroupViewForm constructor
	  public GruppenForm(User user, Vector<User> groupMember) {
		  this.user=user;
		  this.groupMember = groupMember;
	  }
	
	  public GruppenForm(User user, Vector<User> groupMember, String gruppname) {
		  this.user=user;
		  this.groupMember = groupMember;
		  this.gruppname = gruppname;
	  }
	
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Widget, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Widget hinzugefügt.
	 * Den Buttons werden deren Funktion entsprechend ClickHandler zugewiesen. 
	 */
	
	public void onLoad() {
		super.onLoad();
		
		//Aufbau des "GruppenForm" Widgets
		inhalt.add(back);
		back.addStyleName("backButtonStyle");
		back.addClickHandler(new backButtonHandler());
		inhalt.add(gruppenerstellung);
		
		inhalt.add(gruppenname);
		inhalt.add(gruppennamebox);
		gruppennamebox.setText(gruppname);
		
		inhalt.add(nickname);
		suche.add(nicknamebox);
		suche.add(hinzufuegen);
		hinzufuegen.addStyleName("addButtonStyle");
		hinzufuegen.addClickHandler(new hinzufuegenHandler());
		inhalt.add(suche);
		
		liste.add(mitglieder);
		liste.add(loeschen);
		loeschen.addStyleName("deleteButtonStyle");
		loeschen.addClickHandler(new loeschenHandler());
		inhalt.add(liste);
		
		inhalt.add(speichern);
		speichern.addStyleName("saveButtonStyle");
		speichern.addClickHandler(new sichernhandler());
		
		mitglieder.setVisibleItemCount(groupMember.size());
		for(int i = 0; i < groupMember.size(); i++) {
			mitglieder.addItem(groupMember.elementAt(i).getNickname());
		}
		
		this.add(inhalt);
}

	
	//Löschen ClickHandler
	private class loeschenHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			//Wenn der in der Liste Ausgewählt User ungleich des Aktuellen User ist wirde er in einen Vector gespeichert
			if(user.getId() != groupMember.elementAt(mitglieder.getSelectedIndex()).getId()) {
			groupMember.remove(mitglieder.getSelectedIndex());
			mitglieder.removeItem(mitglieder.getSelectedIndex());
			}
			//Wenn der in der Liste Ausgewählt User gleich des Aktuellen User ist kommt eine FehlerMeldung
			else {
				Window.alert("You can't remove yourself from your Group");
			}
		}
	}

	//Hinzufügen ClickHandler
	private class hinzufuegenHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			User name = new User(nicknamebox.getText(),"");

			editorAdministration.getUserByNickname(name, new AsyncCallback<User>() {
				User u = null;
				@Override
				public void onSuccess(User result) {
					int j = 0;
					//Pruefen ob der User bereits im "groupMember" Vector vorhanden ist 
					for (int i = 0; i < groupMember.size(); i++) {
						//Wenn Ja dann setze j=1
						if(groupMember.elementAt(i).getId() == result.getId()) {
						j=1;	
						}
					}
					//Wenn j ungleich 1 wird der User dem groupMember Vector hinzugefügt
					if(j!=1) {
						u = result;
						groupMember.add(u);
						gruppname = gruppennamebox.getText();
						inhalt.clear();
						GruppenForm form = new GruppenForm(user, groupMember, gruppname);
						RootPanel.get().add(form);
					}
					//Wenn j = 0 Fehlerausgabe
					else {
						Window.alert("This user is already in this Group");
					}
					}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert("etwas ist schief gelaufen");
					
				}
			});
		}
	}
	
	//ClickHandler zurück zur Startseite
	private class backButtonHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			RootPanel.get().clear();
			EditorForm editform = new EditorForm(user, Gruppen);
			RootPanel.get().add(editform);
		}
	}
			
			
		
	//Clickhandler zum sichern der Group
	private class sichernhandler implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {	
			//Wenn Groupname nicht leer ist dann wird die Group erstellt
			if(gruppennamebox.getText()!=""){
			RootPanel.get().clear();
			
			Group group1 = new Group(user.getId(),gruppennamebox.getText());
			editorAdministration.createGroup(group1, new AsyncCallback<Group>(){
				
				@Override
				public void onFailure(Throwable caught) {
						Window.alert("was ist schief geloffen");
				}

				@Override
				public void onSuccess(Group result) {
					//Neuer Groupmember wird erzeugt und der Datenbank hinzugefügt
					for(int i = 0; i < groupMember.size(); i++) {
						Groupmember gm = new Groupmember(result.getId(), groupMember.elementAt(i).getId());
						editorAdministration.createGroupmember(gm, new AsyncCallback<Groupmember>() {
							
							@Override
							public void onFailure(Throwable caught) {
							Window.alert("was ist falsch gelaufen");
							}

							@Override
							public void onSuccess(Groupmember result) {
							}});
					}
					//EditorForm wird aufgerufen und die neue Gruppe wird dem dataProvider hinzugefügt
					EditorForm editform = new EditorForm(user, Gruppen);
					RootPanel.get().add(editform);
					List <Group> liste = dataProvider.getList();
					liste.add(group1);
					Window.alert("EINGABE GESICHERT");
				}
				
			});
		}
			//Wenn kein Groupname dann wird dieser Fehler ausgegebne
			else {
				Window.alert("Please enter a groupname");
			}
	}
	}
}


	


