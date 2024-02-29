package com.tspl.java.evaluation;

import java.io.Serializable;

public class Readers extends Users implements Serializable{

	

	private static final long serialVersionUID = 1L;

	protected Readers() {
		super();
		this.userType = "Readers";
	}
	
	public boolean equals(Object object) {
		if(object == this) {
			return true;
		}
		if(!(object instanceof Users)) {
			return false;
		}
		Readers readerObject =(Readers) object;
		return this.name.equals(readerObject.name)&& this.username.equals(readerObject.username);
	}
	
	@Override
	public String toString() {
		return "Readers [username=" + username + ", password=" + password + ", name=" + name + ", userType=" + userType
				+ "]";
	}
	
}
