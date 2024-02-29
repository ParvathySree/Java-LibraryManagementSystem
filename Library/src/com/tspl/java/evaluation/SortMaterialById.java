package com.tspl.java.evaluation;

import java.util.Comparator;

public class SortMaterialById implements Comparator<Materials>{

	/**
	 *compares id of 2 materials
	 *
	 */
	@Override
	public int compare(Materials material1, Materials material2) {
		return Integer.parseInt((material1.getId().substring(1, 4))) - Integer.parseInt( (material2.getId().substring(1, 4)));
	}

	

}
