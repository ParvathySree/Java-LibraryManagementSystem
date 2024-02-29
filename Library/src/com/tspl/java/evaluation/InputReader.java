package com.tspl.java.evaluation;

import java.util.Scanner;

public class InputReader {

	Scanner input = new Scanner(System.in);
	final String REGEX_FOR_USERNAME = "^[a-zA-Z0-9]*$";
	int USERNAME_MAX_LENGTH = 7;
	int PASSWORD_MAX_LENGTH = 10;
	final String REGEX_FOR_NAME = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}";
	int NAME_MAX_LENGTH = 20;
	final String REGEX_FOR_ISBN = "^[0-9] + (-[0-9]+)+$";

	/**
	 * Reads and validate username
	 * @param parameterName , Name of parameter to be validated
	 * @return string , Validated string
	 */
	public String readAndValidateUsername(String parameterName) {
		String message = null;
		boolean isValid = true;
		String string;
		do {
			isValid = true;
			string = input.nextLine();
			if (!string.matches(REGEX_FOR_USERNAME)) {
				isValid = false;
				message = parameterName + " must contain only alphabets and numbers, Please re-enter :";
			}
			if (string.length() > USERNAME_MAX_LENGTH && isValid == true) {
				isValid = false;
				message = parameterName + " cannot exceed " + USERNAME_MAX_LENGTH + " characters, Please re-enter :";
			}
			if (!isValid) {
				System.out.println(message);
			}
		} while (!isValid);
		return string;
	}

	/**
	 * Reads and validate password
	 * @param parameterName
	 * @return string , validated password
	 */
	public String readAndValidatePassword(String parameterName) {

		String message = null;
		boolean isValid = true;
		String string;
		do {
			isValid = true;
			string = input.nextLine();
			if (string.length() > PASSWORD_MAX_LENGTH) {
				isValid = false;
				message = parameterName + " cannot exceed " + PASSWORD_MAX_LENGTH + " characters, Please re-enter :";
			}
			if (!isValid) {
				System.out.println(message);
			}
		} while (!isValid);

		return string;

	}

	/**
	 * Reads and validates name
	 * @param parameterName
	 * @return string , validated name
	 */
	public String readAndValidateName(String parameterName) {

		String message = null;
		boolean isValid = true;
		String string;
		do {
			isValid = true;
			string = input.nextLine();
			if (!string.matches(REGEX_FOR_NAME)) {
				isValid = false;
				message = parameterName + " must contain only alphabets , Please re-enter :";
			}
			if (string.length() > NAME_MAX_LENGTH && isValid == true) {
				isValid = false;
				message = parameterName + " cannot exceed " + NAME_MAX_LENGTH + " characters, Please re-enter :";
			}
			if (!isValid) {
				System.out.println(message);
			}
		} while (!isValid);

		return string;

	}

	/**
	 * Reads and validates integer
	 * @param parameterName
	 * @return number , validated integer
	 */
	public int readAndValidateInteger(String parameterName) {
		int number;
		boolean isValid = true;
		String message = null;
		do {
			isValid = true;
			while (!input.hasNextInt()) {
				System.out.println(parameterName + " must be an integer, Please re-enter :");
				input.next();
			}
			number = input.nextInt();
			input.nextLine();
			if (number < 0) {
				isValid = false;
				message = parameterName + " should be between greater than 0";

			}
			if (!isValid) {
				System.out.println(message);
			}

		} while (!isValid);

		return number;

	}

	public String readAndValidateIsbn(String parameterName) {
		String message = null;
		boolean isValid = true;
		String string;
		do {
			isValid = true;
			string = input.nextLine();
			if (!string.matches(REGEX_FOR_ISBN)) {
				isValid = false;
				message = parameterName + " must contain only numbers and hyphen , Please re-enter :";
			}
			if (string.length() > 17 && isValid == true) {
				isValid = false;
				message = parameterName + " cannot exceed  17 characters, Please re-enter :";
			}
			if (!isValid) {
				System.out.println(message);
			}
		} while (!isValid);

		return string;

	}

	/**
	 * Reads and validates double numbers
	 * @param parameterName
	 * @return number, validated double type number
	 */
	public double readAndValidateDouble(String parameterName) {
		double number;
		boolean isValid = true;
		String message = null;
		do {
			isValid = true;
			while (!input.hasNextDouble()) {
				System.out.println(parameterName + " must be an integer, Please re-enter :");
				input.next();
			}
			number = input.nextDouble();
			input.nextLine();
			if (number < 0) {
				isValid = false;
				message = parameterName + " should be between greater than 0";

			}
			if (!isValid) {
				System.out.println(message);
			}

		} while (!isValid);

		return number;

	}

}
