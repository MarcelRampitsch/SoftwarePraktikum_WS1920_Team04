package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;


public class Movie{
	
private static final long serialversionUID = 1l;

	private int movieID;
	private String name;
	private Timestamp creationDate;
	
	
	public Movie(int movieID, String name, Timestamp creationDate) {

		this.movieID = movieID;
		this.name = name;
		this.creationDate = creationDate;
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
}