package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class CinemaGroup extends BusinessObject {

	private static final long serialversionUID = 1l;
	
	private int cinemaGroupID;
	private Timestamp creationDate;
	private String name;
	
	private int userID;
	
	
	
	
	public CinemaGroup(int cinemaGroupID, Timestamp creationDate, String name, int userID) {
		this.cinemaGroupID=cinemaGroupID;
		this.creationDate=creationDate;
		this.name=name;
		this.userID=userID;
	}




	public int getCinemaGroupID() {
		return cinemaGroupID;
	}




	public void setCinemaGroupID(int cinemaGroupID) {
		this.cinemaGroupID = cinemaGroupID;
	}




	public Timestamp getCreationDate() {
		return creationDate;
	}




	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public int getUserID() {
		return userID;
	}




	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	
	
}
