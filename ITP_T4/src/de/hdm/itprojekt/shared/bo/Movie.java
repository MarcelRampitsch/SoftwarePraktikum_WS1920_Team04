package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

//Movie erbt id und creationDate von BusinessObject
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
	
	public Movie() {
<<<<<<< HEAD
		
=======
>>>>>>> branch 'master' of https://github.com/MarcelRampitsch/SoftwarePraktikum_WS1920_Team04.git
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
	public int getCinemaID() {
		return cinemaID;
	}
	public void setCinemaID(int cinemaID){
		this.cinemaID = cinemaID;
	}
}