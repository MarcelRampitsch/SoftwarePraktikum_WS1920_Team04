package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	
	Label l = new HTML("<p>Möchten Sie zurück zum Login?</p><br>");
	
	Button yB = new Button("Ja");
	Button nB = new Button("Nein");
	
	VerticalPanel vp = new VerticalPanel();
	HorizontalPanel hp = new HorizontalPanel();	
	
	public ToolbarForm(User user) {
		
		this.user = user;
		
	}
	
	public void onLoad() {
		
		super.onLoad();
		
		Button adminBtn = new Button("Admin");
		adminBtn.addStyleName("toolbar-button");
		
		Button logoutBtn = new Button("Logout");
		logoutBtn.addStyleName("toolbar-button");
		
		adminBtn.addClickHandler(new adminBtnClickHandler());
		logoutBtn.addClickHandler(new logoutBtnClickHandler());
		
		this.addStyleName("toolbar");
		
		this.add(adminBtn);
		this.add(logoutBtn);
		
	}
	
	private class adminBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
		
			Window.Location.replace("/Admin.html");
			
		}
			
	}
	
	private class logoutBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			

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
