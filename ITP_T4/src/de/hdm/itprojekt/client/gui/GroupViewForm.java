package de.hdm.itprojekt.client.gui;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.shared.bo.Survey;


/*
 * 
 * @author VanDuyHo
 * 
 */

public class GroupViewForm extends VerticalPanel {

	Label groupNameLabel = new Label("Gruppenname:");
	TextBox groupNameTB = new TextBox();
	Label memberNamesLabel = new Label("Mitglieder:");
	TextBox memberNamesTB = new TextBox();
	Label surveyNameLabel = new Label("Umfrage:");
	TextBox surveyNameTB = new TextBox();
	Button newSurveyButton = new Button("Neu");
	
	ListBox memberLB = new ListBox();
	HorizontalPanel surveyPanel = new HorizontalPanel();
	
	List<Survey> Umfragen;
	
	public GroupViewForm() {
		
	}
	
	public void onLoad() {
		
		super.onLoad();
		
		final CellTable<Survey> table = new CellTable<Survey>();
		
		ListDataProvider<Survey> dataProvider = new ListDataProvider<Survey>();
		
	}
	
}
