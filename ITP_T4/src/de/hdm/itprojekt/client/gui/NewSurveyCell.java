package de.hdm.itprojekt.client.gui;

import java.sql.Date;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.Timeslot;

public class NewSurveyCell extends AbstractCell<SurveyEntry> {

	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();

	Cinema c = new Cinema();
	Movie m = new Movie();
	Timeslot t = new Timeslot();
	SafeHtmlBuilder shb;

	@Override
	public void render(Context context, SurveyEntry value, SafeHtmlBuilder sb) {
		// TODO Auto-generated method stub
		if(value == null) {
			return;
		}
		shb = sb;
		
		shb.appendHtmlConstant("<div>");
		shb.appendHtmlConstant("<Umfrageeinträge:>");
		
		editorAdministration.getCinemaBySurveyEntry(value, new AsyncCallback<Cinema>() {
			
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(Cinema result) {
				
				shb.appendEscaped(result.getName());
				shb.appendHtmlConstant("<div>");
			}
		});
		
		editorAdministration.getMovieBySurveyEntry(value, new AsyncCallback<Movie>() {
			
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(Movie result) {
				
				shb.appendEscaped(result.getName());
				shb.appendHtmlConstant("<div>");
			}
		});
		
		editorAdministration.getTimeslotBySurveyEntry(value, new AsyncCallback<Timeslot>() {
			
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(Timeslot result) {
				
				shb.appendEscaped(result.getTime());
				shb.appendHtmlConstant("<div>");
			}
		});
	}
}
