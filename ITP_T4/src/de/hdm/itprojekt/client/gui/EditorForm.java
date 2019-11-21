package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.User;


/**
 * 
 * @author DominikThumm
 * EditorForm vereint alle grafischen Elemente dieses Packages, zur kompletten Anzeige
 * des Kino-Umfragen-Verwaltungstools
 */

public class EditorForm extends VerticalPanel {
	
	/* 
	 * currentUser speichert den aktuellen Nutzer
	 */
	protected User currentUser = null;
	
	/**
	 * <code>header</code>: Oberer Teil des Fensters. Erstreckt sich über die ganze Länge der Anwendung
	 * <code>main</code>: Zentraler Bestandteil. Umschließt alle anderen Panels, außer <code>header</code>
	 * <code>center</code>: Mitte der Anwendung. Enhält die <code>voteForm</code>
	 * <code>east</code>: Rechte Seite der Anwendung. Enhält die Formen <code>umfrageForm</code>
	 * <code>west</code>: Linke Seite der Anwendung. Enhält die Formen <code>gruppenForm</code>
	 */
	
	HorizontalPanel header = new HorizontalPanel();
	HorizontalPanel main = new HorizontalPanel();

	VerticalPanel center = new VerticalPanel();
	VerticalPanel west = new VerticalPanel();
	VerticalPanel east = new VerticalPanel();
	
	/**
	 * 
	 * @param currentUser 
	 * Der aktuelle Nutzer wird der EditorForm übergeben. So können alle anderen Formen diesen bei Bedarf verwenden.
	 * 
	 */
	
	public EditorForm(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Panel, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Panel hinzugefügt.
	 */
	
	public void onLoad() {
		super.onLoad();
	
	}

}
