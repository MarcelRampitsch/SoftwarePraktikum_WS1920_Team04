package de.hdm.itprojekt.client.gui;


/**
 * 
 * @author serhatulus, Dominik Thumm
 * Klasse, die das Anzeigen der Gruppen untereinander anzeigen lässt. 
 * Diese wiederum wird in der BasisKlasse EditorForm instanziiert.
 */	

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.gui.admin.AdminForm;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;

public class CellListForm extends VerticalPanel {
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();
	User user = null;
	
	public CellListForm(User user, List <Group> Gruppen) {
		this.user = user;
		this.Gruppen = Gruppen;
}
	
	public CellListForm() {
		
	}
	
	 //erstellen des Data Provider
	   List<Group> Gruppen;


	
	//Vector <Group> Gruppen = null;
	public void onLoad() {
	   super.onLoad();
	   Window.alert(Gruppen.get(0).getName());
	   final CellTable<Group> table = new CellTable<Group>();
	   
	   TextColumn<Group> nameColumn = new TextColumn<Group>() {

		@Override
		public String getValue(Group object) {
			// TODO Auto-generated method stub
			return object.getName();
		}
	};
	
		TextColumn<Group> adressColumn = new TextColumn<Group>(){

			@Override
			public String getValue(Group group) {
				// TODO Auto-generated method stub
				return group.getName();
			}
		};
		table.addColumn(nameColumn, "Name");
		table.addColumn(adressColumn, "Name2");
		
		ListDataProvider<Group> dataProvider = new ListDataProvider<Group>();
		dataProvider.addDataDisplay(table);
		
		final List <Group> list = dataProvider.getList();
		for(Group group : Gruppen) {
			list.add(group);
		}
		RootPanel.get().add(table);
   }
}
