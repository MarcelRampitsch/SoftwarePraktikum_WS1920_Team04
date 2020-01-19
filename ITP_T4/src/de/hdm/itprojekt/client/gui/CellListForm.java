package de.hdm.itprojekt.client.gui;


import java.util.Collections;

/**
 * 
 * @author serhatulus, Dominik Thumm
 * Klasse, die das Anzeigen der Gruppen untereinander anzeigen lässt. 
 * Diese wiederum wird in der BasisKlasse EditorForm instanziiert.
 */	

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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Survey;


public class CellListForm extends VerticalPanel {
	
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	User user = null;
	//Button GruppenErstellung = new Button("Neue Gruppe erstellen");
	
	public CellListForm(User user, List <Group> Gruppen) {
		this.user = user;
		this.Gruppen = Gruppen;
}
	

	
	 //erstellen des Data Provider
	   List<Survey> Umfragen;
	   List<Group> Gruppen;
	   Vector<Groupmember> member = new Vector<Groupmember>();
	   Vector<User> groupMember = new Vector<User>();
	   Button neueGruppe = new Button("Create group");
	   VerticalPanel inhalt = new VerticalPanel();
	   
	// Create a list data provider.
	 		ListDataProvider<Group> dataProvider = new ListDataProvider<Group>();

	  
	
	//Vector <Group> Gruppen = null;
	public void onLoad() {
	   super.onLoad();
//	   GruppenErstellung.addClickHandler(new openClickHandler());
//	   this.add(GruppenErstellung);
	   neueGruppe.addClickHandler(new openGroupClickHandler());
	   final CellTable<Group> table = new CellTable<Group>();
	   
	   

	   
	   TextColumn<Group> nameColumn = new TextColumn<Group>() {

		@Override
		public String getValue(Group object) {
			// TODO Auto-generated method stub
			return object.getName();
		}
	   };
		
		table.addColumn(nameColumn, "Groupname");
		
		NoSelectionModel<Group> selectionModelMyObj = new NoSelectionModel<Group>();
		Handler tableHandle = new SelectionChangeEvent.Handler() 
		{

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				Group clickedObj = selectionModelMyObj.getLastSelectedObject();
				if(table.getKeyboardSelectedColumn()==0)
				{
					editorAdministration.getAllSurveyByGroupID(clickedObj, new AsyncCallback<Vector<Survey>>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Vector<Survey> result) {
							Umfragen = Collections.list(result.elements());
							inhalt.clear();
							GroupViewForm viewForm = new GroupViewForm(user,clickedObj,Umfragen);
							RootPanel.get().add(viewForm);	
						}
					});
					
									
				}
			}
		};

		selectionModelMyObj.addSelectionChangeHandler(tableHandle);
		table.setSelectionModel(selectionModelMyObj);


		
		dataProvider.addDataDisplay(table);
		
		final List <Group> list = dataProvider.getList();
		for(Group group : Gruppen) {
			list.add(group);
		}
		inhalt.add(neueGruppe);
		inhalt.add(table);
		this.add(inhalt);
   }
	
	public ListDataProvider<Group> getDataProvider() {
 		return this.dataProvider;
 	}
		
	
	
	class openGroupClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Vector<User> group = new Vector<User>();
			group.add(user);
			GruppenForm gf = new GruppenForm(user, group);
			inhalt.clear();
			inhalt.add(gf);
		}
	}
}
