package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Presentation;

import java.util.Collections;
import java.util.List;
import java.util.Vector;


/**
 * 
 * @author DominikThumm
 * EditorForm vereint alle grafischen Elemente dieses Packages, zur kompletten Anzeige
 * des Kino-Umfragen-Verwaltungstools
 */

public class EditorForm extends VerticalPanel {
	
	//Initialisierung relevanter Variablen
	User user = null;
	Group deleteGroup= null;
	List<Presentation> Surveys;
	List <Group> Gruppen;
	Vector<Group> rs;
	
	/**
	 * <code>header</code>: Oberer Teil des Fensters. Erstreckt sich über die ganze Länge der Anwendung
	 * <code>main</code>: Zentraler Bestandteil. Umschließt alle anderen Panels, außer <code>header</code>
	 * <code>center</code>: Mitte der Anwendung. Enhält die <code>voteForm</code>
	 * <code>east</code>: Rechte Seite der Anwendung. Enhält die Formen <code>umfrageForm</code>
	 * <code>west</code>: Linke Seite der Anwendung. Enhält die Formen <code>gruppenForm</code>
	 */
	//Initialisierung relevanter Widgets
	HorizontalPanel header = new HorizontalPanel();
	HorizontalPanel main = new HorizontalPanel();
	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
	VerticalPanel center = new VerticalPanel();
	VerticalPanel west = new VerticalPanel();
	VerticalPanel east = new VerticalPanel();
	ListBox group = new ListBox(); 
	VerticalPanel contentPanel = new VerticalPanel();
	
	/**
	 * 
	 * @param currentUser 
	 * Der aktuelle Nutzer wird der EditorForm übergeben. So können alle anderen Formen diesen bei Bedarf verwenden.
	 * 
	 */
	
	// Erstellung der <code>EditorForm</code> Constructoren
	public EditorForm(User user, List <Group> Gruppen){
		this.user = user;
		this.Gruppen = Gruppen;
	}
	
	public EditorForm(User user, Group deleteGroup) {
		this.user = user;
		this.deleteGroup = deleteGroup;
	}
	
	public EditorForm(User user) {
		this.user = user;
	}
	
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Panel, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Panel hinzugefügt.
	 */
	CellListForm celllistform ;
	
	

	public void onLoad() {
		super.onLoad();
		
		if(user.getNickname()== "zzzzzzzz") {
			RegistrierungsForm form = new RegistrierungsForm(user);
			form.addStyleName("RegistrierungsForm");
			this.add(new ToolbarForm(user));
			this.add(new FooterForm());

			this.add(form);
			this.addStyleName("center");
			
			
		}else {
		
		//Methoden aufruf um alle Gruppen eines User zu bekommen
		editorAdministration.getAllGroupByUserID(user, new AsyncCallback<Vector<Group>>() { 
				
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(Vector<Group> result) {
				rs = result;
				// Vector result in eine List "Gruppen" umwandeln
				Gruppen = Collections.list(result.elements());
				Gruppen.remove(deleteGroup);
				// Widget celllistform aufrufen
				celllistform = new CellListForm(user , Gruppen);
//				UmfragenTable u1 = new UmfragenTable(user, Surveys);
				contentPanel.add(celllistform);
				contentPanel.addStyleName("content");
				//Methoden aufruf um alle Gruppen zu finden in denen der User ein Mitglied ist
				editorAdministration.getAllGroupsIamMemberFrom(user, new AsyncCallback<Vector<Group>>() { 
						
						@Override
						public void onFailure(Throwable caught) {
						//	Window.alert("Fehler liste");
						}

						@Override
						public void onSuccess(Vector<Group> result) {
							rs = result;
							//ListDataProvider des celllistform erstellt 
							ListDataProvider<Group> dp = celllistform.getDataProvider();
							List <Group> liste = dp.getList();
							for(Group g :result) {
								//List mit Group objekten befüllen und zuvor prüfen ob diese bereits in der Liste vorhanden sind
								if(!liste.contains(g)) {
									liste.add(g);
								}
							}
						}
					});
				}
			});
		this.add(new ToolbarForm(user));
		this.add(contentPanel);
		this.add(new FooterForm());
		this.addStyleName("center");
		}
	}
}