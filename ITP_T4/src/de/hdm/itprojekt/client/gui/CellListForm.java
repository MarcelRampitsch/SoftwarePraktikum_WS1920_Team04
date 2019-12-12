package de.hdm.itprojekt.client.gui;


/**
 * 
 * @author serhatulus
 * Klasse, die das Anzeigen der Gruppen untereinander anzeigen lässt. 
 * Diese wiederum wird in der BasisKlasse EditorForm instanziiert.
 */	

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;

public class CellListForm extends VerticalPanel {
	
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	User user = null;
	private static final List<String> Gruppen = Arrays.asList();
   
	public CellListForm(User user) {
		this.user = user;
}

	public void onLoad() {
	   super.onLoad();
	   
	 editorAdministration.getAllGroupnameByUserID(user, new AsyncCallback<Vector<Group>>() {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("fehler");
		}

		@Override
		public void onSuccess(Vector<Group> result) {
			
			for (int i = 0; i < result.size(); i++ ) {
				String s = null;
				Group g = result.elementAt(i);
				s = g.getName();
				Gruppen.add(s);
				Window.alert(s);
		}
		}});
	   
	   //INSTANZ DER KLASSE TEXTCELL
	   TextCell textCell = new TextCell();
	   
	   //INSTANZ DER KKASSE CELLLIST PLUS DIE VORHER ERSTELLTE INSTANT VON TEXTCELL HIER ÜBERGEBEN 
	   CellList<String> cellList = new CellList<String>(textCell);
	   
	   cellList.addStyleName("gruppenliste");
	    cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
	    cellList.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	        String selected = selectionModel.getSelectedObject();
	        if (selected != null) {
	          Window.alert("You selected : " + selected);
	          
	        }
	      }
	    });
	   
	    
	    cellList.setRowCount(Gruppen.size(), true);
	    cellList.setRowData(0, Gruppen);
	   
	    this.add(cellList);
	 
   }
   
 

}
