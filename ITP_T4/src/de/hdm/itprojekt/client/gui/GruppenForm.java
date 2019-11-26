package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

 
	
	public class GruppenForm extends DialogBox{
		

		VerticalPanel inhalt = new VerticalPanel();
		
		
		Label gruppenerstellung = new Label("Gruppenerstellung:");
		Label gruppenname = new Label("Gruppenname");
		Label nickname = new Label("Nickname");
		
		Button edit = new Button("editieren");
		Button close  = new Button("X");
		
		TextBox tb =new TextBox();
		TextBox tb2 = new TextBox();
		
		Button speichern = new Button("sichern");
		
		TextArea ta = new TextArea();

		
		
		public GruppenForm() {
			
			inhalt.add(close);
			//close.addClickHandler(new closegruppenform());
			inhalt.add(gruppenerstellung);
			gruppenerstellung.addStyleName("Überschrift");
			inhalt.add(gruppenname);
			inhalt.add(tb);
			
			inhalt.add(nickname);
			inhalt.add(tb2);
			
			inhalt.add(speichern);
			inhalt.add(edit);
						
			
//			speichern.addClickHandler(new sichernhandler());
//			this.add(inhalt);


			
		}
		
		public void openGruppenForm() {
			this.setGlassEnabled(true);
			this.setAnimationEnabled(true);
			this.center();
			this.show();	
		}
		
		
		public void closeGruppenForm() {
			this.hide();
		}

		

		public void onLoad() {
			
	}
		
		
//		private class closegruppenform implements ClickHandler{
	//
//			@Override
//			public void onClick(ClickEvent event) {
//				closeGruppenForm();
//				
//			}
	//
	//	
//			
//		}
	//	
//		private class sichernhandler implements ClickHandler{
	//
//			@Override
//			public void onClick(ClickEvent event) {
//				Window.alert("EINGABE GESICHERT");
//			}
			
	}


		




