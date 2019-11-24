package de.hdm.itprojekt.shared.bo;

public class Vote extends BusinessObject {

	private static final long serialversionUID = 1l;
	
	private int surveyentryID;
	private int userID;
	
	public Vote(int surveyentryID, int userID) {
		
		this.surveyentryID = surveyentryID;
		this.userID = userID;
		
	}
	
	public int getSurveyEntryID() {
		return surveyentryID;
	}
	
	public void setSurveyEntryID(int surveyentryID) {
		this.surveyentryID = surveyentryID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
