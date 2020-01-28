package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

/*
 * Realisierung der Vote Klasse. Votes setzen sich aus den Attributen ID, Erstellungsdatum,
 * UmfrageeintragID, NutzerID und voteErgebnis zusammen. Sie bilden das Wahlverhalten 
 * der Anwender in Umfragen ab.
 * 
 */
public class Vote extends BusinessObject {

	/*
	 * SerialVersionUID zur eindeutigen Identifikation der Version der serialisierbaren Klasse.
	 */
	private static final long serialversionUID = 1l;
	
	/*
	 * Die UmfrageeintragID zum Vote
	 */
	private int surveyentryID;
	
	/*
	 * Die UserID zum Vote
	 */
	private int userID;
	
	/*
	 * Das Wahlergebnis
	 */
	private int voteResult;
	
	/*
	 * Konstruktoren
	 */
	public Vote(int id, Timestamp creationDate, int surveyentryID, int userID, int voteResult) {
		super(id, creationDate);
		this.surveyentryID = surveyentryID;
		this.userID = userID;
		this.voteResult = voteResult;
	}
	public Vote(int surveyentryID, int userID, int voteResult) {
		this.surveyentryID = surveyentryID;
		this.userID = userID;
		this.voteResult = voteResult;
	}
	
	/*
	 * Default Konstruktor
	 */
	public Vote() {
		
	}
	
	/*
	 * Auslesen des Wahlergebnis.
	 */
	public int getVoteResult() {
		return voteResult;
	}

	/*
	 * Setzen des Wahlergebnis.
	 */
	public void setVoteResult(int voteResult) {
		this.voteResult = voteResult;
	}

	/*
	 * Auslesen der UmfrageeintragID
	 */
	public int getSurveyEntryID() {
		return surveyentryID;
	}
	
	/*
	 * Setzen der UmfrageeintragID
	 */
	public void setSurveyEntryID(int surveyentryID) {
		this.surveyentryID = surveyentryID;
	}
	
	/*
	 * Auslesen der UserID
	 */
	public int getUserID() {
		return userID;
	}
	
	/*
	 * Setzen der UserID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
