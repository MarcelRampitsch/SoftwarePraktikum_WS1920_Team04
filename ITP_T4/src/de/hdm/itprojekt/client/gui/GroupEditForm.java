package de.hdm.itprojekt.client.gui;

import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.gui.admin.AdminForm;
import de.hdm.itprojekt.client.gui.admin.PresentationForm.searchClickHandler;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.User;

public class GroupEditForm extends VerticalPanel {

	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	User user = null;
	Vector <User> groupMember = new Vector<User>();
	Vector <Groupmember> deleteMember = new Vector<Groupmember>();
	int p = 0;
	Group g =null;
	Group group =null;
	Vector <Groupmember> member = null;
	
	List <Group> Gruppen = null;
	
	Label groupName = new Label("Gruppenname");
	TextBox groupBox = new TextBox();
	Label memberNames = new Label("Gruppenmitglieder");
	TextBox memberBox = new TextBox();
	Button search = new Button("Add"); 
	Button delete = new Button("X");
	ListBox memberList = new ListBox();
	Button save = new Button("save");
	HorizontalPanel searchPanel = new HorizontalPanel();
	HorizontalPanel listPanel = new HorizontalPanel();
	VerticalPanel main = new VerticalPanel();
	
	Button back = new Button("<--");
	
	public GroupEditForm(User user, Group g, Vector<User> groupMember, Vector<Groupmember> member) {
		this.user = user;
		this.g = g;
		this.groupMember = groupMember;
		this.member = member;
	}
	
	public GroupEditForm(int p, User user, Group g, Vector<Groupmember> deleteMember , Vector<User> groupMember) {
		this.p = p;
		this.user = user;
		this.g = g;
		this.groupMember = groupMember;
		this.deleteMember = deleteMember;
	}
	
	public GroupEditForm() {
	}
	
	public void onLoad() {
		super.onLoad();
		
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
		
		search.addClickHandler(new searchClickHandler());
		delete.addClickHandler(new deleteClickHandler());
		save.addClickHandler(new saveClickHandler());
		
		memberList.setVisibleItemCount(10);
		for(int i = 0; i<groupMember.size(); i++) {
			memberList.addItem(groupMember.elementAt(i).getNickname());
		}
		this.add(main);
	}
	
	 private class searchClickHandler implements ClickHandler{
			

			@Override
			public void onClick(ClickEvent event) {

				User name = new User(memberBox.getText(),"");

				editorAdministration.getUserByNickname(name, new AsyncCallback<User>() {
					
					@Override
					public void onSuccess(User result) {
						int j = 0;
						for(int i = 0;i < groupMember.size();i++) {
							if(groupMember.elementAt(i).getId() == result.getId()) {
							j = 1;
							}}
						if(j!=1) {
						groupMember.add(result);
						main.clear();
						GroupEditForm form = new GroupEditForm(1,user, g, deleteMember ,groupMember);
						RootPanel.get().add(form);}
						else {
						Window.alert("Der User ist bereits vorhanden");}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("etwas ist schief gelaufen");
						
					}
				});
			}
		}
	 
	 private class deleteClickHandler implements ClickHandler{
			
			@Override
			public void onClick(ClickEvent event) {
				groupMember.remove(memberList.getSelectedIndex());
				memberList.removeItem(memberList.getSelectedIndex());
			}
		}
	 
	 
	 private class saveClickHandler implements ClickHandler{
			
			@Override
			public void onClick(ClickEvent event) {
								
				for(int i = 0; i < groupMember.size(); i++) {
					Groupmember gm = new Groupmember(g.getId(), groupMember.elementAt(i).getId());
					editorAdministration.createGroupmember(gm, new AsyncCallback<Groupmember>() {
						
						@Override
						public void onFailure(Throwable caught) {
						Window.alert("was ist falsch gelaufen");
						}

						@Override
						public void onSuccess(Groupmember result) {
						}});
				}
				
				group = new Group(g.getId(), g.getCreationDate(), groupBox.getText(),g.getUserID());
				editorAdministration.updateGroup(group, new AsyncCallback<Group>() {
					
					@Override
					public void onFailure(Throwable caught) {
					Window.alert("was ist falsch gelaufen");
					}

					@Override
					public void onSuccess(Group result) {
					RootPanel.get().clear();
					EditorForm editorform = new EditorForm(user,Gruppen);
					RootPanel.get().add(editorform);
					}});
				
				
			}
		}
	 private class backHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			EditorForm form = new EditorForm(user, Gruppen);
			RootPanel.get().add(form);
		}
		 
		 
	 }
}
