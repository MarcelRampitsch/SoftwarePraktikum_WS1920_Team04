package de.hdm.itprojekt.shared.bo;

public class Vote extends BusinessObject {

	private static final long serialversionUID = 1l;
	
	private int surveyentryID;
	private int userID;
	
	public Vote(int surveyentryID, int userID) {
		
		this.surveyentryID = surveyentryID;
		this.userID = userID;
		
	}
	
	
	
	
}
