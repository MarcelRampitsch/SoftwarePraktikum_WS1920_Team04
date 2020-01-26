package de.hdm.itprojekt.client.gui;

import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

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

public class GroupViewCellListForm extends VerticalPanel {
	
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	//Initalisierung relevanter Variablen und Widgets
	User user = null;

	List<Group> Gruppen;
	List<Survey> Umfragen;
	
	Vector<Groupmember> groupMember = new Vector<Groupmember>();
	Vector<User> userMember = new Vector<User>();
	Vector<SurveyEntry> surveyEntry = new Vector<SurveyEntry>();
	
	Button newGroup = new Button("Create a new Group");
	Button newSurvey = new Button("Create a new Survey");
	
	VerticalPanel contentPanel = new VerticalPanel();
	
	public GroupViewCellListForm(User user, List<Group> Gruppen, List<Survey> Umfragen) {
		
		this.user = user;
		this.Gruppen = Gruppen;
		this.Umfragen = Umfragen;
		
	}
	
	public void onLoad() {
		
		super.onLoad();
		
		newGroup.addClickHandler(new newGroupClickHandler());
		newSurvey.addClickHandler(new newSurveyClickHandler());
		
		final CellTable<Group> groupTable = new CellTable<Group>();
		final CellTable<Survey> surveyTable = new CellTable<Survey>();
		
		ListDataProvider<Group> groupDataProvider = new ListDataProvider<Group>();
		ListDataProvider<Survey> surveyDataProvider = new ListDataProvider<Survey>();
		
		TextColumn<Group> groupNameColumn = new TextColumn<Group>() {

			@Override
			public String getValue(Group object) {
			
				return object.getName();
		
			}
			
		};
		
		TextColumn<Survey> surveyNameColumn = new TextColumn<Survey>() {

			@Override
			public String getValue(Survey object) {
			
				return object.getName();
		
			}
			
		};
	
		
		Cell<String> deleteGroupCell = new ButtonCell();	
		
		Column<Group, String> deleteGroupColumn = new Column<Group, String>(deleteGroupCell) {

			@Override
			public String getValue(Group object) {
				
				return "X";
			
			}

		};
		
		Cell<String> deleteSurveyCell = new ButtonCell();	
		
		Column<Survey, String> deleteSurveyColumn = new Column<Survey, String>(deleteSurveyCell) {

			@Override
			public String getValue(Survey object) {
				
				return "X";
			
			}

		};
		
		Cell<String> editGroupCell = new ButtonCell();

		Column<Group, String> editGroupColumn = new Column<Group, String>(editGroupCell) {

			@Override
			public String getValue(Group object) {
				
				return "Edit";
			
			}

		};
		
		Cell<String> editSurveyCell = new ButtonCell();

		Column<Survey, String> editSurveyColumn = new Column<Survey, String>(editSurveyCell) {

			@Override
			public String getValue(Survey object) {
				
				return "Edit";
			
			}

		};
		

		deleteGroupColumn.setFieldUpdater(new FieldUpdater<Group, String>() {

			@Override
			public void update(int index, Group anwender, String value) {
				
				editorAdministration.deleteAllGroupmemberByGroupID(anwender, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						
						
						
					}

					@Override
					public void onSuccess(Void result) {
						
						
						
					}
				
				});
				
				editorAdministration.deleteGroupByGroupID(anwender, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						
						groupDataProvider.getList().remove(anwender);
					
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
					
						
					}
				
				});
				
			}
		
		});
		
		deleteSurveyColumn.setFieldUpdater(new FieldUpdater<Survey, String>() {

			@Override
			public void update(int index, Survey anwender, String value) {
				
				editorAdministration.deleteAllSurveyEntryBySurveyID(anwender, new AsyncCallback<Void>() {
				
					@Override
					public void onFailure(Throwable caught) {
						
						
						
					}

					@Override
					public void onSuccess(Void result) {
						
						
						
					}
				
				});
				
				editorAdministration.deleteSurveyBySurveyID(anwender, new AsyncCallback<Void>() {
			
					@Override
					public void onSuccess(Void result) {
						
						surveyDataProvider.getList().remove(anwender);
					
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
					
						
					}
				
				});
				
			}
		
		});
	
	
		editGroupColumn.setFieldUpdater(new FieldUpdater<Group, String>() {

			@Override
			public void update(int index, Group object, String value) {
			
			Group g = object;			
			editorAdministration.getAllGroupmemberByGroupID(g, new AsyncCallback<Vector<Groupmember>>() {
				
				@Override
				public void onSuccess(Vector<Groupmember> result) {
					groupMember = result;
					
				for (int i = 0; i< groupMember.size(); i++) {
					User u = new User(groupMember.elementAt(i).getUserID());
					editorAdministration.getUserByUserID(u, new AsyncCallback<User>() {
						User u = null;
						@Override
						public void onSuccess(User result) {
						u = result;
						userMember.add(u);
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler!");
						}
						
					});
				}
				contentPanel.clear();
				GroupEditForm groupEditForm = new GroupEditForm(user, g, userMember , groupMember);
				contentPanel.add(groupEditForm);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					
					Window.alert("Fehler!");	
				}			
			
			});
			
			}
		
		});
		
		
//		editSurveyColumn.setFieldUpdater(new FieldUpdater<Survey, String>() {
//
//			@Override
//			public void update(int index, Survey object, String value) {
//			
//			Survey s = object;			
//			editorAdministration.getSurveyBySurveyID(s, new AsyncCallback<Vector<SurveyEntry>>() {
//				
//				@Override
//				public void onSuccess(Vector<SurveyEntry> result) {
//					surveyEntry = result;
//				for (int i = 0; i< surveyEntry.size(); i++) {
//					SurveyEntry se = new SurveyEntry(surveyEntry.elementAt(i).getSurveyEntryID());
//					editorAdministration.getSurveyEntryBySurveyEntryID(se, new AsyncCallback<SurveyEntry>() {
//						SurveyEntry se = null;
//						@Override
//						public void onSuccess(SurveyEntry result) {
//						se = result;
//						surveyEntry.add(se);
//						}
//						
//						@Override
//						public void onFailure(Throwable caught) {
//							Window.alert("Fehler!");
//						}
//						
//					});
//				}
//				contentPanel.clear();
//				GroupViewForm groupViewForm = new GroupViewForm(user, s, surveyEntry);
//				contentPanel.add(groupViewForm);
//				}
//				
//				@Override
//				public void onFailure(Throwable caught) {
//					
//					Window.alert("Fehler!");	
//				}			
//			
//			});
//			
//			}
//		
//		});
		
		groupTable.addColumn(groupNameColumn, "Gruppenname:");
		groupTable.addColumn(deleteGroupColumn);
		groupTable.addColumn(editGroupColumn);
		
		groupDataProvider.addDataDisplay(groupTable);
		
		final List<Group> groupList = groupDataProvider.getList();
		for(Group group : Gruppen) {
			groupList.add(group);
		}
		contentPanel.add(newGroup);
		contentPanel.add(groupTable);
		this.add(contentPanel);
		
		surveyTable.addColumn(surveyNameColumn, "Survey:");
		surveyTable.addColumn(deleteSurveyColumn);
		surveyTable.addColumn(editSurveyColumn);
		
		surveyDataProvider.addDataDisplay(surveyTable);
		
		final List<Survey> surveyList = surveyDataProvider.getList();
		for(Survey survey : Umfragen) {
			surveyList.add(survey);
		}
		contentPanel.add(newSurvey);
		contentPanel.add(surveyTable);
		this.add(contentPanel);
	
	}

	class newGroupClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			Vector<User> group = new Vector<User>();
			group.add(user);
			GruppenForm gruppenForm = new GruppenForm(user, group);
			contentPanel.clear();
			contentPanel.add(gruppenForm);
		
		}
		
	}
	
	class newSurveyClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			NewSurveyForm newSurveyForm = new NewSurveyForm(user,null);
			contentPanel.clear();
			contentPanel.add(newSurveyForm);
			
		}
		
	}
	
	
}
