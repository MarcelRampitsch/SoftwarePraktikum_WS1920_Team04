package de.hdm.itprojekt.client.gui;

import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.gui.UmfragenOpenForm.CloseUmfrageOpenFormClickHandler;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;



public class UmfragenForm extends VerticalPanel {
	
EditorAdministrationAsync editorAdministraion = ClientSideSettings.getEditorAdministration();
	
	private User user = null;
	private Cinema selectedCinema = null;
	private Vector<Cinema> cine = null;
	private Cinema delete = null;
	
	ListDataProvider <SurveyEntry> dataProvider;	

	
	
	 Label umfrageErstellung = new Label("Umfrageerstellung:");
	 Button closeButton = new Button("<--");
	 TextBox namenBox = new TextBox();
	 Label changeLabel = new Label("Umfragenname:");
	 Label datum = new Label("Datum wählen:");
	 Label film = new Label ("Film wählen:");
	 final ListBox dropBoxFilm = new ListBox();
	 Label kino = new Label("Kino wählen:");
	 ListBox dropBoxKino = new ListBox();
	 Label spielzeit = new Label("Spielzeit wählen");
	 final ListBox dropBoxSpielzeit = new ListBox();
	 Button safeUmfrage = new Button("Umfrage speichern");
	 List<Group> Gruppen;
 //   private ArrayList<String> umfagen = new ArrayList<String>();


 //    CloseUmfrageOpenFormClickHandler closeHandler;
     safehandler umfageSpeichern;
    

	
     DatePicker datePicker = new DatePicker();



	
     VerticalPanel content = new VerticalPanel();
	
     public UmfragenForm(User user) {
    	 this.user = user;
     }
     
     
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Widget, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Widget hinzugefügt.
	 * Den Buttons werden deren Funktion entsprechend ClickHandler zugewiesen. 
	 */
	
	public void onLoad() {
		super.onLoad();
		umfageSpeichern = new safehandler();
		
//		closeButton.addStyleName("UmfrageCloser");
//		safeUmfrage.addStyleName("UmfrageSafer");
		
		safeUmfrage.addClickHandler(umfageSpeichern);
		content.add(closeButton);
	//	closeButton.addClickHandler(closeHandler);
		content.add(umfrageErstellung);
		content.add(changeLabel);
		content.add(namenBox);
		content.add(datum);
		content.add(datePicker);
		content.add(film);
		dropBoxFilm.addItem("Beispiel 1");
		dropBoxFilm.addItem("Beispiel 2");
		dropBoxFilm.addItem("Beispiel 3");
		content.add(dropBoxFilm);
		content.add(kino);
		closeButton.addClickHandler(new BackHandler());
		
		content.add(dropBoxKino);

		
/*		editorAdministraion.findAllCinemaByUser1(this.user, new AsyncCallback<Vector<Cinema>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Umfragen erstellen!");
			}

			@Override 
			public void onSuccess(Vector<Cinema> result) {
				
				for (int i = 0; i < result.size(); i++ ) {
					dropBoxKino.addItem(result.elementAt(i).getName());
					cine = result;
				}
			}
		});  */
		
		
		
		
		content.add(spielzeit);
		dropBoxSpielzeit.addItem("19:00");
		dropBoxSpielzeit.addItem("19:30");
		dropBoxSpielzeit.addItem("20:00");
		dropBoxSpielzeit.addItem("21:00");
		dropBoxSpielzeit.addItem("22:00");
		dropBoxSpielzeit.addItem("23:00");
		content.add(dropBoxSpielzeit);
		content.add(safeUmfrage);
//		closeButton.addClickHandler(closeHandler); 
		
		
		this.add(content);
		
		namenBox.getElement().setPropertyString("placeholder", "Umfragenname ...");

	   
		
	}
	
	
	
	/*
	 * Ab hier folgen alle CLICKHANDLER und CALLBACKS dieser Klasse!
	 */
    
	private class BackHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			EditorForm e1 = new EditorForm(user, Gruppen);
			RootPanel.get().add(e1);
		}
		
	}
    	
	
	
	
	
	
	/**
	 * safehandler: Wird beim Click auf <code> safeUmfrage </code> Button ausgelöst.
	 * Der User kann damit die Umfrage speichern.
	 */
	private class safehandsler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			EditorForm e1 = new EditorForm(user, Gruppen);
			RootPanel.get().add(e1);
			UmfragenTable umfragen = new UmfragenTable(user, null);
			RootPanel.get().add(umfragen);
			RootPanel.get().clear();

			
		}
		

	}
	
private class safehandler implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {	
			RootPanel.get().clear();
			EditorForm e1 = new EditorForm(user, Gruppen);
			RootPanel.get().add(e1);
			UmfragenTable umfragen = new UmfragenTable(user, null);
			RootPanel.get().add(umfragen);
			RootPanel.get().clear();
			
			SurveyEntry group1 = new SurveyEntry(1,namenBox.getText());
			editorAdministraion.createSurveyEntry(group1, new AsyncCallback<SurveyEntry>(){

				@Override
				public void onFailure(Throwable caught) {
						Window.alert("was ist schief gelauffen");
				}

				@Override
				public void onSuccess(SurveyEntry result) {
					
					EditorForm editform = new EditorForm(user, Gruppen);
					RootPanel.get().add(editform);

					List <SurveyEntry> liste = dataProvider.getList();
					liste.add(group1);

					
					Window.alert("EINGABE GESICHERT");
					
					
				}
				
			});
			
		
		
}
	}
	
	
	
	}
	
	
