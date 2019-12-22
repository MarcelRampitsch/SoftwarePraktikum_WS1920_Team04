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
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
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
   
	public CellListForm(User user) {
		this.user = user;
}
	
	public CellListForm() {
		
	}
	
	 //erstellen des Data Provider
	 public static final ListDataProvider<Group> dataProvider = new ListDataProvider<Group>();

	
	//Vector <Group> Gruppen = null;
	public void onLoad() {
	   super.onLoad();
/*	   editorAdministration.getAllGroupnameByUserID(user, new AsyncCallback<Vector<Group>>() {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("fehler");
		}

		@Override
		public void onSuccess(Vector<Group> result) {
			for (int i = 0; i < result.size(); i++ ) {
				Group g = result.elementAt(i);
				Window.alert(""+g.getName()+"");
				Vector<Group> a = null;
				a.addElement(g);
				Gruppen = a; 
		}
		}});             */
	 
	   //INSTANZ DER KLASSE TEXTCELL
/*	   GroupCell textCell = new GroupCell();
	   
	   ProvidesKey<Group> keyProvider = new ProvidesKey<Group>() {
	         public Object getKey(Group item) {
	            // Always do a null check.
	            return (item == null) ? null : item.getId();
	         }
	      };   */
	      
	      
	/*      TextCell gruppenNameTextCell = new TextCell();

			Column<Group, String> namenColumn = new Column<Group, String>(gruppenNameTextCell) {

				@Override
				public String getValue(Group gruppe) {
					// TODO Auto-generated method stub

					return gruppe.getName();

				}

			};  */
			
			  TextInputCell input = new TextInputCell();
			   
			   Column<Group, String> changeName = new Column<Group,String>(input){

				@Override
				public String getValue(Group object) {
					// TODO Auto-generated method stub
					return object.getName();
				}
				   
			   };
			
			Cell<String> gruppenDeleteCell = new ButtonCell();

			Column<Group, String> deleteColumn = new Column<Group, String>(gruppenDeleteCell) {

				@Override
				public String getValue(Group object) {
					// TODO Auto-generated method stub
					return "X";
				}

			};
		   
			Cell<String> gruppenEditCell = new ButtonCell();

			Column<Group, String> editColumn = new Column<Group, String>(gruppenEditCell) {

				@Override
				public String getValue(Group object) {
					// TODO Auto-generated method stub
					return "Edit";
				}

			};
			
			
			deleteColumn.setFieldUpdater(new FieldUpdater<Group, String>() {

				@Override
				public void update(int index, Group dd, String value) {
					// TODO Auto-generated method stub
					dataProvider.getList().remove(dd);

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

			//		editorAdministration.deleteByGroupID(dd, loeschenCallback );         // Implementierung des Callbacks fehlt Button wird immer neu
				} // erzeugt wie implementiert man hier den ClickHandler?

			});
	   
	   //INSTANZ DER KLASSE CELLLIST PLUS DIE VORHER ERSTELLTE INSTANT VON TEXTCELL HIER ÜBERGEBEN 
	//   CellList<Group> cellList = new CellList<Group>(textCell, keyProvider);
	      
	      
	      final ProvidesKey<Group> KEY_PROVIDER = new ProvidesKey<Group>() {

		  		@Override
		  		public Object getKey(Group anwender) {
		  			// TODO Auto-generated method stub

		  			return anwender.getId();
		  		}

		  	};
	      
	      
	      
		   //INSTANZ DER KLASSE CELLTable PLUS DIE VORHER ERSTELLTE INSTANT VON TEXTCELL HIER ÜBERGEBEN 
		   CellTable<Group> gruppenCellTable = new CellTable<Group>(KEY_PROVIDER);

	   
	   
	   
	   // Add the cellList to the dataProvider.
	    dataProvider.addDataDisplay(gruppenCellTable);
	    
	    
	    
	    
	    /***********************************************************************
		 * CELL List Code
		 ***********************************************************************/
	    
	    
	    
/*	    cellList.addStyleName("gruppenliste");
	    cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	    final SingleSelectionModel<Group> selectionModel = new SingleSelectionModel<Group>();
	    cellList.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	    	  Group selected = selectionModel.getSelectedObject();
	        if (selected != null) {
	          Window.alert("You selected : " + selected.getName()+selected.getId());
	          
	        }
	      }
	    }); */

	   
/*	    cellList.setRowCount(Gruppen.size(), true);
	    for (int i = 0; i < Gruppen.size(); i++ ) {
	    	Group g = Gruppen.elementAt(i);
	    	String s = g.getName();
	    //	Neu.add(s);
	    	
		 //   cellList.setRowData(0, Neu);;
	    }    */
		 List <Group> Gruppen =  dataProvider.getList();

		 
	//	 gruppenCellTable.addColumn(namenColumn,"Gruppenname");
		 gruppenCellTable.addColumn(changeName,"Gruppenname");
		 gruppenCellTable.addColumn(deleteColumn,"Gruppe entfernen");
		 gruppenCellTable.addColumn(editColumn, "Gruppe editieren");
	   
	    this.add(gruppenCellTable);
	 
   }
   
 

}
