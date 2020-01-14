package de.hdm.itprojekt.client.gui;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

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
	
	Vector<Groupmember> member = new Vector<Groupmember>();
	Vector<User> groupMember = new Vector<User>();
	
	Button newSurvey = new Button("Neue Umfrage erstellen");
	
	VerticalPanel contentPanel = new VerticalPanel();
	
	public GroupViewCellListForm(User user, List<Group> Gruppen) {
		
		this.user = user;
		this.Gruppen = Gruppen;
		
	}
	
	public void onLoad() {
		
		
		
		
		
	}
	
	

}
