package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;


public class Movie extends BusinessObject{
	
private static final long serialversionUID = 1l;

	private String name;
	
	//Fremdschl�sselattribute
	private int userID;
	private int cinemaID;
	
	//Konstruktor
	public Movie(int id, Timestamp creationDate, String name, int userID, int cinemaID) {
		super(id, creationDate);
		this.name = name;
		this.userID = userID;
		this.cinemaID = cinemaID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserID(int userID) {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
}