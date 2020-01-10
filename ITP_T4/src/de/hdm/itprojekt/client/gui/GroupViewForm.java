package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


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
	
	public GroupViewForm() {
		
	}
	
	public void onLoad() {
		
	}
	
}
