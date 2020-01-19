package de.hdm.itprojekt.client.gui;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.EditorAdministration;

public class PresentationCell extends AbstractCell<Presentation> {
	/**
	 * Klasse zur Darstellung von SurveyEntry-Objekten.
	 */
	public void render(Context context, Presentation value, SafeHtmlBuilder sb) {
		// Value can be null, so do a null check..
		if (value == null) {
			return;
		}
		sb.appendHtmlConstant("<table>");
		// TODO: Nicht mit Append Befehl möglich: sb.append(value.getDate());
		sb.append(value.getMovieID());
		sb.append(value.getCinemaID());
		sb.append(value.getTimeslotID());
		sb.appendHtmlConstant("</div>");
	}

	public void onModuleLoad() {

		ProvidesKey<Presentation> keyProvider = new ProvidesKey<Presentation>() {
			public Object getKey(Presentation value) {
				return (value == null) ? null : value.getId();
			}
		};
		
		ListDataProvider<Presentation> dataProvider = new ListDataProvider<Presentation>();
	CellList<Presentation> cellList = new CellList<Presentation>(new PresentationCell(),keyProvider);
	
	cellList.setRowCount(0);
	//cellList.setRowData(0, dataProvider);
	
	VerticalPanel vPanel = new VerticalPanel();
	vPanel.setBorderWidth(1);
	vPanel.setWidth("200");
	vPanel.add(cellList);
	
	RootPanel.get().add(vPanel);
	}

}