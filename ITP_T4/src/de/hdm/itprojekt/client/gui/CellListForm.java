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
	

	
	 //erstellen des Data Provider
	   List<Group> Gruppen;


	
	//Vector <Group> Gruppen = null;
	public void onLoad() {
	   super.onLoad();
	   final CellTable<Group> table = new CellTable<Group>();
	
	    // Create a list data provider.
		ListDataProvider<Group> dataProvider = new ListDataProvider<Group>();

	   
	   TextColumn<Group> nameColumn = new TextColumn<Group>() {

		@Override
		public String getValue(Group object) {
			// TODO Auto-generated method stub
			return object.getName();
		}
	};
		
		
		
        Cell<String> loeschenCell = new ButtonCell();	
		
		Column<Group, String> loeschenColumn = new Column<Group, String>(loeschenCell) {

			@Override
			public String getValue(Group object) {
				// TODO Auto-generated method stub
				return "X";
			}

		};
		
		Cell<String> editCell = new ButtonCell();

		Column<Group, String> editColumn = new Column<Group, String>(editCell) {

			@Override
			public String getValue(Group object) {
				// TODO Auto-generated method stub
				return "Edit";
			}

		};
		
		loeschenColumn.setFieldUpdater(new FieldUpdater<Group, String>() {

			@Override
			public void update(int index, Group anwender, String value) {
				// TODO Auto-generated method stub
				dataProvider.getList().remove(anwender);

				AsyncCallback<Group> loeschenCallback = new AsyncCallback<Group>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fail");

					}

					@Override
					public void onSuccess(Group result) {

						Window.alert("Schmeckt");

					}

				};

		//		adminAdministration.delete(anwender, loeschenCallback );
			}

		});
		
		
		
		
		
		
		table.addColumn(nameColumn, "Gruppenname");
		table.addColumn(loeschenColumn, "Gruppe löschen");
		table.addColumn(editColumn, "Gruppe editieren");
		
		dataProvider.addDataDisplay(table);
		
		final List <Group> list = dataProvider.getList();
		for(Group group : Gruppen) {
			list.add(group);
		}
		RootPanel.get().add(table);
   }
}
