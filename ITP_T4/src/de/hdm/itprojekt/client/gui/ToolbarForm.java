package de.hdm.itprojekt.client.gui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.itprojekt.client.gui.admin.AdminForm;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;

public class ToolbarForm extends HorizontalPanel {
	
	User user = null;

	int a = 0;
	
	List <Group> Gruppen;
	
	public ToolbarForm() {
		
		
		
	}
	
	public void onLoad() {
		
		super.onLoad();
		
		Button adminBtn = new Button("Admin");
		adminBtn.addStyleName("toolbar-button");
		
		Button editorBtn = new Button("Editor");
		editorBtn.addStyleName("toolbar-button");
		
		adminBtn.addClickHandler(new adminBtnClickHandler());
		editorBtn.addClickHandler(new editorBtnClickHandler());
		
		this.addStyleName("toolbar");
		
		this.add(adminBtn);
		this.add(editorBtn);
		
	}
	
	private class adminBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			RootPanel.get().clear();
			AdminForm adminForm = new AdminForm(user, a);
			RootPanel.get().add(adminForm);
			
		}
			
	}
	
	private class editorBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			RootPanel.get().clear();
			EditorForm editorForm = new EditorForm(user, Gruppen);
			RootPanel.get().add(editorForm);
			
		}
			
	}
	
}
