package de.hdm.itprojekt.client.gui;

import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
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
	
	User user = null;

	List<Group> Gruppen;
	List<Survey> Umfragen;
	
	Vector<Groupmember> groupMember = new Vector<Groupmember>();
	Vector<User> userMember = new Vector<User>();
	Vector<SurveyEntry> surveyEntry = new Vector<SurveyEntry>();
	
	Button newSurvey = new Button("Neue Umfrage erstellen");
	
	VerticalPanel contentPanel = new VerticalPanel();
	
	public GroupViewCellListForm(User user, List<Group> Gruppen, List<Survey> Umfragen) {
		
		this.user = user;
		this.Gruppen = Gruppen;
		this.Umfragen = Umfragen;
		
	}
	
	public void onLoad() {
		
		super.onLoad();
		
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

		Column<Survey, String> editColumn = new Column<Survey, String>(editSurveyCell) {

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
	
	
		
		
	
	}

	
	
	
}
