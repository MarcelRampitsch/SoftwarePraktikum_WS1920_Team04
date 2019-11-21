package de.hdm.itprojekt.shared;

import java.sql.Timestamp;

import de.hdm.itprojekt.shared.bo.BusinessObject;

public class Cinema extends BusinessObject{
	
	private static final long serialversionUID = 1L;
	
		//Benötigte Klassenvariable erstellt
		private int cinemaID;
		private Timestamp creationDate;
		private String location;
		private String name;
		
		//Fremdschlüsselattribute erzeugt
		private int cinemaGroupID;
		private int userID;
	
	
		//Konstruktor, der bei der Erzeugung dieser Klasse zum tragen kommt
		public Cinema(int cinemaID, Timestamp creationDate, String location, String name, int cinemaGroupID, int userID) {
			
			this.cinemaGroupID=cinemaID;
			this.creationDate=creationDate;
			this.location=location;
			this.name=name;
			this.cinemaGroupID=cinemaGroupID;
			this.userID=userID;
		}


		public int getCinemaID() {
			return cinemaID;
		}


		public void setCinemaID(int cinemaID) {
			this.cinemaID = cinemaID;
		}


		public Timestamp getCreationDate() {
			return creationDate;
		}


		public void setCreationDate(Timestamp creationDate) {
			this.creationDate = creationDate;
		}


		public String getLocation() {
			return location;
		}


		public void setLocation(String location) {
			this.location = location;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public int getCinemaGroupID() {
			return cinemaGroupID;
		}


		public void setCinemaGroupID(int cinemaGroupID) {
			this.cinemaGroupID = cinemaGroupID;
		}


		public int getUserID() {
			return userID;
		}


		public void setUserID(int userID) {
			this.userID = userID;
		}
		
		
		

}
