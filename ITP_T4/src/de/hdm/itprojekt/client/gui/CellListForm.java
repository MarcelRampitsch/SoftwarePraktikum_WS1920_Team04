package de.hdm.itprojekt.client.gui;


/**
 * 
 * @author serhatulus
 * Klasse, die das Anzeigen der Gruppen untereinander anzeigen lässt. 
 * Diese wiederum wird in der BasisKlasse EditorForm instanziiert.
 */	

import java.util.Arrays;
import java.util.List;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class CellListForm extends VerticalPanel {
	
	
	 private static final List<String> Gruppen = Arrays.asList("Gruppe1", "Gruppe2",
		      "Gruppe3", "Gruppe4", "Gruppe5", "Gruppe6", "Gruppe8"); 
   
   
   
   public CellListForm() {

}
   
   public void onLoad() {
	   super.onLoad();
	   
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
