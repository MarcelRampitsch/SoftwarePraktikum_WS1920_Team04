package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.client.Callback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.gui.admin.VerwaltungsForm;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Group;

import java.util.List;
import java.util.Vector;


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
	User user = null;
	List <Group> Gruppen;
	/**
	 * <code>header</code>: Oberer Teil des Fensters. Erstreckt sich über die ganze Länge der Anwendung
	 * <code>main</code>: Zentraler Bestandteil. Umschließt alle anderen Panels, außer <code>header</code>
	 * <code>center</code>: Mitte der Anwendung. Enhält die <code>voteForm</code>
	 * <code>east</code>: Rechte Seite der Anwendung. Enhält die Formen <code>umfrageForm</code>
	 * <code>west</code>: Linke Seite der Anwendung. Enhält die Formen <code>gruppenForm</code>
	 */
	
	 HorizontalPanel header = new HorizontalPanel();
	 HorizontalPanel main = new HorizontalPanel();
	 EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	 VerticalPanel center = new VerticalPanel();
	 VerticalPanel west = new VerticalPanel();
	 VerticalPanel east = new VerticalPanel();
	 ListBox group = new ListBox();
	
	/**
	 * 
	 * @param currentUser 
	 * Der aktuelle Nutzer wird der EditorForm übergeben. So können alle anderen Formen diesen bei Bedarf verwenden.
	 * 
	 */
	
	public EditorForm(User user, List <Group> Gruppen){
		this.user = user;
		this.Gruppen = Gruppen;
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
		
	//	VoteForm voteForm = new VoteForm(currentUser);
		UmfragenForm umfrageForm = new UmfragenForm();
		CellListForm celllistform = new CellListForm(user , Gruppen);
	    UmfrageEintragTable umfragen = new UmfrageEintragTable();
		

		/**
		 *  Jede Form wird demjeweiligen Panel hinzugefügt.
		 */
		
	//	center.add(voteForm);
		east.add(umfrageForm);
		west.add(celllistform);
		east.add(umfragen);
		
		
		main.add(center);
		main.add(east);
		main.add(west);
		
		//Button, dessen ClickEvent zum Admin Mode führt.
		Button toAdmin = new Button("Admin", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.Location.replace("/Admin.html");
			}
		});
		
		main.add(toAdmin);
		GruppenForm gruppenForm = new GruppenForm(user);
		main.add(gruppenForm);
		this.add(main);
		
		
	}

}
