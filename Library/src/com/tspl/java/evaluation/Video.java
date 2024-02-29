package com.tspl.java.evaluation;

import java.io.Serializable;
import java.util.Random;

public class Video extends Materials implements Serializable{

	private static final long serialVersionUID = 1L;
	private double duration;

	protected Video() {
		super();
		duration = 2;
	}
	
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public void generateAndSetId() {
		Random random = new Random();
		int number = random.nextInt(99999);
		String numericPart = String.format("%05d",number);
		id = "V" + numericPart;
	}
	
	@Override
	public String toString() {
		return "Id : " + id + " , Name : " + name + " , Author : " + author + " , Creator : " + createdBy + ", Created at : " + createdAt  + " , Duration : " + duration + " , Total stock : " + totalStock;
	}
	
}
