package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;


public class Survey extends BusinessObject {

private static final long serialversionUID = 1l;
	
	private String name;
	
	//Fremdschl�sselattribute
	private int userID;
	private int groupID;
	
	//Konstruktor
	public Survey(int id, Timestamp creationDate, String name, int userID, int groupID) {
		super(id, creationDate);
		this.name = name;
		this.userID = userID;
		this.groupID = groupID;
		}
	
	public Survey(String name, int userID, int groupID) {
		this.name = name;
		this.userID = userID;
		this.groupID = groupID;
		}
	
	
	public Survey() {
	}
	
	public Survey (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
}
