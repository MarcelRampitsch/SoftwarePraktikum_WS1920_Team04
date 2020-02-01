package de.hdm.itprojekt.client.gui;

import java.sql.Date;
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
import com.google.gwt.user.client.ui.HasVisibility;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.gui.UmfragenOpenForm.CloseUmfrageOpenFormClickHandler;
import de.hdm.itprojekt.client.gui.admin.AdminForm;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;
/*
 * Diese Klasse dient der Erstellung von SurveyEntrys. Hier k�nnen Vorstellungen gesucht werden,
 * und diese dann als SurveyEntry gespeichert werden. Die Anzeige der SurveyEntrys erfolgt
 * dann in einer CellList.
 */
public class NewSurveyForm extends VerticalPanel {

	/**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>EditorAdministration</code>.
	   */
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();

	// Konstruktor
	public NewSurveyForm(User user, Group group) {
		this.user = user;
		this.group = group;
	}
	
	public NewSurveyForm(User user, Group group, Vector<Presentation> pres) {
		this.user = user;
		this.group = group;
		this.prese = pres;
	}

	private User user = null;
	private Vector<Cinema> cine = null;
	private Vector<Movie> movie = null;
	private Vector<Timeslot> timesl = null;
	private Vector<Presentation> prese = new Vector<Presentation>();

	ListDataProvider<SurveyEntry> dataProvider;

	VerticalPanel inhalt = new VerticalPanel();

	List<SurveyEntry> Umfrageeintrag;

	List<Group> Gruppen;

	Group group;
	/*
	 * Erstellung der Widgets
	 */
	Button zurueckButton = new Button("back");
	
	Label createSurveyLabel = new Label("Create survey:");
	
	Label name = new Label("Enter a surveyname:");
	TextBox umfrageNameBox = new TextBox();

	Label datum = new Label("Select date:");
	DatePicker datumAuswahlPicker = new DatePicker();
	
	Label kino = new Label("Select cinema:");
	ListBox kinoDropBox = new ListBox();

	Label film = new Label("Select movie:");
	ListBox filmDropBox = new ListBox();

	Label spielzeit = new Label("Select timeslot:");
	ListBox spielzeitDropBox = new ListBox();

	Button vorstellungSuchenButton = new Button("Add presentation");
	ListBox vorstellungenDropBox = new ListBox();
	
	Button umfrageSichernButton = new Button("Save survey");

	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Widget, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Widget hinzugefügt.
	 * Den Buttons werden deren Funktion entsprechend ClickHandler zugewiesen. 
	 */
	public void onLoad() {
		super.onLoad();
		inhalt.add(zurueckButton);
		inhalt.add(createSurveyLabel);
		createSurveyLabel.addStyleName("text");
		zurueckButton.addStyleName("backButtonStyle");
		zurueckButton.addClickHandler(new BackHandler());
		inhalt.add(name);
		inhalt.add(umfrageNameBox);
		umfrageNameBox.getElement().setPropertyString("placeholder", "Surveyname...");
		inhalt.add(datum);
		inhalt.add(datumAuswahlPicker);
		inhalt.add(kino);
		inhalt.add(kinoDropBox);
		inhalt.add(film);
		inhalt.add(filmDropBox);
		inhalt.add(spielzeit);
		inhalt.add(spielzeitDropBox);
		inhalt.add(vorstellungSuchenButton);
		vorstellungSuchenButton.addStyleName("searchButtonStyle");
		inhalt.add(vorstellungenDropBox);
		inhalt.add(umfrageSichernButton);
		umfrageSichernButton.addStyleName("saveButtonStyle");
		vorstellungSuchenButton.addClickHandler(new SearchHandler());
		umfrageSichernButton.addClickHandler(new SafeHandler());
		
		editorAdministration.getAllCinemaByUser(this.user, new AsyncCallback<Vector<Cinema>>() {

			public void onFailure(Throwable caught) {
				Window.alert("Something went wrong when loading the Cinema");
			}

			public void onSuccess(Vector<Cinema> result) {
				for (int i = 0; i < result.size(); i++) {
					kinoDropBox.addItem(result.elementAt(i).getName());
					kinoDropBox.setSelectedIndex(-1);
					cine = result;
				}
			}
		});

		editorAdministration.getAllMovieByUser(this.user, new AsyncCallback<Vector<Movie>>() {

			public void onFailure(Throwable caught) {
				Window.alert("Something went wrong when loading the Movie");
			}

			public void onSuccess(Vector<Movie> result) {
				for (int i = 0; i < result.size(); i++) {
					filmDropBox.addItem(result.elementAt(i).getName());
					filmDropBox.setSelectedIndex(-1);
					movie = result;
				}
			}
		});

		editorAdministration.getAllTimeslotByUser(this.user, new AsyncCallback<Vector<Timeslot>>() {

			public void onFailure(Throwable caught) {
				Window.alert("Something went wrong when loading the Timeslot");
			}

			public void onSuccess(Vector<Timeslot> result) {
				for (int i = 0; i < result.size(); i++) {
					spielzeitDropBox.addItem(result.elementAt(i).getTime());
					spielzeitDropBox.setSelectedIndex(-1);
					timesl = result;
				}
			}
		});
		this.add(inhalt);
	}
	/*
	 * Implementierung der Click Handler
	 */

	/*
	 * BackHandler: Handler, der auf die Bet�tigung der Schaltfl�che "Zurueck"
	 * reagiert, und den Nutzer wieder auf die vorhergehende Seite bef�rdert.
	 */
	private class BackHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			EditorForm ef = new EditorForm(user, Gruppen);
			RootPanel.get().add(ef);
		}
	}

	/*
	 * SearchHandler: Handler der auf die Bet�tigung der Schaltfl�che "Suchen"
	 * reagiert, und dabei dann die gefundenen Vorstellungen ausgibt.
	 */
	private class SearchHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			
			Cinema c = cine.elementAt(kinoDropBox.getSelectedIndex());
			Movie m = movie.elementAt(filmDropBox.getSelectedIndex());
			Timeslot t = timesl.elementAt(spielzeitDropBox.getSelectedIndex());
			Date date = new java.sql.Date(datumAuswahlPicker.getValue().getTime());
			Presentation p = new Presentation(umfrageNameBox.getText(), c.getId(), m.getId(), user.getId(), t.getId(),
					date);
			/*
			 * If Else Abfrage: F�r den Fall, dass bei einem Feld kein Wert ausgew�hlt wurde, wird eine entsprechende Fehlermeldung ausgegeben.
			 */
			
			if(kinoDropBox.getSelectedIndex() == -1) {
				Window.alert("Please select a cinema");
			}
			else if(filmDropBox.getSelectedIndex() == -1) {
				Window.alert("Please select a movie");
			}
			else if(spielzeitDropBox.getSelectedIndex() == -1) {
				Window.alert("Please select a timeslot");
			}
			else {
			 
			editorAdministration.getAllPresentationBySearchCriteria(p, new AsyncCallback<Vector<Presentation>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Something went wrong");

				}

				@Override
				public void onSuccess(Vector<Presentation> result) {
					vorstellungenDropBox.clear();
					if (result.isEmpty()) {
						Window.alert("There is no Presentation with these selected values");
						for (int i = 0; i < prese.size(); i++) {
							vorstellungenDropBox.addItem(prese.elementAt(i).getName());
						}
						vorstellungenDropBox.setVisibleItemCount(prese.size());
					}
					else {
						prese.add(result.elementAt(0));
						kinoDropBox.setSelectedIndex(-1);
						filmDropBox.setSelectedIndex(-1);
						spielzeitDropBox.setSelectedIndex(-1);
						for (int i = 0; i < prese.size(); i++) {
							vorstellungenDropBox.addItem(prese.elementAt(i).getName());
						}
						vorstellungenDropBox.setVisibleItemCount(prese.size());
						
							
					}
					
				}
			});
			}
		}
	}

	/*
	 * SafeHandler: Handler, der auf die Bet�tigung der Schaltfl�che "Speichern"
	 * reagiert, und dabei die zuvor eingegebenen Daten abspeichert und danach den
	 * Umfragetable anzeigt.
	 */
	private class SafeHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			if(prese.size()!=0) {
			
			Survey s = new Survey(umfrageNameBox.getText(), user.getId(),group.getId());
			editorAdministration.createSurvey(s, new AsyncCallback<Survey>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Something went wrong. Couldn't safe the surveyentry.");				
				}

				@Override
				public void onSuccess(Survey result) {
					
					for(int i=0; i<prese.size(); i++) {
						SurveyEntry se = new SurveyEntry(0,null,result.getId(),prese.elementAt(i).getId());
					
					editorAdministration.createSurveyEntry(se, new AsyncCallback<SurveyEntry>() {

						public void onFailure(Throwable caught) {
							Window.alert("Something went wrong");
						}

						@Override
						public void onSuccess(SurveyEntry result) {
							RootPanel.get().clear();
							EditorForm editform = new EditorForm(user, Gruppen);
							RootPanel.get().add(editform);
//
//							List<SurveyEntry> liste = dataProvider.getList();
//							liste.add(se);
						}
					});
					}
					}
			});
			//RootPanel.get().clear();
			EditorForm ef = new EditorForm(user, Gruppen);
			RootPanel.get().add(ef);
			}else {
				Window.alert("Survey has no entry");
			}
		}
	}
}