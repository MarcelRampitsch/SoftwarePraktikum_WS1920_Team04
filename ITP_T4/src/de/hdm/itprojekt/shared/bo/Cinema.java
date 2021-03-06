package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class Cinema extends BusinessObject{
	
	private static final long serialversionUID = 1L;
	
		//Benötigte Instanzvariable erstellt

		private String location;
		private String name;
		
		//Fremdschlüsselattribute erzeugt
		private int cinemaGroupID;
		private int userID;
	
	
		//Konstruktor, der bei der Erzeugung dieser Klasse zum tragen kommt
		public Cinema(int id, Timestamp creationDate, String location, String name, int cinemaGroupID, int userID) {
			super (id, creationDate);
			
			this.location=location;
			this.name=name;
			this.cinemaGroupID=cinemaGroupID;
			this.userID=userID;
		}
		
		public Cinema(String location, String name, int userID, int cinemaGroupID) {
			
			this.location=location;
			this.name=name;
			this.userID=userID;
			this.cinemaGroupID=cinemaGroupID;
		}
		
		public Cinema() {
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
