package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;



public class VerwaltungsForm extends VerticalPanel {
	
	
	Button sp1 = new Button("Spielplan verwalten");
	
	HorizontalPanel test = new HorizontalPanel();


	
	VerwaltungsForm(){
		
	}
	
	//VerwaltungForm(Administrator a){
	//	this.adminUser=a;
//	}
	
	public void onLoad() {
		
		sp1.addStyleName("verwaltung");
		super.onLoad();
		
		test.add(sp1);
		
		this.add(test);
		
		sp1.addClickHandler(new OpenVUpClickHandler());

		
	}
	
	 class OpenVUpClickHandler implements ClickHandler {
	    	
		    
	    	
			public void onClick(ClickEvent event) {
				
			//	greetingService.greetServer(schmeckt.getText(), new AsyncCallback<String>() {

				
				VerwaltungsOpenForm gr = new VerwaltungsOpenForm();
				gr.openSpielPlanForm();

	    }}
	
	
	
	

}
