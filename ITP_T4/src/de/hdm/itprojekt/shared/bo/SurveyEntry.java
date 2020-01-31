package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class SurveyEntry extends BusinessObject {

	private static final long serialversionUID = 1l;
	
	private int surveyID;
	private int presentationID;
	private int result;
	
	// TEST !!!!
			private String name;
			
			private int userID;
	//TEST !!!

	public SurveyEntry(int id, int surveyID, int presentationID, int result) {
		super(id);
		this.surveyID = surveyID;
		this.result = result;
		this.presentationID = presentationID;
				
	}		
			
	public SurveyEntry(int id, Timestamp creationDate, int surveyID, int presentationID) {
		super(id, creationDate);
		this.surveyID = surveyID;
		this.presentationID = presentationID;
		
	}
	
	// Konstruktur, der zum tragen kommt, wenn eine neue Group erstellt wird
	public SurveyEntry(int userID, String name ) {
		this.userID=userID;
		this.name=name;
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
	
	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	
}
