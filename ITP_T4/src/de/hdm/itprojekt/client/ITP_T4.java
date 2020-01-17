package de.hdm.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Anchor;

import de.hdm.itprojekt.client.gui.EditorForm;
import de.hdm.itprojekt.client.gui.GruppenForm;
import de.hdm.itprojekt.client.gui.admin.AdminForm;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
import de.hdm.itprojekt.shared.LoginAdministrationAsync;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Group;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.Collections;



/**
 * Entry-Point-Klasse des Projekts <b>ITP_T4</b>.
 */
public class ITP_T4 implements EntryPoint {

	EditorAdministrationAsync editorAdministration = ClientSideSettings.getEditorAdministration();
//	protected User currentUser = new User("a");
	LoginAdministrationAsync loginService = ClientSideSettings.getLoginAdministration();
 	//protected User currentUser = new User("a");
	public List <Group> Gruppen;
	ListBox group = new ListBox();
	public Vector <Group> rs = null;
	private Anchor signInLink = new Anchor();

	
	/**
	   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benötigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. 
	   */
	
//	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	@Override
	public void onModuleLoad() {
				
	//	RootPanel.get().add(new EditorForm(currentUser, Gruppen));
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback <User> (){

 			@Override
 			public void onFailure(Throwable caught) {
 				Window.alert("Fehler Login");

 			}

 			@Override
 			public void onSuccess(User result) {
 				if(result.getLoggedIn()) {
 					RootPanel.get().add(new EditorForm(result, Gruppen));

 				}else {
 					loadLogin(result);

 				}


 			}

 		});

 	}
	
public void loadLogin(User userBO) {
		// Assemble login panel.
		signInLink.setHref(userBO.getURL());
		VerticalPanel loginPanel = new VerticalPanel();
		Label loginLabel = new Label("Bitte melden Sie ich sich mit Ihrem Google-Account an, um KinoFix zu nutzen.");
		signInLink.getElement().appendChild(loginLabel.getElement());
	//	loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get().add(loginPanel);

//		und dann?

	}  

}
