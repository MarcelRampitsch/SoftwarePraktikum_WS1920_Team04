package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import java.util.Arrays;
import java.util.Collections;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
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
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
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
	Vector<Groupmember> member = new Vector<Groupmember>();
	Vector<User> groupMember = new Vector<User>();
	Group group = null;
	
	List<Survey> Umfragen;
	List<SurveyEntry> UmfragenEintrag;
	
	Button back = new Button("<--");
	Label groupNameLabel = new Label();
	Button newSurveyButton = new Button("Neue Umfrage erstellen");
	Button editGroupButton = new Button("edit");
	Button deleteGroupButton = new Button("X");
	CellTable<Survey> table = new CellTable<Survey>();
	UmfragenCell umfragenCell = new UmfragenCell();
	CellList<SurveyEntry> cellList = new CellList<SurveyEntry>(umfragenCell);
	
	VerticalPanel mainPanel = new VerticalPanel();
	HorizontalPanel editPanel = new HorizontalPanel();
	
	ListDataProvider<Survey> dataProvider = new ListDataProvider<Survey>();
	ListDataProvider<SurveyEntry> surveyEntryProvider = new ListDataProvider<SurveyEntry>();
	
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
	
		TextColumn<Survey> nameColumn = new TextColumn<Survey>() {

			@Override
			public String getValue(Survey object) {
				// TODO Auto-generated method stub
				return object.getName();
			}
		};
			
		//Cell<String> loeschenCell = new ButtonCell();	
//			
//		Column<Survey, String> loeschenColumn = new Column<Survey, String>(loeschenCell) {
//
//			@Override
//			public String getValue(Survey object) {
//				// TODO Auto-generated method stub
//				return "X";
//			}
//		};
		
		table.addColumn(nameColumn, "Umfragename");
		//able.addColumn(loeschenColumn);
		
		NoSelectionModel<Survey> selectionModelSurvey = new NoSelectionModel<Survey>();
		Handler tableHandle = new SelectionChangeEvent.Handler() 
		{

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				Survey clickedObj = selectionModelSurvey.getLastSelectedObject();
				editorAdministration.getAllSurveyEntryBySurveyID(clickedObj, new AsyncCallback<Vector<SurveyEntry>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Vector<SurveyEntry> result) {
						UmfragenEintrag = Collections.list(result.elements());
						UmfragenTable form = new UmfragenTable(user , UmfragenEintrag, clickedObj);
						mainPanel.clear();
						RootPanel.get().add(form);
					}
				});
			}
		};

		selectionModelSurvey.addSelectionChangeHandler(tableHandle);
		table.setSelectionModel(selectionModelSurvey);
		
		dataProvider.addDataDisplay(table);
		surveyEntryProvider.addDataDisplay(cellList);
		
		final List <Survey> list = dataProvider.getList();
			for(Survey survey: Umfragen) {
			list.add(survey);
			}
		
		final List <SurveyEntry> surveyEntrylist = surveyEntryProvider.getList();
			for(SurveyEntry surveyEntry: UmfragenEintrag) {
			surveyEntrylist.add(surveyEntry);
			}
		}
	
	public void buildForm() {
		
		this.clear();
		mainPanel.add(back);
		back.addClickHandler(new backHandler());
		mainPanel.add(groupNameLabel);
		editPanel.add(editGroupButton);
		editPanel.add(deleteGroupButton);
		mainPanel.add(editPanel);
		editGroupButton.addClickHandler(new editGroupButtonClickHandler());
		deleteGroupButton.addClickHandler(new deleteGroupButtonClickHandler());
		groupNameLabel.setText(group.getName());
		mainPanel.add(newSurveyButton);
		newSurveyButton.addClickHandler(new newSurveyButtonClickHandler());
		mainPanel.add(table);
		mainPanel.add(cellList);
		this.add(mainPanel);
	}
	
	private class deleteGroupButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			editorAdministration.deleteAllGroupmemberByGroupID(group, new AsyncCallback<Void>() {
				
			@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub					
				}
				
				@Override
					public void onSuccess(Void result) {
					
					editorAdministration.deleteGroupByGroupID(group, new AsyncCallback<Void>() {
						
						@Override
							public void onSuccess(Void result) {
							}
										
						@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub				
							}
					});
					}
			});
			EditorForm form = new EditorForm(user);
			RootPanel.get().clear();
			RootPanel.get().add(form);
		}
	}
	
	private class editGroupButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			editorAdministration.getAllGroupmemberByGroupID(group, new AsyncCallback<Vector<Groupmember>>() {
				
				@Override
				public void onSuccess(Vector<Groupmember> result) {
					member=result;
				
					for(int i = 0; i< member.size(); i++) {
						int j=i;
						User u = new User(member.elementAt(i).getUserID());
						editorAdministration.getUserByUserID(u, new AsyncCallback<User>() {
							User u = null;
							
							@Override
							public void onSuccess(User result) {
								u = result;
								groupMember.add(u);
								if(j < member.size()+1) {
									mainPanel.clear();
									GroupEditForm edit = new GroupEditForm(user, group, groupMember, member);
									mainPanel.add(edit);
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
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert("etwas ist schief gelaufen");	
				}			
			});
			
		}
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
