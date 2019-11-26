package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;



public class UmfragenForm extends VerticalPanel {
	
	
	
    public UmfragenForm() {
		
	}

    Button umfrageErstellung = new Button("Neue Umfrage erstellen:");

    HorizontalPanel umfrageEintragPanel =new HorizontalPanel();
    
    
	FlexTable umfrageEintrag = new FlexTable();  

	
	public void onLoad() {
		super.onLoad();
		
		
		
		//CSS- Stylename Vergabe
		umfrageErstellung.addStyleName("UmfrageButton");
		this.addStyleName("UmfragenForm");
		
		umfrageEintrag.setStyleName("flexTable");  
		
		
		umfrageEintragPanel.add(umfrageEintrag);
	    
		umfrageEintragPanel.add(umfrageErstellung);

		
		this.add(umfrageErstellung);
		
		this.add(umfrageEintragPanel);
		
		umfrageErstellung.addClickHandler(new OpenUpClickHandler());


		
		
		
		
	}
	
	
       class OpenUpClickHandler implements ClickHandler{
		
		 public void onClick(ClickEvent event) {
			
			UmfragenOpenForm uf = new UmfragenOpenForm();
			uf.umfrageEditForm();
		}
	}
		

	

}
