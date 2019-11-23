package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;


public class Movie extends BusinessObject{
	
private static final long serialversionUID = 1l;

	private int movieID;
	private String name;
	private Timestamp creationDate;
	
	//Fremdschlüsselattribute
	private int presentationID;
	private int userID;
	
	//Konstruktor
	public Movie(int movieID, String name, Timestamp creationDate, int presentationID, int userID) {

		this.movieID = movieID;
		this.name = name;
		this.creationDate = creationDate;
		this.presentationID= presentationID;
		this.userID= userID;
	}
	
	
	
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate() {
		this.creationDate = creationDate;
	}
	public int getPresentationID(int presentationID) {
		return presentationID;
	}
	public void setPresentationID(int presentationID) {
		this.presentationID = presentationID;
	}
	public int getUserID(int userID) {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
}