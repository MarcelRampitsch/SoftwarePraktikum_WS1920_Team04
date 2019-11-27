package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class Admin extends User {
	
	private static final long serialversionUID = 1l;

	private int admin;

	public Admin(String nickname, String email, int id, Timestamp creationDate, int admin) {
		super(nickname, email, id, creationDate );
		this.admin = admin;
	}
	
	public int getAdmin() {
		return admin;
	}
	
	public void setAdmin(int admin) {
		this.admin = admin;
	}
}
