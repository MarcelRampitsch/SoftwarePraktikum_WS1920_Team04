package de.hdm.itprojekt.client.gui;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Vote;

public class PrepareSurveyEntry {
	
	
	/**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>EditorAdministration</code>.
	   */
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	
	//Erstellung der notwendigen Widgets und Variablen
	User user = new User();
	CinemaGroup cg = new CinemaGroup();
	Cinema c = new Cinema();
	Movie m = new Movie();
	Timeslot t = new Timeslot();
	Presentation p = new Presentation();
//	String date = new String();
	Vote v = new Vote();
	int voteResulte = 0;
	SafeHtmlBuilder shb = new SafeHtmlBuilder();
	
	List<SurveyEntry> UmfragenEintrag;
	SurveyEntry se;
	List<SafeHtml> data;
	int outstandingCalls = 4;
	UmfragenTable ut;
	Vector<Vote>votes;
	
	
	public PrepareSurveyEntry () {
		
	}
	
	public PrepareSurveyEntry (SurveyEntry se, List<SafeHtml> data, UmfragenTable ut, User user) {
		this.se = se;
		this.data = data;
		this.ut = ut;
		this.user = user;
		loadData(se);
	}
	
	public void loadData(SurveyEntry value) {
		
		editorAdministration.getCinemaBySurveyEntry(value, new AsyncCallback<Cinema>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				createSafeHtml();
			}

			@Override
			public void onSuccess(Cinema result) {
				c = result;
				createSafeHtml();
				
			}
		});
		
		editorAdministration.getMovieBySurveyEntry(value, new AsyncCallback<Movie>() {
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				createSafeHtml();
			}

			@Override
			public void onSuccess(Movie result) {
				m = result;
				createSafeHtml();
				
			}
		});
		
		editorAdministration.getTimeslotBySurveyEntry(value, new AsyncCallback<Timeslot>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				createSafeHtml();
			}

			@Override
			public void onSuccess(Timeslot result) {
				t = result;
				createSafeHtml();
				
			}
		});
		
		editorAdministration.getPresentationBySurveyEntry(value, new AsyncCallback<Presentation>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				createSafeHtml();
			}

			@Override
			public void onSuccess(Presentation result) {
				p = result;
				createSafeHtml();
				
			}
		});
		
	
		editorAdministration.getAllVoteBySurveyEntryID(value, new AsyncCallback<Vector<Vote>>() {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			createSafeHtml();
		}

		@Override
		public void onSuccess(Vector <Vote> result) {
			votes = result;
			createSafeHtml();
			
		}
	});
	
}
	


	
	@SuppressWarnings("deprecation")

	public void createSafeHtml() {
		
		//Window.alert("alertini");
		if(outstandingCalls > 0) {
			outstandingCalls -= 1;
			return; 
		}	
		
		boolean voted = false;
		Vote vote =null;
		
		for (int i = 0; i < votes.size(); i++) {
			if(votes.elementAt(i).getVoteResult()==1) {
			voteResulte += 1;
			}else {
			voteResulte -= 1;	
			}
		}
		
		if(votes == null) {
			

		}else {
		for(int i=0; i<votes.size(); i++ ) {
			if(votes.elementAt(i).getUserID()==ut.user.getId()) {
				voted= true;
				vote= votes.elementAt(i);
			}
		}
		}
		shb.appendHtmlConstant("<div ");
		shb.appendHtmlConstant("<div data-userID=\""+ut.user.getId()+"\" data-seID=\""+se.getId()+ "\" >");
		// Kinoteil des SurvyEntrys
		shb.appendHtmlConstant("<div>");
		shb.appendEscaped(c.getName() + " in ");
		shb.appendEscaped(c.getLocation());
		shb.appendHtmlConstant("</div>");
			
		
		// Filmteil des SurvyEntrys
		shb.appendHtmlConstant("<div>");
		shb.appendEscaped(m.getName());
		shb.appendHtmlConstant("</div>");
			
		// Zeit des SurvyEntrys	
		shb.appendHtmlConstant("<div>");
		shb.appendEscaped(p.getDate().toString());
		shb.appendEscaped(" um " + t.getTime());
		shb.appendHtmlConstant("</div>");
		
		//Ergebniss des SurvyEntrys
	    
		//Voting Buttons
		RadioButton rb_ja = new RadioButton("Ja");
		rb_ja.setName(se.getId()+"");
		RadioButton rb_nein = new RadioButton("Nein");
		rb_nein.setName(se.getId()+"");
		Button save = new Button("Save");
		
		if(voted) {
			save.setEnabled(false);
			rb_ja.setEnabled(false); // geht nicht
			rb_nein.setEnabled(false);
			if(vote != null ) {
				if(vote.getVoteResult()>0) {
					rb_ja.setValue(true); // geht nicht
				}else {
					rb_nein.setValue(true);
				}
			}
		}
		
		
		
		save.addClickHandler(new saveHandler());
		shb.appendEscaped("Ja");
		shb.appendHtmlConstant("<input type='radio' name='"+se.getId()+"'>");//rb_ja.toString());
		shb.appendEscaped("Nein");
		shb.appendHtmlConstant("<input type='radio' name='"+se.getId()+"'>");
		//shb.appendHtmlConstant(rb_nein.toString());
		shb.appendHtmlConstant(save.toString());
		shb.appendHtmlConstant("</div>");
		shb.appendHtmlConstant("<div>");
		shb.appendHtmlConstant("The vote result is " + voteResulte);
		shb.appendHtmlConstant("</div>");
		shb.appendHtmlConstant("</div>");
		data.add(shb.toSafeHtml());
		ut.updateTable();
		CheckboxCell vb;
	}

	private class saveHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
		}
	}
}
