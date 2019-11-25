package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class Vote extends BusinessObject {

	private static final long serialversionUID = 1l;
	
	private int surveyentryID;
	private int userID;
	private int voteResult;
	
	public Vote(int id, Timestamp creationDate, int surveyentryID, int userID, int voteResult) {
		super(id, creationDate);
		this.surveyentryID = surveyentryID;
		this.userID = userID;
		this.voteResult = voteResult;
	}
	
	public int getVoteResult() {
		return voteResult;
	}

	public void setVoteResult(int voteResult) {
		this.voteResult = voteResult;
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
