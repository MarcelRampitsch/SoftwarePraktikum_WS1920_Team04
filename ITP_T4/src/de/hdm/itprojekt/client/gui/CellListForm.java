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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
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
	
	/**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>EditorAdministration</code>.
	   */
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	
	//Initialisierung relevanter Variablen und Widgets
	User user = null;
	List<Survey> Umfragen;
	List<Group> Gruppen;
	Vector<Groupmember> member = new Vector<Groupmember>();
	Vector<User> groupMember = new Vector<User>();
	Button userManage = new Button("Manage user");
	Button neueGruppe = new Button("Create group");
	VerticalPanel inhalt = new VerticalPanel();
	
	// Create a list data provider.
	ListDataProvider<Group> dataProvider = new ListDataProvider<Group>();
	
	// Erstellung der <code>CellListForm</code> Constructor
	public CellListForm(User user, List <Group> Gruppen) {
		this.user = user;
		this.Gruppen = Gruppen;
	}
	
	public void onLoad() {
	   super.onLoad();
	   userManage.addStyleName("createButtonStyle");
	   userManage.addClickHandler(new userClickHandler());
	   neueGruppe.addStyleName("createButtonStyle");
	   neueGruppe.addClickHandler(new openGroupClickHandler());
	   
	   //Erstellung eins CellTables in dem alle Gruppen in denen der User vorhanden ist angezeigt werden
	   final CellTable<Group> table = new CellTable<Group>();
	   
	   //Erzeugen einer TextSpalte in dem der Name der Gruppe angezeigt wirde
	   TextColumn<Group> nameColumn = new TextColumn<Group>() {

		   @Override
			public String getValue(Group object) {
			   // Übergabe des Gruppenname an die Textspalte
			   return object.getName();
		   }
	   };
	   
	   //Dem CellTable "table" die Spalte "nameColumn" hinzufügen und die Spalten Überschrift setzen "Groupname"
	   table.addColumn(nameColumn, "Groupname");
	   
	   //Erstellung eines NoSelectionModels des Typs Group
	   NoSelectionModel<Group> selectionModelMyObj = new NoSelectionModel<Group>();
	   //Erstellung eines SelectionChangeEvent Handler mit dem Namen "tableHandle"
	   Handler tableHandle = new SelectionChangeEvent.Handler() 
		{

			@Override
			// Methode zur behandlung einer Auswahl eines objectes
			public void onSelectionChange(SelectionChangeEvent event) {
				// Die Ausgewaehlte Gruppe speichern
				Group clickedObj = selectionModelMyObj.getLastSelectedObject();
				// Wenn die ausgewählte Spalte die erste Spalte ist dann werden alle Umfragen dieser Gruppe gesucht
				if(table.getKeyboardSelectedColumn()==0)
				{
					editorAdministration.getAllSurveyByGroupID(clickedObj, new AsyncCallback<Vector<Survey>>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Vector<Survey> result) {
							//Umwandeln des Surveyvectors in eine List mit Survey objekten
							Umfragen = Collections.list(result.elements());
							//Den inhalt des widgets "inhalt" löschen
							inhalt.clear();
							//Erstellen eines neuen GroupViewForm widgets
							GroupViewForm viewForm = new GroupViewForm(user,clickedObj,Umfragen);
							//Dem inhalt-Panel dieses widget hinzufügen
							inhalt.add(viewForm);
						}
					});				
				}
			}
		};
		// Dem NoSelectionModel den ChangeEventHandler "tableHandle" hinzufügen
		selectionModelMyObj.addSelectionChangeHandler(tableHandle);
		// Dem CellTable das NoSelectionModel hinzufügen
		table.setSelectionModel(selectionModelMyObj);
		
		//Dem DataProvider "dataProvider" der aus Group objekten besteht wird eine Ausgabe hinzugefügt hier der CellTable "table"
		dataProvider.addDataDisplay(table);
		
		final List <Group> list = dataProvider.getList();
		for(Group group : Gruppen) {
			list.add(group);
		}
		inhalt.add(userManage);
		inhalt.add(neueGruppe);
		inhalt.add(table);
		this.add(inhalt);
	}
	
	// Methode ListDataProvider die einen DataProvider zurück gibt
	public ListDataProvider<Group> getDataProvider() {
 		return this.dataProvider;
 	}
	
	// ClickHandler zur öffnung des Gruppenerstellungs Form GruppenForm
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
	
	class userClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
		UserEdit form = new UserEdit(user);
		form.openUser();
		}
	}
}
