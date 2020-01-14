package de.hdm.itprojekt.client.gui;

import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
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
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
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
	Vector<SurveyEntry> surveyEntry = new Vector<SurveyEntry>();
	Group group = null;
	Survey survey = null;
	
	List<Group> Gruppen = null;
	
	Label groupLabel = new Label("Gruppen");
	Label groupNameLabel = new Label("Gruppenname:");
	TextBox groupNameTB = new TextBox();
	Label memberNameLabel = new Label("Mitglieder:");
	TextBox memberNameTB = new TextBox();
	Label surveyNameLabel = new Label("Umfragen:");
	TextBox surveyNameTB = new TextBox();
	Button newSurveyButton = new Button("Neue Umfrage erstellen");
	Button backButton = new Button("zurück");
	
	ListBox memberLB = new ListBox();
	HorizontalPanel groupNamePanel = new HorizontalPanel();
	HorizontalPanel memberNamePanel = new HorizontalPanel();
	HorizontalPanel surveyPanel = new HorizontalPanel();
	VerticalPanel contentPanel = new VerticalPanel();
	
	List<Survey> Umfragen;
	
	public GroupViewForm(User user, Group group, Vector<User> groupMember) {
		
		this.user = user;
		this.group = group;
		this.groupMember = groupMember;
		
	}
	
	public GroupViewForm(User user, Survey survey, Vector<SurveyEntry> surveyEntry) {
		
		this.user = user;
		this.survey = survey;
		this.surveyEntry = surveyEntry;
		
	}
	
	public GroupViewForm() {
		
	}
	
	public void onLoad() {
		
		super.onLoad();
		
//		this.clear();
		
		Grid gvfGrid = new Grid(4, 3);
		this.add(gvfGrid);
		
		gvfGrid.setWidget(0, 0, groupLabel);
		gvfGrid.setWidget(0, 1, backButton);
		gvfGrid.setWidget(1, 0, groupNameLabel);
		gvfGrid.setWidget(1, 1, groupNameTB);
		gvfGrid.setWidget(2, 0, memberNameLabel);
		gvfGrid.setWidget(2, 1, memberNameTB);
		gvfGrid.setWidget(3, 0, surveyNameLabel);
		gvfGrid.setWidget(3, 1, surveyNameTB);
		gvfGrid.setWidget(3, 2, newSurveyButton);
		
		backButton.addClickHandler(new backButtonClickHandler());
		newSurveyButton.addClickHandler(new newSurveyButtonClickHandler());
		
//		groupNamePanel.add(groupNameLabel);
//		groupNamePanel.add(groupNameTB);
//		memberNamePanel.add(memberNameLabel);
//		memberNamePanel.add(memberNameTB);
//		surveyPanel.add(surveyNameLabel);
//		surveyPanel.add(surveyNameTB);
//		surveyPanel.add(newSurveyButton);
//		contentPanel.add(groupNamePanel);
//		contentPanel.add(memberNamePanel);
//		contentPanel.add(surveyPanel);

//		groupNameTB.setText(group.getName()); hier funktioniert was nicht!!!


		
//		this.add(contentPanel);
		
//		final CellTable<Survey> table = new CellTable<Survey>();
		
//		ListDataProvider<Survey> dataProvider = new ListDataProvider<Survey>();
		
	}
	
	private class backButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			RootPanel.get().clear();
			EditorForm editorForm = new EditorForm(user, Gruppen);
			RootPanel.get().add(editorForm);
			
		}	
		
	}
	
	private class newSurveyButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			Window.alert("Test");
			
		}
		
	}
	
	
}
