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

import de.hdm.itprojekt.client.gui.EditorForm;
import de.hdm.itprojekt.client.gui.GruppenForm;
import de.hdm.itprojekt.client.gui.admin.AdminForm;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.EditorAdministrationAsync;
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
	protected User currentUser = new User("a");
	public List <Group> Gruppen;
	ListBox group = new ListBox();
	public Vector <Group> rs = null;
	
	/**
	   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benötigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. 
	   */
	
//	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	
	public void onModuleLoad() {
				
		RootPanel.get().add(new EditorForm(currentUser, Gruppen));
				
	}
}
