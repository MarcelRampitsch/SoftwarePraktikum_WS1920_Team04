package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;

/**
 * 
 * @author serhatulus, DominikThumm
 * BasisKlasse für GruppenOpenForm. Diese Klasse wird in dieser Klasse instanziiert. 
 */

public class GruppenForm extends VerticalPanel {
	
	private EditorAdministrationAsync editorAdministraion = ClientSideSettings.getEditorAdministration();

	private User user = null;
	
	CellListForm c1 =null;
	
	CellList <Group> c2;
	
	private Vector<Group> groups = null;


	private Vector<Group> currentsubs;
	private ListDataProvider<Group> cellListDataProvider;
	private SelectionModel<Group> selectionModel;
	
	
	
	public GruppenForm() {

	}

	 Button GruppenErstellung = new Button("Neue Gruppe erstellen");

	public void onLoad() {
		super.onLoad();
		
		// Versuch Laden der Vorhandenn Gruppen in die CellList
		
		editorAdministraion.getAllGroupByUserID(this.user, new AsyncCallback<Vector<Group>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim laden der Gruppen");
				
			}

			@Override
			public void onSuccess(Vector<Group> result) {
				
				// Daten der CellList zurücksetzen
				cellListDataProvider.getList().clear();
				
				for (int i = 0; i < result.size(); i++ ) {
					cellListDataProvider.getList().add(result.get(i));

				//	c2.getCellParent(result.elementAt(i).getName());
				//	groups = result;
				}
				selectionModel.setSelected(cellListDataProvider.getList().get(0), true);

				
				
				
			}
			
		});

		GruppenErstellung.addStyleName("GruppenButton");
		GruppenErstellung.addClickHandler(new openClickHandler());

		this.add(GruppenErstellung);

	}

	private class openClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			//Methode, um das anzeigen der DialogBox(GruppenErstellung) zu realisieren
			GruppenOpenForm open = new GruppenOpenForm();
			open.openGruppenForm();
		}

	}
}
