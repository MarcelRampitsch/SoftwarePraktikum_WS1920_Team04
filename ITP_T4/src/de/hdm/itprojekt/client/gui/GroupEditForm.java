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

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.User;

public class GroupEditForm extends VerticalPanel {

	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	
	//Initalisierung relevanter Variablen
	User user = null;
	Vector <User> groupMember = null;
	Vector <User> newMember = new Vector<User>();
	Vector <Groupmember> deleteMember = new Vector<Groupmember>();
	int j = 0;
	Group g = null;
	Group group = null;
	Vector <Groupmember> member = new Vector<Groupmember>();
	List <Group> Gruppen = null;
	
	//Initalisierung relevanter Variablen
	Label groupName = new Label("Groupname");
	TextBox groupBox = new TextBox();
	Label memberNames = new Label("Groupmember");
	TextBox memberBox = new TextBox();
	Button search = new Button("+"); 
	Button delete = new Button("X");
	ListBox memberList = new ListBox();
	Button save = new Button("save");
	HorizontalPanel searchPanel = new HorizontalPanel();
	HorizontalPanel listPanel = new HorizontalPanel();
	VerticalPanel main = new VerticalPanel();
	Button back = new Button("<--");
	
	// Erstellung der <code>GroupEditForm</code> Constructoren
	public GroupEditForm(User user, Group g, Vector<User> groupMember, Vector<Groupmember> member) {
		this.user = user;
		this.g = g;
		this.groupMember = groupMember;
		this.member = member;
	}
	
	public GroupEditForm(User user, Group g, Vector<Groupmember> deleteMember , Vector<User> groupMember, Vector<User> newMember, Vector<Groupmember> member) {
		this.user = user;
		this.g = g;
		this.groupMember = groupMember;
		this.deleteMember = deleteMember;
		this.newMember = newMember;
		this.member = member; 
	}
	
	public GroupEditForm() {
	}
	
	public void onLoad() {
		
		super.onLoad();
		
		// Aufbau des GroupEditForm
		main.add(back);
		back.addClickHandler(new backHandler());
		
		main.add(groupName);
		main.add(groupBox);
		main.add(memberNames);
		searchPanel.add(memberBox);
		main.add(searchPanel);
		listPanel.add(memberList);
		main.add(listPanel);
		searchPanel.add(search);
		listPanel.add(delete);
		main.add(save);
		groupBox.setText(g.getName());
		
		//Hinzufuegen der ClickHandler
		search.addClickHandler(new searchClickHandler());
		delete.addClickHandler(new deleteClickHandler());
		save.addClickHandler(new saveClickHandler());
		
		//Befuellen der memberList
		memberList.setVisibleItemCount(10);
		for(int i = 0; i<groupMember.size(); i++) {
			memberList.addItem(groupMember.elementAt(i).getNickname());
		}
		this.add(main);
	}
	
	//ClickHandler um User der Gruppe hinzuzufügen
	 private class searchClickHandler implements ClickHandler{
			
			@Override
			public void onClick(ClickEvent event) {
				User name = new User(memberBox.getText(),"");
				//User in der Datenbank finden
				editorAdministration.getUserByNickname(name, new AsyncCallback<User>() {
				
					@Override
					public void onSuccess(User result) {
						int j = 0;
						//Wenn der Nutzer bereits in der Gruppe ist wird der int j auf 1 gesetzt
						for(int i = 0;i < groupMember.size();i++) {
							if(groupMember.elementAt(i).getId() == result.getId()) {
							j = 1;
							}}
						//Wenn j != 1 wird der user der Gruppe hinzugefügt
						if(j!=1) {
						groupMember.add(result);
						newMember.add(result);
						main.clear();
						GroupEditForm form = new GroupEditForm(user, g, deleteMember ,groupMember, newMember, member);
						RootPanel.get().add(form);}
						//Sonst kommt die Meldung das der User bereits vorhanden ist
						else {
						Window.alert("This User is already part of this group");}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("etwas ist schief gelaufen");
						
					}
				});
			}
		}
	 
	 //ClickHandler zum löschen eines ausgewählten nutzers
	 private class deleteClickHandler implements ClickHandler{
			
			@Override
		public void onClick(ClickEvent event) {
		//Wenn das ausgewählte User objekt die gleiche Id wie der eingelogte User hat wird eine Fehlermeldung ausgegeben
			if(groupMember.elementAt(memberList.getSelectedIndex()).getId()==user.getId()) {
				Window.alert("You can not delete yourself from your group");
			}
			else {
			// Wenn der User schon in der Liste war beim ersten Laden wird diese aus der Liste gelöscht
				if(memberList.getSelectedIndex()<member.size()) {
					deleteMember.add(member.elementAt(memberList.getSelectedIndex()));
					member.remove(memberList.getSelectedIndex());
					memberList.removeItem(memberList.getSelectedIndex());
					groupMember.remove(memberList.getSelectedIndex());
				}
				else {
				// Wenn der User beim ersten Laden nicht in der Liste war wird er aus dem new Member Vector gelöscht
					newMember.remove(memberList.getSelectedIndex()-member.size());
					memberList.removeItem(memberList.getSelectedIndex());
				}
			}	
		}
	}
	 
	 //ClickHandeler zum speichern der änderungen
	 private class saveClickHandler implements ClickHandler{
			
		@Override
		public void onClick(ClickEvent event) {
			//Wenn die groupBox leer ist 
			if(groupBox.getText()!="") {
			//Dann wird der Vector "deleteMember" durchgegangen
			for(int j = 0; j < deleteMember.size(); j++) {
				//Jeder User des deleteMember wird gelöscht
				editorAdministration.deleteGroupmemberByGroupmemberID(deleteMember.elementAt(j), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub				
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
					}
				});
			}
			//Der Vector "newMember" wird durchgegangen
			for(int i = 0; i < newMember.size(); i++) {
				//Ein neuer Groupmember wird erstellt mit dem User des "newMember" Vector
				Groupmember gm = new Groupmember(g.getId(), newMember.elementAt(i).getId());
				//Der Groupmember "gm" wird in der Datenbank gespeichert
				editorAdministration.createGroupmember(gm, new AsyncCallback<Groupmember>() {
						
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("was ist falsch gelaufen");
					}

					@Override
						public void onSuccess(Groupmember result) {
					}
				});
			}
			// Eine neue group wird erstellt mit dem neuen namen 
			group = new Group(g.getId(), g.getCreationDate(), groupBox.getText(),g.getUserID());
			editorAdministration.updateGroup(group, new AsyncCallback<Group>() {
					
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("was ist falsch gelaufen");
				}

				@Override
				public void onSuccess(Group result) {
					//Das RootPanel wird gelöscht
					RootPanel.get().clear();
					//Die EditorForm wird erstellt und dem RootPanel hinzugefuegt
					EditorForm editorform = new EditorForm(user,Gruppen);
					RootPanel.get().add(editorform);
				}
			});
		}
			//Wenn die Groupbox leer ist wird diese Meldung ausgegeben
			else {
				Window.alert("Pleas enter a groupname:");
			}
			}
		}
	 
	 //ClickHandler um zu Startseite zu kommen
	 private class backHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			EditorForm form = new EditorForm(user, Gruppen);
			RootPanel.get().add(form);	
		}
	 }
}
