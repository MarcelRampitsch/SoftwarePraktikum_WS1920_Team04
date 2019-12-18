package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RadioButton;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;

     public class UmfrageEintragTable extends FlowPanel {
    	 
      private EditorAdministrationAsync editorAdministraion = ClientSideSettings.getEditorAdministration();

      private User user = null;

    	 
    	 
	
      private FlexTable umfragen = new FlexTable();
	  
	  private ArrayList<SurveyEntry> stocks = new ArrayList<SurveyEntry>();
	  
	  RadioButton radioJa = new RadioButton("radioGroup", "Ja");
      RadioButton radioNein = new RadioButton("radioGroup", "Nein");

      int row = umfragen.getRowCount();

      public UmfrageEintragTable() {
    	  
    	  
      }
	
	
	
	public void onLoad() {
		
		umfragen.setText(0, 0, "Film" );
		umfragen.setText(0, 1, "Spielzeit" );
		umfragen.setText(0, 2, "Kino" );
		umfragen.setText(0, 3, "Datum" );
		umfragen.setText(0, 4, "Votum" );
	//	umfragen.setWidget(row, 4, radioJa);
	//	umfragen.setWidget(row, 4, radioNein);

		this.add(umfragen);
		
		
	}

}
