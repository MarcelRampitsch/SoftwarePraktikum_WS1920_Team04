package de.hdm.itprojekt.shared.bo;

public class SurveyEntry extends BusinessObject {

	private static final long serialversionUID = 1l;
	
	private int surveyID;
	private int presentationID;

	public SurveyEntry(int surveyID, int presentationID) {
		
		this.surveyID = surveyID;
		this.presentationID = presentationID;
		
	}
	
	
	
}
