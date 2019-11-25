package de.hdm.itprojekt.shared.bo;
	

	import java.io.Serializable;
	import java.sql.Timestamp;

	public abstract class BusinessObject implements Serializable {
		
		private static final long serialversionUID = 1l;
		
		private int id;
		private Timestamp creationDate;

		public BusinessObject() {
		}

		public BusinessObject(int id, Timestamp creationDate) {
			this.id =id;
			this.creationDate = creationDate;
		}
		
		public BusinessObject(int id) {
			this.id =id;
		}
		
		public void setId(int id) {
			this.id = id;
		}

		public void setCreationDate(Timestamp creationDate) {
			this.creationDate = creationDate;
		}

		public int getId() {
			return id;
		}

		public Timestamp getCreationDate() {
			return creationDate;
		}
	}

