package com.tspl.java.evaluation;

public class Main {

	public static void main(String[] args) {

		final String LIBRARY_NAME = "Chalakudy Municipal Library";
		final String MAIN_MENU_OPTIONS = "\n*************** Welcome to " + LIBRARY_NAME + " ***************\n"
				+ "\nChoose options from below :\n" + "\n1 - Login" + "\n2 - User Sign Up" + "\n3 - Exit";
		final String DEFAULT = " Invalid input, Please choose again";
		final String EXIT = "Bye , Happy Reading - വായിച്ചു വളരു !";
		int optionMainMenu;
		Services service = new Services();
		InputReader inputReader = new InputReader();

		service.readMaterialsListFromFile(); // reading materialList from file
		service.readReaderDetailsFromFile(); // reading readerList from file
		service.readAdminsDetailsFromFile(); // reading adminList from file

		Admins defaultAdmin = new Admins();
		defaultAdmin.setUsername("admin");
		defaultAdmin.setPassword("admin");
		defaultAdmin.setName("admin");

		if (!service.adminList.contains(defaultAdmin)) {
			service.adminList.add(defaultAdmin);
		}

		//service.adminList.stream().forEach(x -> System.out.println(x));
		//service.readerList.stream().forEach(x -> System.out.println(x));      //testing for read
		//service.materialList.stream().forEach(x -> System.out.println(x));

		do {
			System.out.println(MAIN_MENU_OPTIONS); // print main menu options
			optionMainMenu = inputReader.readAndValidateInteger("Options");// Integer.parseInt(input.nextLine()); //
																			// option selected from main menu
			switch (optionMainMenu) {
			case 1:
				service.getLoginOptions(); // for login
				break;

			case 2:
				service.readerSignUp(); // for sign up
				break;

			case 3:
				service.writeMaterialsListToFile();
				service.writeReaderDetailsToFile();
				service.writeAdminDetailsToFile();
				System.out.println(EXIT);
				break;

			default:
				System.out.println(DEFAULT);
				break;

			}
		} while (optionMainMenu != 3);

	}

}
