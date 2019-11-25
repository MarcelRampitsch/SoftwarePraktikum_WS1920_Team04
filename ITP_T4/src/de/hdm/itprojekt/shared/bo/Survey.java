package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;


public class Survey extends BusinessObject {

private static final long serialversionUID = 1l;
	
	private int surveyID;
	private String name;
	private Timestamp creationDate;
	
	//Fremdschlüsselattribute
	private int surveyEntryID;
	private int userID;
	private int groupID;
	
	//Konstruktor
	public Survey(int surveyID, String name, Timestamp creationDate, int surveyEntryID, int userID, int groupID) {
		
		this.surveyID= surveyID;
		this.name = name;
		this.creationDate = creationDate;
		this.surveyEntryID = surveyEntryID;
		this.userID = userID;
		this.groupID = groupID;
		}

	
	public int getSurveyID() {
		return surveyID;
	}
	public void setSurveyID() {
		this.surveyID = surveyID;
	}
	public String getName() {
		return name;
	}
	public void setName() {
		this.name = name;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate() {
		this.creationDate = creationDate;
	}
	
	public int getSurveyEntryID() {
		return surveyEntryID;
	}
	public void setSurveyEntryID() {
		this.surveyEntryID = surveyEntryID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID() {
		this.userID = userID;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID() {
		this.groupID = groupID;
	}
}
