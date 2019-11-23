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
		
		public String getName(){
			return name;
		}
		
		public String setName(String name){
			return this.name = name;
		}
		
		public int UserID(){
			return userID;
		}
		
		public int setUserID(int userID){
			return this.userID = userID;
		}
}
