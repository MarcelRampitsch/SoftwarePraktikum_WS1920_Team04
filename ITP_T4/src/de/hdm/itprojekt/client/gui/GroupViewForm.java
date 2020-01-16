package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
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
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.User;


/*
 * 
 * @author VanDuyHo
 * 
 */

public class GroupViewForm extends VerticalPanel {

	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	
	User user = null;
	Vector<User> groupMember = new Vector<User>();
	Vector<Groupmember> member = new Vector<Groupmember>();
	Group group = null;
	
	List<Survey> Umfragen;
	
	Button back = new Button("<--");
	Label groupNameLabel = new Label();
	Label memberNamesLabel = new Label("Mitglieder:");
	Label surveyNameLabel = new Label("Umfrage:");
	Button newSurveyButton = new Button("+");
	CellTable<Survey> table = new CellTable<Survey>();
	
	ListBox memberLB = new ListBox();
	VerticalPanel mainPanel = new VerticalPanel();
	HorizontalPanel umfragePanel = new HorizontalPanel();
	
	public GroupViewForm(User user, Group group, Vector<User> groupMember) {
		
		this.user = user;
		this.group = group;
		this.groupMember = groupMember;
		
	}
	
	public GroupViewForm(User user, Group group, List<Survey> Umfragen) {
		
		this.user = user;
		this.group = group;
		this.Umfragen = Umfragen;
	}
	
	public GroupViewForm() {
	}
	
	public void onLoad() {
		
		super.onLoad();
		buildForm();
		
		
		editorAdministration.getAllGroupmemberByGroupID(group, new AsyncCallback<Vector<Groupmember>>() {
			
			@Override
			public void onSuccess(Vector<Groupmember> result) {
				member=result;
				
			for(int i = 0; i< member.size(); i++) {
				User u = new User(member.elementAt(i).getUserID());
				editorAdministration.getUserByUserID(u, new AsyncCallback<User>() {
					User u = null;
					@Override
					public void onSuccess(User result) {
					u = result;
					groupMember.add(u);
					memberLB.addItem(result.getNickname());
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("etwas ist schief gelaufen");
					}
					
				});
			}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("etwas ist schief gelaufen");	
			}			
		});
		
		ListDataProvider<Survey> dataProvider = new ListDataProvider<Survey>();
		
		TextColumn<Survey> nameColumn = new TextColumn<Survey>() {

			@Override
			public String getValue(Survey object) {
				// TODO Auto-generated method stub
				return object.getName();
			}
		};
			
		Cell<String> loeschenCell = new ButtonCell();	
			
		Column<Survey, String> loeschenColumn = new Column<Survey, String>(loeschenCell) {

			@Override
			public String getValue(Survey object) {
				// TODO Auto-generated method stub
				return "X";
			}
		};
		
		table.addColumn(nameColumn);
		table.addColumn(loeschenColumn);
		
		dataProvider.addDataDisplay(table);
		
		final List <Survey> list = dataProvider.getList();
		for(Survey survey: Umfragen) {
			list.add(survey);
		}		
		memberLB.setVisibleItemCount(2);
		}
	
	public void buildForm() {
		
		this.clear();
		mainPanel.add(back);
		back.addClickHandler(new backHandler());
		mainPanel.add(groupNameLabel);
		groupNameLabel.setText(group.getName());
		mainPanel.add(memberNamesLabel);
		mainPanel.add(memberLB);
		umfragePanel.add(surveyNameLabel);
		umfragePanel.add(newSurveyButton);
		mainPanel.add(umfragePanel);
		newSurveyButton.addClickHandler(new newSurveyButtonClickHandler());
		mainPanel.add(table);
		this.add(mainPanel);
		
	}
	
	private class newSurveyButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			mainPanel.clear();
			NewSurveyForm nsf = new NewSurveyForm(user,group);
			RootPanel.get().add(nsf);
			
		}
	}
	
	private class backHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			EditorForm form = new EditorForm(user);
			RootPanel.get().add(form);
		}
	 }
	
}
