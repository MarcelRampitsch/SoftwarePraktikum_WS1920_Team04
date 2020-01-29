package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.User;

/*
 * 
 * @author VanDuyHo
 * 
 * Toolbar die auf jeder Seite am oberen Rand angezeigt wird
 * 
 */
public class ToolbarForm extends HorizontalPanel {
	
	User user = null;
	Anchor logOutLink = new Anchor();
	
	Image logo = new Image("/images/kinofixLogo.png");
	Label l = new HTML("<p>Back to the login screen?</p><br>");
	
	Button yB = new Button("yes");
	Button nB = new Button("no");
	
	VerticalPanel vp = new VerticalPanel();
	HorizontalPanel hp = new HorizontalPanel();	
	
	public ToolbarForm(User user) {
		
		this.user = user;
		
	}
	
	
	/*
	 * onLoad-Methode: Wird ausgeführt, wenn das Widget, dem Browser hinzugefügt wurde. 
	 * Die dieser Klasse dazugehörigen grafischen Elemente werden dem Widget hinzugefügt.
	 * Den Buttons werden deren Funktion entsprechend ClickHandler zugewiesen. 
	 */
	public void onLoad() {
		
		super.onLoad();
		
		Button adminBtn = new Button("Admin");
		adminBtn.addStyleName("toolbar-button");
		
		Button logoutBtn = new Button("Logout");
		logoutBtn.addStyleName("toolbar-button");
		
		adminBtn.addClickHandler(new adminBtnClickHandler());
		logoutBtn.addClickHandler(new logoutBtnClickHandler());
		
		this.addStyleName("toolbar");
		logo.setStylePrimaryName("logo");
		
		this.add(logo);
		this.add(adminBtn);
		this.add(logoutBtn);
		
	}
	
	//ClickHandler der zum Tragen kommt, falls auf den Admin-Button geklickt wird
	private class adminBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
		
			Window.Location.replace("/Admin.html");
			
		}
			
	}
	
	//ClickHandler mit dem ein User sich vom System ausloggen kann
	private class logoutBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			yB.addStyleName("yesButtonStyle");
			nB.addStyleName("noButtonStyle");
			DialogBox db = new DialogBox();
			nB.addClickHandler(new noClickHandler(db));
			logOutLink.setHref(user.getURL());
			logOutLink.getElement().appendChild(yB.getElement());
			db.setText("Logout");
			vp.add(l);	
			hp.add(logOutLink);
			hp.add(nB);
			vp.add(hp);

			db.setGlassEnabled(true);
			db.setAnimationEnabled(true);
			db.center();
			db.show();

			db.add(vp);
			
		}
			
	}
	
	//ClickHandler mit dem sich ein DialogFenster öffnet
	private class noClickHandler implements ClickHandler {

		DialogBox dbox;

		public noClickHandler(DialogBox db) {
			
			this.dbox = db;
			
		}

		public void onClick(ClickEvent event) {

			if (dbox != null) {
				
				dbox.hide();
				dbox.clear();
				dbox.removeFromParent();
				dbox.setAnimationEnabled(false);
				dbox.setGlassEnabled(false);
				
				hp.remove(yB);
				hp.remove(nB);
				vp.remove(l);
				
			}
			
		}

	}
	
}
