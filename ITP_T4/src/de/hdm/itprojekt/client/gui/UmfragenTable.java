package de.hdm.itprojekt.client.gui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.shared.AdminAdministrationAsync;
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;

public class UmfragenTable extends VerticalPanel {
	
	AdminAdministrationAsync adminAdministration = ClientSideSettings.getAdminAdministration();

	User user = null;
	List<SurveyEntry> eintrag;
	Survey s = null;
	
	Button back = new Button("<-");
	Label name = new Label();
	UmfragenCell cell = new UmfragenCell();
	CellList<SurveyEntry> list = new CellList<SurveyEntry>(cell);
	VerticalPanel main = new VerticalPanel();
	
	
	public UmfragenTable() {
	}
	
	public UmfragenTable(User user, List<SurveyEntry> eintrag, Survey s) {
		this.user = user;
		this.eintrag = eintrag;		
		this.s = s;
	}
	
	 //erstellen des Data Provider
	   ListDataProvider<SurveyEntry> Umfrageeintrag = new ListDataProvider<SurveyEntry>();
	
	
	public void onLoad() {
		super.onLoad();
		name.setText(s.getName());
		main.add(back);
		main.add(name);
		main.add(list);
		back.addClickHandler(new backHandler());
		this.add(main);
		Window.alert("" + eintrag.get(0).getId());
		list.setRowCount(eintrag.size(), true);
		list.setRowData(0, eintrag);
	}
	
	public ListDataProvider<SurveyEntry> getDataProvider() {
 		return this.Umfrageeintrag;
 	}
	
	private class backHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get().clear();
			EditorForm form = new EditorForm(user);
			RootPanel.get().add(form);
		}
	 }
}
