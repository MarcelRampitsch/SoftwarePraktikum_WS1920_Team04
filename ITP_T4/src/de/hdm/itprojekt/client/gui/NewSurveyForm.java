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

public class NewSurveyForm extends VerticalPanel {

	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	
	private User user = null;
	
	ListDataProvider <SurveyEntry> dataProvider;
	
	VerticalPanel inhalt = new VerticalPanel();
	
	List <SurveyEntry> Umfrageeintrag;
	 
	List <Group> Gruppen;
	
	/*
	 * Erstellung der Widgets
	 */
	Button zurueckButton = new Button ("Zurueck");
	
	TextBox umfrageNameBox = new TextBox();
	
	Label datum = new Label("Datum waehlen:");
	DatePicker datumAuswahlPicker = new DatePicker();
	
	Label film = new Label("Film waehlen:");
	ListBox filmDropBox = new ListBox();
	
	Label kino = new Label("Kino waehlen:");
	ListBox kinoDropBox = new ListBox();
	
	Label spielzeit = new Label("Uhrzeit auswaehlen:");
	ListBox spielzeitDropBox = new ListBox();
	
	Button umfrageSichernButton = new Button ("Umfrage speichern");
	
	
	public void onLoad() {
		super.onLoad();
		inhalt.add(zurueckButton);
		zurueckButton.addClickHandler(new BackHandler());
		inhalt.add(umfrageNameBox);
		umfrageNameBox.getElement().setPropertyString("placeholder", "Umfragename...");
		inhalt.add(datum);
		inhalt.add(datumAuswahlPicker);
		inhalt.add(film);
		inhalt.add(filmDropBox);
		inhalt.add(kino);
		inhalt.add(kinoDropBox);
		inhalt.add(spielzeit);
		inhalt.add(spielzeitDropBox);
		inhalt.add(umfrageSichernButton);
		umfrageSichernButton.addClickHandler(new SafeHandler());
		
		this.add(inhalt);
	}
	
	private class BackHandler implements ClickHandler {

		
		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			EditorForm ef = new EditorForm(user, Gruppen);
			RootPanel.get().add(ef);
		}
	}
	
	private class SafeHandler implements ClickHandler {
		
		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			EditorForm ef = new EditorForm(user, Gruppen);
			RootPanel.get().add(ef);
			UmfragenTable umfragen = new UmfragenTable(user, null);
			RootPanel.get().add(umfragen);
			
			SurveyEntry se = new SurveyEntry(1, umfrageNameBox.getText());
			editorAdministration.createSurveyEntry(se, new AsyncCallback<SurveyEntry>() {
				
				public void onFailure(Throwable caught) {
					Window.alert("Beim Hinzufügen des Umfrageeintrages ist etwas schief gelaufen.");
				}

				@Override
				public void onSuccess(SurveyEntry result) {
					EditorForm editform = new EditorForm(user, Gruppen);
					RootPanel.get().add(editform);
					
					List <SurveyEntry> liste = dataProvider.getList();
					liste.add(se);
					Window.alert("Eingabe gesichert");
					
				}
			});
		}
	}
}
