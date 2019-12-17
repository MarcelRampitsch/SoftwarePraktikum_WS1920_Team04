package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class Timeslot  extends BusinessObject {
	
private static final long serialversionUID = 1l;
	
	private String time;
	private int userID;
		
	
	public Timeslot(String time, int userID, int id, Timestamp creationDate) {
		super(id, creationDate);

		this.time = time;
		this.userID = userID;
	}
	

	

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
