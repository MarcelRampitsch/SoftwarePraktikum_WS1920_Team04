package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class User extends BusinessObject {
	
	private static final long serialversionUID = 1l;

	private String nickname;

	public User(String nickname, int id, Timestamp creationDate) {
		super(id, creationDate);
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}

