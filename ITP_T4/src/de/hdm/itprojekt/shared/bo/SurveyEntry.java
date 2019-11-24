package de.hdm.itprojekt.shared.bo;

public class SurveyEntry extends BusinessObject {

	private static final long serialversionUID = 1l;
	
	private int surveyID;
	private int presentationID;

	public SurveyEntry(int surveyID, int presentationID) {
		
		this.surveyID = surveyID;
		this.presentationID = presentationID;
		
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
