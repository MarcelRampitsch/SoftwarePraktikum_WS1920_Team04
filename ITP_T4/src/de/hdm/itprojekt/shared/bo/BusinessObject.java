package de.hdm.itprojekt.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class BusinessObject implements IsSerializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Timestamp creationDate;

	public BusinessObject() {
	}

	public BusinessObject(int id, Timestamp creationDate) {
		this.id = id;
		this.creationDate = creationDate;
	}

	public BusinessObject(int id) {
		this.id = id;
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

	public boolean equals(Object o) {
		boolean result = false;

		if (o!= null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			if (bo.getId() == this.id) {
				result = true;
			}
		}
		return result;
	}
}