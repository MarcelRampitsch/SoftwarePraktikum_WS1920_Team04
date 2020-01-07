package de.hdm.itprojekt.client.gui;


/**
 * 
 * @author serhatulus, Dominik Thumm
 * Klasse, die das Anzeigen der Gruppen untereinander anzeigen lässt. 
 * Diese wiederum wird in der BasisKlasse EditorForm instanziiert.
 */	

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;

public class CellListForm extends VerticalPanel {
	
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	User user = null;
	//Button GruppenErstellung = new Button("Neue Gruppe erstellen");
	
	public CellListForm(User user, List <Group> Gruppen) {
		this.user = user;
		this.Gruppen = Gruppen;
}
	

	
	 //erstellen des Data Provider
	   List<Group> Gruppen;


	
	//Vector <Group> Gruppen = null;
	public void onLoad() {
	   super.onLoad();
//	   GruppenErstellung.addClickHandler(new openClickHandler());
//	   this.add(GruppenErstellung);
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
				editorAdministration.deleteGroupByGroupID(anwender, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						dataProvider.getList().remove(anwender);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
				
//				AsyncCallback<Group> loeschenCallback = new AsyncCallback<Group>() {
//
//					@Override
//					public void onFailure(Throwable caught) {
//						Window.alert("Fail");
//
//					}
//
//					@Override
//					public void onSuccess(Group result) {
//						
//						Window.alert("Schmeckt");
//					}
//				};
			}
		});
		
		editColumn.setFieldUpdater(new FieldUpdater<Group, String>() {

			@Override
			public void update(int index, Group object, String value) {
			Group g = object;
			table.removeFromParent();
			table.removeColumn(1);
			table.setRemoveColumnsOnHide(true);
			GroupEditForm edit = new GroupEditForm(user, g);
			RootPanel.get().add(edit);
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
		this.add(table);
   }
	
	private class openClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			
			GroupEditForm edit = new GroupEditForm();
			RootPanel.get().add(edit);
			
		}
	}
}
