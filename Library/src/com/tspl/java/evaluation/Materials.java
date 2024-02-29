package com.tspl.java.evaluation;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Materials implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String id;
	protected String name;
	protected String author;
	protected String createdBy;
	protected String createdAt;
	protected int totalStock;

	/**
	 * 
	 * Getters and setters for the varibales of class Materials
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public void generateAndSetId() {
		System.out.println("Generate and set Id");
	}

	public void addStock() {
		totalStock += 1;
	}

	public void reduceStock() {
		if (totalStock > 0) {
			totalStock -= 1;
		} else {
			System.out.println("No stock available");
		}
	}

	public void generateCreatedAt() {
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		createdAt = dateTimeFormat.format(LocalDateTime.now());
	}

}
