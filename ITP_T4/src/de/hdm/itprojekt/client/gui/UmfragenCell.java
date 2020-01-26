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
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.Vote;;

public class UmfragenCell extends AbstractCell<SurveyEntry> {
	
	
	/**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>EditorAdministration</code>.
	   */
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	
	//Erstellung der notwendigen Widget sowie Variablen
	CinemaGroup cg = new CinemaGroup();
	Cinema c = new Cinema();
	Movie m = new Movie();
	Timeslot t = new Timeslot();
	Presentation p = new Presentation();
//	String date = new String();
	Vote v = new Vote();
	SafeHtmlBuilder shb;

	
	int outstandingCalls = 4;

	@Override
	public void render(Context context, SurveyEntry value, SafeHtmlBuilder sb) {
		// TODO Auto-generated method stub
		if(value == null) {
			return;
		}
		shb=sb;
		shb.appendEscapedLines("Start");
		
		editorAdministration.getCinemaBySurveyEntry(value, new AsyncCallback<Cinema>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Cinema result) {
				c = result;
				//reenderer2 ( );
				shb.appendEscapedLines("Testasdadadqwklsd1111");
				
			}
		});
		
		shb = sb;

		shb.appendHtmlConstant("<div>");
		shb.appendHtmlConstant("Umfrage: ");
		
		editorAdministration.getCinemaBySurveyEntry(value, new AsyncCallback<Cinema>() {

			@Override
			public void onSuccess(Cinema result) {
				shb.appendHtmlConstant(" Gruppe: ");
				shb.appendEscaped(result.getName());
				shb.appendHtmlConstant("</div>");
				
				
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

		
//		editorAdministration.getMovieBySurveyEntry(value, new AsyncCallback<Movie>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onSuccess(Movie result) {
//				m = result;
//				//reenderer2 ( );
//				shb.appendEscapedLines("m");
//				
//			}
//		});
//		
//		editorAdministration.getTimeslotBySurveyEntry(value, new AsyncCallback<Timeslot>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onSuccess(Timeslot result) {
//				t = result;
//				reenderer2 ( );
//				shb.appendEscapedLines("t");
//				
//			}
//		});
//		
//		editorAdministration.getPresentationBySurveyEntry(value, new AsyncCallback<Presentation>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onSuccess(Presentation result) {
//				p = result;
//				reenderer2 ( );
//				shb.appendEscapedLines("p");
//				
//			}
//		});
//		
//		shb.appendEscapedLines("Start");
		
	}
	
	public void reenderer2 () {
		
		if(outstandingCalls >0) {
			outstandingCalls--;
			return; 
		}

		
			
		
		shb.appendHtmlConstant("<div>");
		// Kinoteil des SurvyEntrys
		shb.appendHtmlConstant("<div>");
		shb.appendEscaped(cg.getName() + " in ");
		shb.appendEscaped(c.getName());
		shb.appendEscaped(c.getLocation());
		shb.appendHtmlConstant("</div>");
			
		
		// Filmteil des SurvyEntrys
		shb.appendHtmlConstant("<div>");
		shb.appendEscaped(m.getName());
		shb.appendHtmlConstant("</div>");
			
		// Zeit des SurvyEntrys	
		shb.appendHtmlConstant("<div>");
//			sb.appendEscaped(p.getDate().toString());
		shb.appendEscaped(t.getTime());
		shb.appendHtmlConstant("</div>");
		
		//Ergebniss des SurvyEntrys
	      
		shb.appendHtmlConstant("</div>");
		
	}
}

 