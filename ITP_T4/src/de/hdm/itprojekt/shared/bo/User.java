package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

/*
 * Realisierung der Anwenderklasse. Anwender besitzen einen Nicknamen, eine E-Mail Adresse.
 * Zudem wird überprüft ob der Anwender eingeloggt ist.
 */
public class User extends BusinessObject {
	
	/*
	 * SerialVersionUID zur eindeutigen Identifikation der Version der serialisierbaren Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Der Nickname des Anwenders
	 */
	private String nickname;
	
	/*
	 * Die E-Mail Adresse des Anwenders
	 */
	private String email;
	
	/*
	 * Der Login Status des Anwenders
	 */
	private boolean loggedIn = false;
	
	/*
	 * Die URL
	 */
	private String url;
	
	/*
	 * Konstruktoren
	 */
	public User(int id, String nickname, String email, Timestamp creationDate) {
		super(id, creationDate);
		this.nickname = nickname;
		this.email = email;
	}
	
	public User(String nickname, String email) {
		this.nickname = nickname;
		this.email = email;
	}
	
	public User(String email) {
		this.email = email;
	}
	
	public User(int id) {
		super(id);
	}
	
	/*
	 * Der Default Konstruktor
	 */
	public User() {
	}
	
	/*
	 * Auslesen des Nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/*
	 * Setzen des Nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/*
	 * Auslesen der E-Mailadresse
	 */
	public String getEmail() {
		return email;
	}

	/*
	 * Setzen der E-Mailadresse
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * Anwender als eingeloggt setzen.
	 */
	public void setIsLoggedIn(boolean b) {
		this.loggedIn=b;	
	}
	
	/*
	 * Auslesen des Loginstatus
	 */
	public boolean getLoggedIn() {
		return this.loggedIn;	
	}
	
	/*
	 * Setzen der URL
	 */
	public void setURL (String url) {
    	 this.url= url;	
	}
	
	/*
	 * Auslesen der URL
	 */
    public String getURL () {
    	return this.url;	
	}
	
}
