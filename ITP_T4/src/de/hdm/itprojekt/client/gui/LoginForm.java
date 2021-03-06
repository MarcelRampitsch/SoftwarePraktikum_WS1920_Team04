package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.User;

/*
 * 
 * @author VanDuyHo
 * 
 * Login bei dem ein User mit seinem Google-Account sich einloggen kann 
 * 
 */
public class LoginForm extends VerticalPanel {

	//Initialisierung relevanter Variablen
	User user = null;
	Anchor signInLink = new Anchor();
	Image googleSignIn = new Image ("/images/googleLogin.png");
	
	// Erstellen der Login constructor
	public LoginForm(User user) {
		this.user = user;
	}
	
	public void onLoad() {
		//Aufbau des LoginForms sowie styling der Widgets
		signInLink.setHref(user.getURL());
		Label welcomeLabel = new Label("Welcome to Kinofix!");
		Label txtLabel = new Label("Please sign in with your Google account to use Kinofix.");
		HorizontalPanel loginPanel = new HorizontalPanel();
		welcomeLabel.setStyleName("h1");
		googleSignIn.setStylePrimaryName("signinwithgoogle");
		signInLink.getElement().appendChild(googleSignIn.getElement());
		loginPanel.addStyleName("align-center");
		this.setHorizontalAlignment(ALIGN_CENTER);
		this.addStyleName("align-center");
		loginPanel.add(signInLink);
		this.add(welcomeLabel);
		this.add(txtLabel);
		this.add(loginPanel);
		this.addStyleName("login");
	}

}
