package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class Admin extends User {
	
	private static final long serialversionUID = 1l;

	private boolean admin;

	public Admin(String nickname, int id, Timestamp creationDate, boolean admin) {
		super(nickname, id, creationDate);
		this.admin = admin;
	}
	
	public boolean getAdmin() {
		return admin;
	}
	
	public boolean setAdmin(boolean admin) {
		return this.admin = admin;
	}
}
