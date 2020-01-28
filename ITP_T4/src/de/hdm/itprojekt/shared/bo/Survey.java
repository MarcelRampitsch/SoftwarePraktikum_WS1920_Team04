package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

/*
 * Realisierung der Umfragen Klasse. Umfragen besitzen einen Namen, zudem 
 * besitzen sie die Fremdschlüsselattribute userID, groupID und round.
 * 
 */
public class Survey extends BusinessObject {

	/*
	 * SerialVersionUID zur eindeutigen Identifikation der Version der serialisierbaren Klasse.
	 */
	private static final long serialversionUID = 1l;
	
	private String name;
	
	//Fremdschlï¿½sselattribute
	private int userID;
	private int groupID;
	private int round;
	
	/*
	 * 	Konstruktoren
	 */
	public Survey(int id, Timestamp creationDate, String name, int userID, int groupID, int round) {
		super(id, creationDate);
		this.name = name;
		this.userID = userID;
		this.groupID = groupID;
		this.round = round;
		}
	
	public Survey(int id, Timestamp creationDate, String name, int userID, int groupID) {
		super(id, creationDate);
		this.name = name;
		this.userID = userID;
		this.groupID = groupID;
		}
	
	public Survey(String name, int userID, int groupID) {
		this.name = name;
		this.userID = userID;
		this.groupID = groupID;
		}
	
	public Survey (String name) {
		this.name = name;
	}
	
	/*
	 * Default Konstruktor
	 */
	public Survey() {
	}

	/*
	 * Auslesen des Namen
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Setzen des Namen
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Auslesen der AnwenderID
	 */
	public int getUserID() {
		return userID;
	}
	
	/*
	 * Setzen der AnwenderID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	/*
	 * Auslesen der GruppenID
	 */
	public int getGroupID() {
		return groupID;
	}
	
	/*
	 * Setzen der GruppenID
	 */
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	
	/*
	 * Auslesen der Wahlrunde
	 */
	public int getRound() {
		return round;
	}
	
	/*
	 * Setzen der Wahlrunde
	 */
	public void setRound(int round) {
		this.round = round;
	}
}