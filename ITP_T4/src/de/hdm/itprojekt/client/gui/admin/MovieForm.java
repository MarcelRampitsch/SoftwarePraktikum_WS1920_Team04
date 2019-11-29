package de.hdm.itprojekt.client.gui.admin;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Dominik Thumm, SerhatUlus
 *
 */

public class MovieForm  extends VerticalPanel{
	
	
	TextBox moviebox = new TextBox();
    Label movieLabel = new Label("Movie");
    
    Button movieEdit = new Button("Edit");
    Button movieNew = new Button("New");
    Button movieDelete = new Button("Delete");
    
    Button movieAdd = new Button("+");
    
    HorizontalPanel movieaddbox = new HorizontalPanel();
    HorizontalPanel buttonbox = new HorizontalPanel();

    
    public MovieForm() {
    	
    }

    
    public void onLoad() {
    	
    	super.onLoad();
    	
    	this.add(movieLabel);
    	
    	movieaddbox.add(moviebox);
    	movieaddbox.add(movieAdd);
    	
    	buttonbox.add(movieEdit);
    	buttonbox.add(movieNew);
    	buttonbox.add(movieDelete);
    	
    	this.add(movieaddbox);
    	this.add(buttonbox);
     	
    	
    	
    }

}
