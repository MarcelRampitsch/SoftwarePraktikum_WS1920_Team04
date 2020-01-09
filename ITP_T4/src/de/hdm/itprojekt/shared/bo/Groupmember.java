package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class Groupmember extends BusinessObject{

		private static final long serialversionUID = 1l;
			
		//Fremdschlüsselattribute
		private int userID;
		private int groupID;
		
		//Konstruktor, der zum tragen kommt, falls eine Instanz dieser Klasse erstellt wird
		public Groupmember(int id, Timestamp creationDate, int groupID, int userID) {
			super(id, creationDate);
			this.groupID=groupID;
			this.userID=userID;
		}
		
		public Groupmember(int groupID, int userID) {
			this.groupID=groupID;
			this.userID=userID;
		}
		
		public Groupmember() {
			
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