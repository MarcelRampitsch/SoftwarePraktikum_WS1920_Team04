package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

/*
 * Realisierung der Timeslot Klasse. Timeslots (Zeitfenster) setzen sich aus den Attributen time und userID und creationDate zusammen.
 *  Sie bieten für die Kinobesitzer die Möglichkeit spezifische Zeiten festzulegen, zu denen Filme ausgestrahlt werden können.
 */
public class Timeslot  extends BusinessObject {
	
	/*
	 * SerialVersionUID zur eindeutigen Identifikation der Version der serialisierbaren Klasse.
	 */
private static final long serialversionUID = 1l;

	private String time;
	private int userID;
		
	/*
	 * Konstruktoren
	 */
	public Timeslot(String time, int userID, int id, Timestamp creationDate) {
		super(id, creationDate);
		this.time = time;
		this.userID = userID;
	}
	
	public Timeslot(String time, int userID) {
		this.time = time;
		this.userID = userID;
	}
	
	/*
	 * Der Default Konstruktor
	 */
	public Timeslot() {
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
	 * Auslesen der Zeit
	 */
	public String getTime() {
		return time;
	}

	/*
	 * Setzer der Zeit
	 */
	public void setTime(String time) {
		this.time = time;
	}

}
