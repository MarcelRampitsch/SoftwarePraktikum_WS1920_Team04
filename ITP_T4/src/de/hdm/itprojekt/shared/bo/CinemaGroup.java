package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class CinemaGroup extends BusinessObject {

	private static final long serialversionUID = 1l;
	
	//Klassenvariablen 
	private String name;
	
	//Fremdschlüsselattribute
	private int userID;
	
	
	
	//Konstruktor, der zum tragen kommt, falls eine Instanz dieser Klasse erstellt wird
	public CinemaGroup(String name, int userID) {
		this.name=name;
		this.userID=userID;
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
