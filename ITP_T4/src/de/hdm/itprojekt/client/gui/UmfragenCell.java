package de.hdm.itprojekt.client.gui;

import java.sql.Date;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.Vote;;

public class UmfragenCell extends AbstractCell<SurveyEntry> {
	
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	CinemaGroup cg = new CinemaGroup();
	Cinema c = new Cinema();
	Movie m = new Movie();
	Timeslot t = new Timeslot();
	Presentation p = new Presentation();
	String date = new String();
	Vote v = new Vote();

	@Override
	public void render(Context context, SurveyEntry value, SafeHtmlBuilder sb) {
		// TODO Auto-generated method stub
		if(value == null) {
			return;
		}
		
		editorAdministration.getCinemaBySurveyEntry(value, new AsyncCallback<Cinema>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Cinema result) {
				c = result;
			}
		});
		
		editorAdministration.getMovieBySurveyEntry(value, new AsyncCallback<Movie>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Movie result) {
				m = result;
			}
		});
		
		editorAdministration.getTimeslotBySurveyEntry(value, new AsyncCallback<Timeslot>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Timeslot result) {
				t = result;
			}
		});
		
		editorAdministration.getPresentationBySurveyEntry(value, new AsyncCallback<Presentation>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Presentation result) {
				p = result;
			}
		});
		
		sb.appendHtmlConstant("<div>");
			
		// Kinoteil des SurvyEntrys
			sb.appendHtmlConstant("<div>");
			sb.appendEscaped(cg.getName());
			sb.appendEscaped(c.getName());
			sb.appendEscaped(c.getLocation());
			sb.appendHtmlConstant("</div>");
		
		// Filmteil des SurvyEntrys
			sb.appendHtmlConstant("<div>");
			sb.appendEscaped(m.getName());
			sb.appendHtmlConstant("</div>");
			
		// Zeit des SurvyEntrys	
			sb.appendHtmlConstant("<div>");
			sb.appendEscaped(p.getDate().toString());
			sb.appendEscaped(t.getTime());
			sb.appendHtmlConstant("</div>");
		
		//Ergebniss des SurvyEntrys
	      
	    sb.appendHtmlConstant("</div>");
	}
}

 