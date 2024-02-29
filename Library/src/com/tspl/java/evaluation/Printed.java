package com.tspl.java.evaluation;

import java.io.Serializable;
import java.util.Random;

public class Printed extends Materials implements Serializable{

	private static final long serialVersionUID = 1L;
	private int noOfPages;
	private String isbn;

	protected Printed() {
		super();
		noOfPages = 200;
		isbn = "123-345-567-123-1";

	}

	public int getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public void generateAndSetId() {
		Random random = new Random();
		int number = random.nextInt(99999);
		String numericPart = String.format("%05d", number);
		id = "P" + numericPart;

	}

	@Override
	public String toString() {
		return "Id : " + id + " , Name : " + name + " , Author : " + author + " , Creator : " + createdBy + " , Created at : " + createdAt    + " , ISBN : " + isbn + " ,No. of pages : " + noOfPages + " , Total stock : " + totalStock;
	}
	

}
