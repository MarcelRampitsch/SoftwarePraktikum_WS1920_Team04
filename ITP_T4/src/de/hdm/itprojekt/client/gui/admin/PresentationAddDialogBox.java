package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PresentationAddDialogBox extends DialogBox {
	
    VerticalPanel content = new VerticalPanel();

    HorizontalPanel buttoncontent  = new HorizontalPanel();
    Label inhalt  =  new Label("Die ausgewählten Inhalte als eine Präsentation einspeichern?");
	
    Button yes = new Button("yes");
    Button no = new Button("no");
    
    
    public PresentationAddDialogBox () {
    	
    }
    
    public void onLoad() {
    	super.onLoad();
    	
    	content.add(inhalt);
    	
    	buttoncontent.add(yes);
    	yes.addClickHandler(new save());
    	buttoncontent.add(no);
    	no.addClickHandler(new close());
    	
    	content.add(buttoncontent);
    	
    	
    }

    
    
    

	/*
	 * Methode die das Anzeigen der DialogBox realisiert
	 */
	public void openPresentation() {
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
		this.show();	
	}
	
	/*
	 * Methode die das schließen des Fensters realisiert
	 */
	public void closePresentation() {
		this.hide();
		this.clear();
		this.removeFromParent();
		this.setAnimationEnabled(false);
		this.setGlassEnabled(false);
	}
    
    
    private class close implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			closePresentation();			
		}
    	
    	
    }
    
    private class save implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
				Window.alert("EINGABE WURDE GESICHERT");
		}
}
}