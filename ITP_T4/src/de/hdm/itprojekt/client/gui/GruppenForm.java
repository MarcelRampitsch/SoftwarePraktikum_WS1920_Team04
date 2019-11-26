package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GruppenForm extends VerticalPanel {

	
	public GruppenForm() {

	}

	Button GruppenErstellung = new Button("Neue Gruppe erstellen");

	public void onLoad() {
		super.onLoad();

		GruppenErstellung.addStyleName("GruppenButton");
		GruppenErstellung.addClickHandler(new openClickHandler());

		this.add(GruppenErstellung);

	}

	private class openClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			//Methode, um das anzeigen der DialogBox(GruppenErstellung) zu realisieren
			GruppenOpenForm open = new GruppenOpenForm();
			open.openGruppenForm();
		}

	}
}
