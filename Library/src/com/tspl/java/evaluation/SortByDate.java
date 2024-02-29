package com.tspl.java.evaluation;

import java.util.Comparator;

public class SortByDate implements Comparator<Materials> {

	/**
	 *compares created date of 2 materials
	 *
	 */
	@Override
	public int compare(Materials material1, Materials material2) {

		return material1.getCreatedAt().compareTo(material2.getCreatedAt());
	}

}
