package de.hdm.itprojekt.client.gui;

import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
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
	
	Vector<Groupmember> groupMember = new Vector<Groupmember>();
	Vector<User> userMember = new Vector<User>();
	
	Button newSurvey = new Button("Neue Umfrage erstellen");
	
	VerticalPanel contentPanel = new VerticalPanel();
	
	public GroupViewCellListForm(User user, List<Group> Gruppen) {
		
		this.user = user;
		this.Gruppen = Gruppen;
		
	}
	
	public void onLoad() {
		
		super.onLoad();
		
		final CellTable<Group> groupTable = new CellTable<Group>();

		ListDataProvider<Group> dataProvider = new ListDataProvider<Group>();

		TextColumn<Group> nameColumn = new TextColumn<Group>() {

			@Override
			public String getValue(Group object) {
			
				return object.getName();
		
			}
			
		};
	
		
		Cell<String> deleteCell = new ButtonCell();	
		
		Column<Group, String> deleteColumn = new Column<Group, String>(deleteCell) {

			@Override
			public String getValue(Group object) {
				
				return "X";
			
			}

		};
		
		Cell<String> editCell = new ButtonCell();

		Column<Group, String> editColumn = new Column<Group, String>(editCell) {

			@Override
			public String getValue(Group object) {
				
				return "Edit";
			
			}

		};
		
		
		
		
		
		
	}

	
	
	
}
