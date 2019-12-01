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
	
	private HorizontalPanel header = new HorizontalPanel();
	private HorizontalPanel main = new HorizontalPanel();

	private VerticalPanel center = new VerticalPanel();
	private VerticalPanel west = new VerticalPanel();
	private VerticalPanel east = new VerticalPanel();
	
	/**
	 * 
	 * @param currentUser 
	 * Der aktuelle Nutzer wird der EditorForm übergeben. So können alle anderen Formen diesen bei Bedarf verwenden.
	 * 
	 */
	
	public EditorForm(){
	}
	
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Panel, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Panel hinzugefügt.
	 */
	
	public void onLoad() {
		super.onLoad();
		
		
		/*
		 * CSS-StyleName-Vergabe, um Panels direkt anzusprechen.
		 */
		
		this.setStylePrimaryName("EditorForm");
		header.setStylePrimaryName("Header");
		main.setStylePrimaryName("Main");
		center.setStylePrimaryName("Center");
		west.setStylePrimaryName("West");
		east.setStylePrimaryName("East");
		
		
		/*
		 * Instanzierung aller nötigen Formen, die in der EditorForm angezeigt werden sollen. 
		 */
		
		GruppenForm gruppenForm = new GruppenForm();
	//	VoteForm voteForm = new VoteForm(currentUser);
		UmfragenForm umfrageForm = new UmfragenForm();
		CellListForm celllistform = new CellListForm();
	
		

		/**
		 *  Jede Form wird demjeweiligen Panel hinzugefügt.
		 */
		
	//	center.add(voteForm);
		east.add(umfrageForm);
		west.add(gruppenForm);
		west.add(celllistform);
		
		
		main.add(center);
		main.add(east);
		main.add(west);
		
		this.add(main);

		
		
	}

}
