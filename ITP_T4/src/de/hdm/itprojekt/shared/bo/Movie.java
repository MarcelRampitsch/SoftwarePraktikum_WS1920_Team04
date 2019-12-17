package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

//Movie erbt id und creationDate von BusinessObject
public class Movie extends BusinessObject{
	
private static final long serialversionUID = 1l;

	private String name;
	
	//Fremdschl�sselattribute
	private int userID;
	
	//Konstruktor
	public Movie(int id, Timestamp creationDate, String name, int userID) {
		super(id, creationDate);
		this.name = name;
		this.userID = userID;
	}
	
	public Movie(String name, int userID) {
	this.name = name;
	this.userID = userID;
	}
	
	public Movie() {
	}
	
	// Getter und Setter
	
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