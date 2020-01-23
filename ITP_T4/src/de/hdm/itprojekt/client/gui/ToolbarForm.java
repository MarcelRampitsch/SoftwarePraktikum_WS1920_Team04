package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.itprojekt.client.gui.admin.AdminForm;
import de.hdm.itprojekt.shared.bo.User;

public class ToolbarForm extends HorizontalPanel {
	
	User user = null;
	
	int a = 0;
	
	public ToolbarForm() {
		
		
		
	}
	
	public void onLoad() {
		
		super.onLoad();
		
		Button adminBtn = new Button("Admin");
		adminBtn.addStyleName("button");
		
		Button createGroupBtn = new Button("Create Group");
		createGroupBtn.addStyleName("button");
		
		Button groupViewBtn = new Button("Group View");
		groupViewBtn.addStyleName("button");
		
		adminBtn.addClickHandler(new adminBtnClickHandler());
		
	}
	
	private class adminBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			RootPanel.get().clear();
			AdminForm adminForm = new AdminForm(user, a);
			RootPanel.get().add(adminForm);
			
		}
			
	}
	
}
