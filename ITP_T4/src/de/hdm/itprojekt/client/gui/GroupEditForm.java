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
	Group g =null;
	Group group =null;
	int index = 0;
	
	List <Group> Gruppen = null;
	
	Label groupName = new Label("Gruppenname");
	TextBox groupBox = new TextBox();
	Label memberNames = new Label("Gruppenmitglieder");
	TextBox memberBox = new TextBox();
	Button search = new Button("Add"); 
	Button delete = new Button("X");
	ListBox memberList = new ListBox();
	Button save = new Button("save");
	HorizontalPanel buttonMember = new HorizontalPanel();
	HorizontalPanel buttonForm = new HorizontalPanel();
	VerticalPanel main = new VerticalPanel();
	
	Button back = new Button("<--");
	
	public GroupEditForm(User user, Group g) {
		this.user = user;
		this.g = g;
	}
	
	public GroupEditForm(User user, Group g, Vector<User> groupMember) {
		this.user = user;
		this.g = g;
		this.groupMember = groupMember;
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
		main.add(memberBox);
		main.add(buttonMember);
		main.add(memberList);
		main.add(buttonForm);
		buttonMember.add(search);
		buttonMember.add(delete);
		buttonForm.add(save);
		groupBox.setText(g.getName());
		
		search.addClickHandler(new searchClickHandler());
		delete.addClickHandler(new deleteClickHandler());
		save.addClickHandler(new saveClickHandler());
		
		this.add(main);
		memberList.setVisibleItemCount(groupMember.size());
		for(int i = 0; i<groupMember.size(); i++) {
			memberList.addItem(groupMember.elementAt(i).getNickname());
		}
		
	}
	
	 private class searchClickHandler implements ClickHandler{
			

			@Override
			public void onClick(ClickEvent event) {

				User name = new User(memberBox.getText(),"");

				editorAdministration.getUserByNickname(name, new AsyncCallback<User>() {
					
					@Override
					public void onSuccess(User result) {
						groupMember.add(result);
						main.clear();
						GroupEditForm form = new GroupEditForm(user, g ,groupMember);
						RootPanel.get().add(form);
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
			}
		}
	 
	 private class closeClickHandler implements ClickHandler{
			
			@Override
			public void onClick(ClickEvent event) {
			}
		}
	 
	 private class saveClickHandler implements ClickHandler{
			
			@Override
			public void onClick(ClickEvent event) {
						
				
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