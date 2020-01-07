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
import de.hdm.itprojekt.client.gui.admin.PresentationForm.searchClickHandler;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;

public class GroupEditForm extends VerticalPanel {

	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	User user = null;
	Vector <User> groupMember = null;
	Group g;
	int index = 0;
	
	List <Group> Gruppen = null;
	
	Label groupName = new Label("Gruppenname");
	TextBox groupBox = new TextBox();
	Label memberNames = new Label("Gruppenmitglieder");
	TextBox memberBox = new TextBox();
	Button search = new Button("Add"); 
	Button delete = new Button("X");
	ListBox memberList = new ListBox();
	Button x = new Button("X");
	Button save = new Button("save");
	HorizontalPanel buttonMember = new HorizontalPanel();
	HorizontalPanel buttonForm = new HorizontalPanel();
	VerticalPanel main = new VerticalPanel();
	
	Button back = new Button("<--");
	
	public GroupEditForm(User user, Group g) {
		this.user = user;
		this.g = g;
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
		buttonForm.add(x);
		buttonForm.add(save);
		groupBox.setText(g.getName());
		
		search.addClickHandler(new searchClickHandler());
		delete.addClickHandler(new deleteClickHandler());
		x.addClickHandler(new closeClickHandler());
		save.addClickHandler(new saveClickHandler());
		
		this.add(main);
		
	}
	
	 private class searchClickHandler implements ClickHandler{
			
			@Override
			public void onClick(ClickEvent event) {
				User name = new User(memberBox.getText(),"a");

				editorAdministration.getUserByNickname(name, new AsyncCallback<User>() {
					
					@Override
					public void onSuccess(User result) {
						memberList.setItemText(index, result.getNickname());
						groupMember.add(result);
						index++;
						Window.alert(result.getNickname());
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
