package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.client.Callback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.gui.admin.VerwaltungsForm;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.SurveyEntry;

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
	
	/* 
	 * currentUser speichert den aktuellen Nutzer
	 */
	User user = null;
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
	
	public EditorForm(User user, List <Group> Gruppen){
		this.user = user;
		this.Gruppen = Gruppen;
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
	//	Window.alert(user.getEmail());
		
		/*
		 * CSS-StyleName-Vergabe, um Panels direkt anzusprechen.
		 */
		
//		this.setStylePrimaryName("EditorForm");
//		header.setStylePrimaryName("Header");
//		main.setStylePrimaryName("Main");
//		center.setStylePrimaryName("Center");
//		west.setStylePrimaryName("West");
//		east.setStylePrimaryName("East");
		
	/*	editorAdministration.getUserByEmail(user, new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
			Window.alert("GetUser Fehler");
			}
			@Override
			public void onSuccess(User result) {
			user = result; */
			editorAdministration.getAllGroupByUserID(user, new AsyncCallback<Vector<Group>>() { 
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Vector<Group> result) {
					rs = result;
					Gruppen = Collections.list(result.elements());
					celllistform = new CellListForm(user , Gruppen);
//					UmfragenTable u1 = new UmfragenTable(user, Surveys);
					contentPanel.add(celllistform);
					contentPanel.addStyleName("content");
					editorAdministration.getAllGroupsIamMemberFrom(user, new AsyncCallback<Vector<Group>>() { 
						
						@Override
						public void onFailure(Throwable caught) {
						//	Window.alert("Fehler liste");
							
						}

						@Override
						public void onSuccess(Vector<Group> result) {
							rs = result;
							
							ListDataProvider<Group> dp = celllistform.getDataProvider();
							List <Group> liste = dp.getList();
							for(Group g :result) {
								if(!liste.contains(g)) {
									liste.add(g);

								}else {
							//		Window.alert("bereits in der Liste");
								}

							}
					//		west.add(umfragen);
						}
						
					});
			//		west.add(umfragen);
				}
				
			});
			
	//		}
	//	});
		
		/*
		 * Instanzierung aller nötigen Formen, die in der EditorForm angezeigt werden sollen. 
		 */
		
	//	VoteForm voteForm = new VoteForm(currentUser);
		
		

		/**
		 *  Jede Form wird demjeweiligen Panel hinzugefügt.
		 */
		
	//	center.add(voteForm);
		
		
		
//		main.add(center);
//		main.add(east);
//		main.add(west);
		
		//Button, dessen ClickEvent zum Admin Mode führt.
//		Button toAdmin = new Button("Admin", new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				Window.Location.replace("/Admin.html");
//			}
//		});
		
		//Button, dessen ClickEvent zum Admin Mode führt.
	/*		Button logout = new Button("Logout", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						Window.Location.assign(user.getURL());
					}
				});  */
//		toAdmin.addStyleName("AdminButton");
//		
//		Anchor logOutLink = new Anchor("Logout");
//		
//		logOutLink.setHref(user.getURL());
		
		
		
		
		
//		header.add(toAdmin);
//		header.add(logOutLink);
//		GruppenForm gruppenForm = new GruppenForm(user);
//		main.add(gruppenForm);
		

//		this.add(header);
//		this.add(main);
			
		this.add(new ToolbarForm(user));
		this.add(contentPanel);
		this.add(new FooterForm());
		
		
	}

}