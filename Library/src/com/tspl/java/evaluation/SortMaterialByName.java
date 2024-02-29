package com.tspl.java.evaluation;

import java.util.Comparator;

public class SortMaterialByName implements Comparator<Materials> {
	
	/**
	 *compares name of 2 materials
	 *
	 */
	@Override
	public int compare(Materials material1, Materials material2) {
		return material1.getName().compareTo(material2.getName());
	}

}
