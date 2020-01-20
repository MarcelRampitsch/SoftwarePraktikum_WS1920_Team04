package de.hdm.itprojekt.client.gui;

import java.sql.Date;
import java.sql.Time;
import java.util.Vector;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.Timeslot;;

public class UmfragenCell extends AbstractCell<SurveyEntry> {
	
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	Cinema c = new Cinema();
	Movie m = new Movie();
	Timeslot t = new Timeslot();
	Presentation p = new Presentation();
	
	
	interface Templates extends SafeHtmlTemplates{
		@SafeHtmlTemplates.Template("\"<div style=\\\"{0}\\\">{1}</div>\"")
		SafeHtml cell(SafeStyles styles, SafeHtml value);
	}
	
	private static Templates templates = GWT.create(Templates.class);
	
	@Override
	public void render(Context context, SurveyEntry value, SafeHtmlBuilder sb) {
		// TODO Auto-generated method stub
		if(value == null) {
			return;
		}
		editorAdministration.getAllPresentationBySurveyEntry(value, new AsyncCallback<Cinema>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Cinema result) {
				c = result;
			}
		});
		
		sb.appendHtmlConstant("<table>");

	      // Add the contact image.
	      sb.appendHtmlConstant("<tr><td rowspan='2'>");
	      sb.appendHtmlConstant("</td>");

	      // Add the name and address.
	      sb.appendHtmlConstant("<td style='font-size:95%;'>");
	      sb.appendEscaped(c.getName());
	      sb.appendHtmlConstant("</td></tr><tr><td>");
	      sb.appendEscaped(c.getLocation());
	      sb.appendHtmlConstant("</td></tr></table>");
	}
}

 