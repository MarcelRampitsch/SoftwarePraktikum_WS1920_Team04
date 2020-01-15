package de.hdm.itprojekt.client.gui;

import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
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
import com.google.gwt.view.client.SelectionModel;

import de.hdm.itprojekt.client.ClientSideSettings;

import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author serhatulus, DominikThumm
 * BasisKlasse fÃ¼r GruppenOpenForm. Diese Klasse wird in dieser Klasse instanziiert. 
 */

public class GruppenForm extends VerticalPanel {
	
	

	
	  EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	
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
	  
	  Button hinzufügen = new Button("+");
	  Button löschen = new Button("X");
	  Button speichern = new Button("sichern");
	
	  GruppenForm gruppenForm = null;
	
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
	 * onLoad-Methode: Wird ausgefÃ¼hrt, wenn das Widget, dem Browser hinzugefÃ¼gt wurde. 
	 * Die dieser Klasse dazugehÃ¶rigen grafischen Elemente werden dem Widget hinzugefÃ¼gt.
	 * Den Buttons werden deren Funktion entsprechend ClickHandler zugewiesen. 
	 */
	
	public void onLoad() {
		super.onLoad();
		
		inhalt.add(back);
		back.addClickHandler(new backButtonHandler());
		inhalt.add(gruppenerstellung);
		
		inhalt.add(gruppenname);
		inhalt.add(gruppennamebox);
		gruppennamebox.setText(gruppname);
		
		inhalt.add(nickname);
		suche.add(nicknamebox);
		suche.add(hinzufügen);
		hinzufügen.addClickHandler(new hinzufuegenHandler());
		inhalt.add(suche);
		
		liste.add(mitglieder);
		liste.add(löschen);
		löschen.addClickHandler(new loeschenHandler());
		inhalt.add(liste);
		
		inhalt.add(speichern);		
		speichern.addClickHandler(new sichernhandler());
		
		mitglieder.setVisibleItemCount(groupMember.size());
		for(int i = 0; i < groupMember.size(); i++) {
			mitglieder.addItem(groupMember.elementAt(i).getNickname());
		}
		
		this.add(inhalt);
}

	

	private class loeschenHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			if(user.getId() != groupMember.elementAt(mitglieder.getSelectedIndex()).getId()) {
			groupMember.remove(mitglieder.getSelectedIndex());
			mitglieder.removeItem(mitglieder.getSelectedIndex());
			}
			else {
				Window.alert("Sie kÃ¶nnen sich nicht aus der Gruppe entfernen");
			}
		}
	}

	
	private class hinzufuegenHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			User name = new User(nicknamebox.getText(),"");

			editorAdministration.getUserByNickname(name, new AsyncCallback<User>() {
				User u = null;
				@Override
				public void onSuccess(User result) {
					int j = 0;
					for (int i = 0; i < groupMember.size(); i++) {
						if(groupMember.elementAt(i).getId() == result.getId()) {
						j=1;	
						}
					}
					if(j!=1) {
						u = result;
						groupMember.add(u);
						gruppname = gruppennamebox.getText();
						inhalt.clear();
						GruppenForm form = new GruppenForm(user, groupMember, gruppname);
						RootPanel.get().add(form);
					}
					else {
						Window.alert("Nutzer ist bereits in der Gruppe");
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
					
					EditorForm editform = new EditorForm(user, Gruppen);
					RootPanel.get().add(editform);
					List <Group> liste = dataProvider.getList();
					liste.add(group1);
					Window.alert("EINGABE GESICHERT");
				}
				
			});
		}
			else {
				Window.alert("Please enter a groupname");
			}
	}
	}
}


	


