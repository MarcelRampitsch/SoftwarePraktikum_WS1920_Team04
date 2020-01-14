package de.hdm.itprojekt.client.gui;

import java.util.List;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;


/*
 * 
 * @author VanDuyHo
 * 
 */

public class GroupViewCellListForm {
	
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	
	User user = null;

	List<Group> Gruppen;
	
	public GroupViewCellListForm(User user, List<Group> Gruppen) {
		
		this.user = user;
		this.Gruppen = Gruppen;
		
	}

}
