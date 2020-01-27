package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author serhatulus DialogBox, die angezeigt wird, wenn der Admin den
 *         SpielPlan verwalten möchte. Die Klasse enthält entsprechende
 *         ClickHandler & Methoden zum Bestätigen oder Abbrechen der Aktion.
 */
public class VerwaltungsOpenForm extends DialogBox {

	//Erstellung notwendiger Widget und Panel
	VerticalPanel inhalt = new VerticalPanel();

	Button close = new Button("X");

	Label admin = new Label("AdminVerwaltung:");
	Label kino = new Label("Kino anlegen:");

	TextBox tb = new TextBox();

	Label film = new Label("Film anlegen:");
	Label auswahl = new Label("Kino auswählen:");

	ListBox lb = new ListBox();

	TextBox tb2 = new TextBox();

	Label spielzeit = new Label("Spielzeit anlegen:");
	Label filmauswahl = new Label("Film auswählen:");

	ListBox auswahl2 = new ListBox();

	TextBox tb3 = new TextBox();

	Button sichern = new Button("Eingabe Sichern");

	public VerwaltungsOpenForm() {

	}

	public void onLoad() {
		super.onLoad();

		close.addStyleName("presentationEditCloser");
		sichern.addStyleDependentName("ff");

		inhalt.add(close);
		close.addClickHandler(new schließen());

		inhalt.add(admin);
		inhalt.add(kino);
		inhalt.add(tb);
		inhalt.add(film);
		inhalt.add(auswahl);

		auswahl2.addItem("test");

		inhalt.add(lb);
		lb.addItem("UfaPalast");

		inhalt.add(tb2);

		inhalt.add(spielzeit);
		inhalt.add(filmauswahl);
		inhalt.add(auswahl2);

		inhalt.add(tb3);

		inhalt.add(sichern);
		sichern.addClickHandler(new sichern());

		// Füge die ganze Klasse auf das VerticalPanel hinzu
		this.add(inhalt);

	}

	public void closeForm() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}

	public void openSpielPlanForm() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();
	}

	private class schließen implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			closeForm();
		}

	}

	private class sichern implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Window.alert("Spielplan gesichert");
		}

	}
}
