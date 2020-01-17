package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class User extends BusinessObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nickname;
	private String email;
	private boolean loggedIn = false;
	private String url;
	

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
	
	public User() {
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIsLoggedIn(boolean b) {
		this.loggedIn=b;
		
	}
	
	public boolean getLoggedIn() {
		return this.loggedIn;
		
	}
	
	public void setURL (String url) {
    	 this.url= url;

		
	}
	
    public String getURL () {
    	return this.url;
		
	}
	

}
