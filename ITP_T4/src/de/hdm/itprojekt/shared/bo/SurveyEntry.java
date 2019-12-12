package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class SurveyEntry extends BusinessObject {

	private static final long serialversionUID = 1l;
	
	private int surveyID;
	private int presentationID;

	public SurveyEntry(int id, Timestamp creationDate, int surveyID, int presentationID) {
		super(id, creationDate);
		this.surveyID = surveyID;
		this.presentationID = presentationID;
		
	}
	
	public SurveyEntry() {
		
	}
	public int getSurveyID() {
		return surveyID;
	}
	
	public void setSurveyID(int surveyID) {
		this.surveyID = surveyID;
	}
	
	public int getPresentationID() {
		return presentationID;
	}
	
	public void setPresentationID(int presentationID) {
		this.presentationID = presentationID;
	}
	
}
