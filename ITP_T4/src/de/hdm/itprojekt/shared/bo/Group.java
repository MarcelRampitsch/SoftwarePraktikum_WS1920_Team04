package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class Group extends BusinessObject{

		private static final long serialversionUID = 1l;
		
		//Klassenvariablen 
		private String name;
		
		//Fremdschlüsselattribute
		private int userID;
		
		
		
		//Konstruktor, der zum tragen kommt, falls eine Instanz dieser Klasse erstellt wird
		public Group(int id, Timestamp creationDate, String name, int userID) {
			super(id, creationDate);
			this.name=name;
			this.userID=userID;
		}
		
		public Group() {
			
		}
		
		public String getName(){
			return name;
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public int getUserID(){
			return userID;
		}
		
		public void setUserID(int userID){
			this.userID = userID;
		}
}
