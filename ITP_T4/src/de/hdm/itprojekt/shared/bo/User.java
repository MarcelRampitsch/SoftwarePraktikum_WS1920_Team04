package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class User extends BusinessObject {
	
	private static final long serialversionUID = 1l;

	private String nickname;
	private String email;

	public User(String nickname, String email, int id, Timestamp creationDate) {
		super(id, creationDate);
		this.nickname = nickname;
		this.email = email;
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

}

