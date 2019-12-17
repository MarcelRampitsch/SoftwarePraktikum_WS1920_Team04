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
		
		// Konstruktur, der zum tragen kommt, wenn eine neue Group erstellt wird
//		public Group(String name, int userID) {
//			this.name=name;
//			this.userID=userID;
//		}
		
		// Konstruktur, der zum tragen kommt, wenn eine neue Group erstellt wird
		public Group(int userID, String name ) {
			this.userID=userID;
			this.name=name;
				}
		
		public Group(String name, int ID  ) {
			super(ID);
			this.name=name;
		}
		
		public Group(String name) {
			this.name = name;
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
