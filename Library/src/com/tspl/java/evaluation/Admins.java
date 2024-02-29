package com.tspl.java.evaluation;

import java.io.Serializable;

public class Admins extends Users implements Serializable{

	private static final long serialVersionUID = 1L;

	protected Admins() {
		super();
		this.userType = "Admins";
		
	}
	
	public boolean equals(Object object) {
		if(object == this) {
			return true;
		}
		if(!(object instanceof Users)) {
			return false;
		}
		Admins adminObject =(Admins) object;
		return this.name.equals(adminObject.name)&& this.username.equals(adminObject.username);
	}
	
	@Override
	public String toString() {
		return "Admins [username=" + username + ", password=" + password + ", name=" + name + ", userType=" + userType
				+ "]";
	}

	
	
	

}
