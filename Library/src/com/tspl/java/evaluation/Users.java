package com.tspl.java.evaluation;

import java.io.Serializable;

public class Users implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	protected String username;
	protected String password;
	protected String name;
	protected String userType;
	
	
	/**
	 * Getters and setters for variables of class Users
	 *
	 */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

}
