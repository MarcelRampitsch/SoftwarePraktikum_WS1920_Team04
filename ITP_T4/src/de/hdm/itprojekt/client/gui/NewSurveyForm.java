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

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.gui.UmfragenOpenForm.CloseUmfrageOpenFormClickHandler;
import de.hdm.itprojekt.client.gui.admin.AdminForm;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;

public class NewSurveyForm extends VerticalPanel {

	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();

	public NewSurveyForm(User user, Group group) {
		this.user = user;
		this.group = group;
	}

	private User user = null;
	private Vector<Cinema> cine = null;
	private Vector<Movie> movie = null;
	private Vector<Timeslot> timesl = null;
	private Vector<Presentation> prese = null;

	ListDataProvider<SurveyEntry> dataProvider;

	VerticalPanel inhalt = new VerticalPanel();

	List<SurveyEntry> Umfrageeintrag;

	List<Group> Gruppen;

	Group group;
	/*
	 * Erstellung der Widgets
	 */
	Button zurueckButton = new Button("Zurueck");
	
	Label name = new Label("Bitte Name der Umfrage eingeben:");
	TextBox umfrageNameBox = new TextBox();

	Label datum = new Label("Datum auswaehlen:");
	DatePicker datumAuswahlPicker = new DatePicker();

	Label kino = new Label("Kino auswaehlen:");
	ListBox kinoDropBox = new ListBox();

	Label film = new Label("Film auswaehlen:");
	ListBox filmDropBox = new ListBox();

	Label spielzeit = new Label("Uhrzeit auswaehlen:");
	ListBox spielzeitDropBox = new ListBox();

	Button vorstellungSuchenButton = new Button("Vorstellung suchen");

	Button umfrageSichernButton = new Button("Umfrage speichern");

	public void onLoad() {
		super.onLoad();
		inhalt.add(zurueckButton);
		zurueckButton.addClickHandler(new BackHandler());
		inhalt.add(name);
		inhalt.add(umfrageNameBox);
		umfrageNameBox.getElement().setPropertyString("placeholder", "Umfragename...");
		inhalt.add(datum);
		inhalt.add(datumAuswahlPicker);
		inhalt.add(kino);
		inhalt.add(kinoDropBox);
		inhalt.add(film);
		inhalt.add(filmDropBox);
		inhalt.add(spielzeit);
		inhalt.add(spielzeitDropBox);
		inhalt.add(vorstellungSuchenButton);
		inhalt.add(umfrageSichernButton);
		vorstellungSuchenButton.addClickHandler(new SearchHandler());
		umfrageSichernButton.addClickHandler(new SafeHandler());

		editorAdministration.getAllCinemaByUser(this.user, new AsyncCallback<Vector<Cinema>>() {

			public void onFailure(Throwable caught) {
				Window.alert("Beim Laden der Kinos ist etwas schief gelaufen");
			}

			public void onSuccess(Vector<Cinema> result) {
				for (int i = 0; i < result.size(); i++) {
					kinoDropBox.addItem(result.elementAt(i).getName());
					cine = result;
				}
			}
		});

		editorAdministration.getAllMovieByUser(this.user, new AsyncCallback<Vector<Movie>>() {

			public void onFailure(Throwable caught) {
				Window.alert("Beim Laden der Filme ist etwas schief gelaufen");
			}

			public void onSuccess(Vector<Movie> result) {
				for (int i = 0; i < result.size(); i++) {
					filmDropBox.addItem(result.elementAt(i).getName());
					movie = result;
				}
			}
		});

		editorAdministration.getAllTimeslotByUser(this.user, new AsyncCallback<Vector<Timeslot>>() {

			public void onFailure(Throwable caught) {
				Window.alert("Beim Laden der Timeslots ist etwas schief gelaufen");
			}

			public void onSuccess(Vector<Timeslot> result) {
				for (int i = 0; i < result.size(); i++) {
					spielzeitDropBox.addItem(result.elementAt(i).getTime());
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

	private class SearchHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			Cinema c = cine.elementAt(kinoDropBox.getSelectedIndex());
			Movie m = movie.elementAt(filmDropBox.getSelectedIndex());
			Timeslot t = timesl.elementAt(spielzeitDropBox.getSelectedIndex());
			Date date = new java.sql.Date(datumAuswahlPicker.getValue().getTime());
			Presentation p = new Presentation(umfrageNameBox.getText(), c.getId(), m.getId(), user.getId(), t.getId(),
					date);
			/*
			 * if(umfrageNameBox.getText() != null & kinoDropBox.getSelectedItemText() !=
			 * "Kein Kino ausgewählt" & filmDropBox.getSelectedItemText()
			 * !="Kein Film ausgewählt" & spielzeitDropBox.getSelectedItemText() !=
			 * "Keine Spielzeit ausgewählt") {
			 */
			editorAdministration.getAllPresentationBySearchCriteria(p, new AsyncCallback<Vector<Presentation>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Bei der Suche der Vorstellungen ist etwas schief gelaufen");

				}

				@Override
				public void onSuccess(Vector<Presentation> result) {
					ListBox vorstellungenDropBox = new ListBox();
					inhalt.add(vorstellungenDropBox);
					for (int i = 0; i < result.size(); i++) {
						vorstellungenDropBox.addItem(result.elementAt(i).getName());
						prese = result;

					}
				}
			});

		}
	}

	/*
	 * SafeHandler: Handler, der auf die Bet�tigung der Schaltfl�che "Speichern"
	 * reagiert, und dabei die zuvor eingegebenen Daten abspeichert und danach den
	 * Umfragetable anzeigt.
	 */
	private class SafeHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			Window.alert(group.getName());

			
			
			
			Survey s = new Survey(umfrageNameBox.getText(), user.getId(),group.getId());
			editorAdministration.createSurvey(s, new AsyncCallback<Survey>(){

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Survey result) {
					if (false) {  // WIrd ausgeführt wenn der Nutzer eine Veranstaltung ausgewählt hat
						
					
					SurveyEntry se = new SurveyEntry(result.getId(),"Hier sollte die VeranstaltungsID stehen!" );
					editorAdministration.createSurveyEntry(se, new AsyncCallback<SurveyEntry>() {

						public void onFailure(Throwable caught) {
							Window.alert("Beim Hinzuf�gen des Umfrageeintrages ist etwas schief gelaufen.");
						}

						@Override
						public void onSuccess(SurveyEntry result) {
							EditorForm editform = new EditorForm(user, Gruppen);
							RootPanel.get().add(editform);

							List<SurveyEntry> liste = dataProvider.getList();
							liste.add(se);
							Window.alert("Eingabe gesichert");
						}
					});
					}
				}
				
			});
			RootPanel.get().clear();
			EditorForm ef = new EditorForm(user, Gruppen);
			RootPanel.get().add(ef);
			UmfragenTable umfragen = new UmfragenTable(user, null);
			RootPanel.get().add(umfragen);

			
		}
	}
}