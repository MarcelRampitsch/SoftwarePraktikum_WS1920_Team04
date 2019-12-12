package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;


public class Presentation extends BusinessObject {
	
private static final long serialversionUID = 1l;

	
	private int cinemaID;
	private int movieID;
	private int userID;
	private int timeslotID;
	private int date;
	
	// Konstruktor für die find methoden der Mapper
	public Presentation(int cinemaID, int movieID, int userID, int timeslotID, int date, int id, Timestamp creationDate) {
		super(id, creationDate);

		this.cinemaID = cinemaID;
		this.movieID = movieID;
		this.userID = userID;
		this.timeslotID = timeslotID;
		this.date= date;
	}
<<<<<<< HEAD
	
	public Presentation() {
		
=======
	//Konstrucktor für die insert methoden der Mapper
	public Presentation(int cinemaID, int movieID, int userID, int timeslotID, int date) {
		
		this.cinemaID = cinemaID;
		this.movieID = movieID;
		this.userID = userID;
		this.timeslotID = timeslotID;
		this.date= date;
	}
	//Default Konstruktor für ?????????????????????????????????????????????????????????????
	public Presentation() {
>>>>>>> branch 'master' of https://github.com/MarcelRampitsch/SoftwarePraktikum_WS1920_Team04.git
	}
	
	public int getCinemaID() {
		return cinemaID;
	}
	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getTimeslotID() {
		return timeslotID;
	}
	public void setTimeslotID(int timeslotID) {
		this.timeslotID = timeslotID;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	
}


