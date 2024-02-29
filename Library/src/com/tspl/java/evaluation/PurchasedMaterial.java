package com.tspl.java.evaluation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PurchasedMaterial extends Materials{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String readerName;
	String dueDate;
	double rating;

	public PurchasedMaterial() {
		super();
		readerName = "Raju";
		dueDate = "2023/01/19";
		rating = 3; 
	}
	@Override
	public String toString() {
		return "Book name : " + name  + " , Reader's Name  : " + readerName + " , Due date" + dueDate + ", Rating : " + rating ;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public void generateDueDate() {
		DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dueDate = dateformat.format(LocalDate.now().plusDays(30));
	}
	

}
