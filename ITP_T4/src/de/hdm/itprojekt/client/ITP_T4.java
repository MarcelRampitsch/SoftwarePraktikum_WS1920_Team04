package de.hdm.itprojekt.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.gui.EditorForm;
import de.hdm.itprojekt.client.gui.LoginForm;
import de.hdm.itprojekt.shared.LoginAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;


/**
 * Entry-Point-Klasse des Projekts <b>ITP_T4</b>.
 */
public class ITP_T4 implements EntryPoint {

	 /*
     * Zunächst weisen wir der EditorAdministration eine editor-Instanz zu.
     */

	LoginAdministrationAsync loginService = ClientSideSettings.getLoginAdministration();
	private Anchor signInLink = new Anchor();
	List<Group> Gruppen;
	/**
	   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benötigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. 
	   */
	
	@Override
	public void onModuleLoad() {
				
		// Aufrufen der LoginService Methode um den Eingelogten User zu bekommen
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback <User> (){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler Login");	
			}

			@Override
			
			public void onSuccess(User result) {
				// Wenn der User bereits eingeloggt ist wird das EditorForm aufgerufen
				if(result.getLoggedIn()) {
					RootPanel.get().add(new EditorForm(result, Gruppen));
				
				// Wenn dies nicht der Fall ist wird das LoginForm aufgerufen
				}else {
					RootPanel.get().add(new LoginForm(result));
//					loadLogin(result);	
				}
			}
		});
	}
	
	public void loadLogin(User userBO) {
		// Aufbauen des Login Panels
		signInLink.setHref(userBO.getURL());
		VerticalPanel loginPanel = new VerticalPanel();
		Label loginLabel = new Label("Bitte melden Sie ich sich mit Ihrem Google-Account an, um KinoFix zu nutzen.");
		signInLink.getElement().appendChild(loginLabel.getElement());
	//	loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get().add(loginPanel);
	}  
}