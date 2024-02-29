package com.tspl.java.evaluation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Services {

	Scanner input = new Scanner(System.in); // object of scanner class
	InputReader inputReader = new InputReader(); // object of class inputReader

	ArrayList<Users> userList = new ArrayList<>(); // arraylist of type Users
	ArrayList<Readers> readerList = new ArrayList<>(); // arraylist of type Readers
	ArrayList<Admins> adminList = new ArrayList<>(); // arraylist of type Admiins
	ArrayList<Printed> printedList = new ArrayList<>(); // arraylist of type Printed
	ArrayList<Audio> audioList = new ArrayList<>(); // arrayList of type Audio
	ArrayList<Video> videoList = new ArrayList<>(); // arrayList of type Video
	ArrayList<Materials> materialList = new ArrayList<>(); // arrayList of type Materials
	ArrayList<PurchasedMaterial> purchaseMaterialList = new ArrayList<>(); // arraylist of type PurchasedMaterials
	ArrayList<String> purchaseIdList = new ArrayList<>(); // arrayList for Ids of purchased Materials

	final String USERNAME_STRING = "Enter username :  "; // text to display while asking for username
	final String PASSWORD_STRING = "Enter password : "; // text to display while asking for password
	final String NAME_STRING = "Enter name : "; // text to display while asking for pname
	final String ADMIN_OPTIONMENU_TEXT = "\n********** Admin Options **********\n" + "\n"
			+ "Choose from options below: " + "\n" + "1 - Add material" + "\n" + "2 - Delete material" + "\n"
			+ "3 - Add new admin user" + "\n" + "4 - View Materials " + "\n" + "5 - Log Out"; // text for admin option
																								// menu
	final String READER_OPTION_MENU = "\n********** Reader Options **********\n \nChoose from below options: \n1 - View \n2 - Purchase"; // text
	// for
	// reader
	// option
	// menu
	final String DEFAULT_INVALID_TEXT = "Invalid input, Try again! \n"; // text displayed for default in switch
	final String MATERIAL_NAME_TEXT = "Enter material name :"; // text asking for material name
	final String MATERIAL_AUTHOR_TEXT = "Enter author name :"; // text asking for author name
	final String MATERIAL_STOCK_TEXT = "Enter total stock :"; // text asking for total stock
	final String TYPE_OF_MATERIAL_TEXT = "Select which type of material :" + "\n" + "1 - Printed" + "\n" + "2 - Audio"
			+ "\n" + "3 - Video"; // text for types of materials options

	String inputUserName; // username accepted from user
	String inputPassword; // password accepted from user
	int accIndex; // index of account logged into
	DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); // format for date & time
	DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // format for date
	int purchaseIndex; // index of purchased materials
	String inputId; // input Id given by user
	int materialIndex; // index of materials
	Boolean isPresent = false;
	int option; // option selected from menus
	String pathLibraryDetails = "materialsData.ser"; // file where material details are stored
	String pathAdminDetails = "adminData.ser";
	String pathReaderDetails = "readerData.ser";

	/**
	 * To sign up Readers account
	 */
	public void readerSignUp() {
		System.out.println("\n***** Reader Sign Up *****");
		readerList.add(new Readers()); // new object of Readers added to readerList
		int currentReaderIndex = readerList.size() - 1; // index of newly created object
		System.out.println(USERNAME_STRING);
		readerList.get(currentReaderIndex).setUsername(inputReader.readAndValidateUsername("Username")); // sets
																											// username
		System.out.println(PASSWORD_STRING);
		readerList.get(currentReaderIndex).setPassword(inputReader.readAndValidatePassword("Password")); // sets
																											// password
		System.out.println(NAME_STRING);
		readerList.get(currentReaderIndex).setName(inputReader.readAndValidateName("Name")); // sets name
		checkUniqueReader();
	}

	/**
	 * Check if existing user tries to sign up again
	 */
	public void checkUniqueReader() {
		boolean doExist = false;
		String message = "Successfull reader sign up";
		for (int readerIndex = 0; readerIndex < readerList.size() - 1; readerIndex++) {

			if (readerList.get(readerList.size() - 1).equals((readerList.get(readerIndex)))) {
				message = "User already exist. Try logging in ! ";
				doExist=true;
				break;
			}
		}
		if(doExist == true) {
			readerList.remove(readerList.size() - 1);
		}
		System.out.println(message);

	}

	/**
	 * To display the login options and do login functionalities
	 */
	public void getLoginOptions() {
		System.out.println("********** Login **********\n" + "\n1 - Login as admin \n2 - Login as reader ");
		option = inputReader.readAndValidateInteger("Option");
		switch (option) {
		case 1:
			doLogin(); // Asks password and username
			adminLoginAuthentication(); // Authenticate admin
			break;
		case 2:
			doLogin(); // Asks password and username
			readerLoginAuthentication(); // Authenticates readers
			break;
		default:
			System.out.println(DEFAULT_INVALID_TEXT);
			break;
		}
	}

	/**
	 * Accepts password and username from user
	 */
	public void doLogin() {
		System.out.println(USERNAME_STRING);
		inputUserName = input.nextLine();
		System.out.println(PASSWORD_STRING);
		inputPassword = input.nextLine();
	}

	/**
	 * Authenticates the reader
	 */
	public void readerLoginAuthentication() {
		String message = "Invalid username or password, Please try again";
		Boolean isReader = false;
		for (Readers reader : readerList) {
			if (reader.getUsername().equals(inputUserName) && reader.getPassword().equals(inputPassword)) { // checking
																											// if input
																											// username
																											// and
																											// password
																											// exists in
				isReader = true;
				accIndex = readerList.indexOf(reader); // index of reader currently logged in
				message = "Successfull reader login ! \n";
				break;
			}
		}
		if (isReader) {
			System.out.println(message);
			displayReaderMenu();

		} else {
			System.out.println(message);
		}

	}

	/**
	 * Adds new Admins object to adminList
	 */
	public void adminSignUp() {
		System.out.println("********** Admin Sign Up **********\n");
		adminList.add(new Admins());
		int currentReaderIndex = adminList.size() - 1;
		System.out.println(USERNAME_STRING);
		adminList.get(currentReaderIndex).setUsername(inputReader.readAndValidateUsername("Username"));
		System.out.println(PASSWORD_STRING);
		adminList.get(currentReaderIndex).setPassword(inputReader.readAndValidatePassword("Password"));
		System.out.println(NAME_STRING);
		adminList.get(currentReaderIndex).setName(inputReader.readAndValidateName("Name"));
		checkUniqueAdmin();
	}

	/**
	 * Check if existing user tries to sign up again
	 */
	public void checkUniqueAdmin() {
		Boolean doExist = false;
		String message = "Admin added successfully";
		for (int adminIndex = 0; adminIndex < adminList.size() - 1; adminIndex++) {

			if (adminList.get(adminList.size() - 1).equals((adminList.get(adminIndex)))) {
				doExist= true;
				message = "User already exist. Try logging in ! ";
				break;
			}
		}
		if(doExist == true) {
			adminList.remove(adminList.size() - 1);
		}
		System.out.println(message);
	}

	/**
	 * Authenticates the admin
	 */
	public void adminLoginAuthentication() {
		Boolean isAdmin = false;
		for (Admins admin : adminList) {
			if (admin.getUsername().equals(inputUserName) && admin.getPassword().equals(inputPassword)) {
				isAdmin = true;
				accIndex = adminList.indexOf(admin);
				break;
			}
		}
		if (isAdmin) {
			System.out.println("Successfull admin login ! \n");
			displayAdminOptionMenu();
		} else {
			System.out.println("Invalid username or password, Please try again");
		}

	}

	/**
	 * Displays reader options
	 */
	public void displayReaderMenu() {
		do {
			System.out.println(READER_OPTION_MENU + "\n3 - Log Out");
			option = inputReader.readAndValidateInteger("Options");
			if (option == 1) {
				getViewMaterialOptions();
				displayReaderMenu();// View the materials
			} else if (option == 2) {
				doPurchaseOperations(); // To purchase the material
			} else if (option == 3) {
				System.out.println("Logging off");
			} else {
				System.out.println(DEFAULT_INVALID_TEXT);
			}
		} while (option != 3);
	}

	/**
	 * Displays and to do purchase functionalities
	 */
	public void doPurchaseOperations() {
		System.out.println(
				"Choose from below :\n \n1 - Purchase new book \n2 - View purchased materials \n3 - Return \n4 - Rate books");
		option = inputReader.readAndValidateInteger("Option");
		if (option == 1) {
			purchaseBookById();
		} else if (option == 2) {
			viewPurchasedBooks();
		} else if (option == 3) {
			returnPurchasedMaterial();
		} else if (option == 4) {
			System.out.println("Feature coming soon!!");
		} else {
			System.out.println(DEFAULT_INVALID_TEXT);
		}
	}

	/**
	 * Purchase book by Id accepted from user
	 */
	public void purchaseBookById() {
		String message = "Book with ID does not exist";
		System.out.println("Enter id of the book to purchased :");
		inputId = input.nextLine();
		if (checkBookIdExist() == true) {
			purchaseMaterialList.add(new PurchasedMaterial()); // new object of class purchase material
			int currentIndex = purchaseMaterialList.size() - 1; // index of current object in the arraylist
			purchaseMaterialList.get(currentIndex).setId(inputId);
			purchaseMaterialList.get(currentIndex).setReaderName(readerList.get(accIndex).getName());
			purchaseMaterialList.get(currentIndex).setName(materialList.get(materialIndex).getName());
			purchaseMaterialList.get(currentIndex).generateDueDate();
			materialList.get(materialIndex).reduceStock(); // reduces stock by 1
			message = "Book Id :" + inputId + " purchased !";
		}
		System.out.println(message);
	}

	/**
	 * To return purchased material
	 */
	public void returnPurchasedMaterial() {
		Iterator<PurchasedMaterial> iteratorThroughPurchaseList = purchaseMaterialList.iterator();
		getArrayListofPurchasedMaterialIds();
		System.out.println("Enter id of the book to return :");
		inputId = input.nextLine();
		if (checkBookIdExist() == true && purchaseIdList.contains(inputId)) {
			materialList.get(materialIndex).addStock(); // reduces stock by 1
			purchaseIdList.remove(inputId);
			while (iteratorThroughPurchaseList.hasNext()) {
				PurchasedMaterial purchaseBook = iteratorThroughPurchaseList.next();
				if (inputId.equals(purchaseBook.getId()) // if input id equals id of purchased book
						&& purchaseBook.getReaderName().equals(readerList.get(accIndex).getName())) { // if Reader is
																										// same as the
																										// one logged in
					iteratorThroughPurchaseList.remove(); // removes the object from purchaseMaterialsList
					System.out.println("Successfully returned");
				}
			}
		} else {
			System.out.println("Book with given ID not in your purchase list");
		}
		displayReaderMenu();
	}

	/**
	 * To get an array of purchasedMaterials
	 */
	public void getArrayListofPurchasedMaterialIds() {
		for (PurchasedMaterial purchaseMaterial : purchaseMaterialList) {
			if (readerList.get(accIndex).getName().equals(purchaseMaterial.getReaderName())) {
				System.out.println("Id : " + purchaseMaterial.getId() + " ,Name : " + purchaseMaterial.getName());
				purchaseIdList.add(purchaseMaterial.getId());
			}
		}
	}

	/**
	 * View books purchased by logged in user
	 */
	public void viewPurchasedBooks() {
		String message = "No books purchased";
		int stockAvailable = materialList.get(materialIndex).getTotalStock();
		for (PurchasedMaterial purchaseMaterial : purchaseMaterialList) {
			if (readerList.get(accIndex).getName().equals(purchaseMaterial.getReaderName())) {
				message = " ";
				LocalDate dueDate = LocalDate.parse(purchaseMaterial.getDueDate(), dateformat);
				LocalDate now = LocalDate.now();
				long dueIn = now.until(dueDate, ChronoUnit.DAYS);
				System.out.println("Id : " + purchaseMaterial.getId() + " , Name : " + purchaseMaterial.getName()
						+ " , Due date :" + purchaseMaterial.getDueDate() + " , Due in : " + dueIn + " days" + ""
						+ " , Stock Available :" + stockAvailable);
			}
		}
		System.out.println(message);
	}

	/**
	 * To check presence of particular id in the material list
	 * 
	 * @return isAvailable , flag that denotes presence of particular id in the
	 *         material list
	 */
	public boolean checkBookIdExist() {
		boolean isAvailable = false;
		for (Materials material : materialList) {
			if (material.getId().equals(inputId)) {
				materialIndex = materialList.indexOf(material);
				isAvailable = true;
				break;
			}
			if (isAvailable) {
				System.out.println("Book with given ID does not exist,Try again ");
				purchaseBookById();
			}
		}
		return isAvailable;
	}

	/**
	 * Get view options for materials
	 */
	public void getViewMaterialOptions() {
		System.out.println(
				"Select from below :\n \n1 - Sort material list by id \n2 - Sort by catagory \n3 - Sort by name or created date-time \n4 - Search materials");
		option = inputReader.readAndValidateInteger("Option");
		if (option == 1) {
			sortByIdAndDisplay(); // Sort by Id
		} else if (option == 2) {
			viewMaterialsByCatagory(); // view by catagory
		} else if (option == 3) {
			sortByNameAndDisplay(); // sort by name and display
			sortByDateAndDisplay(); // sort by date and display
		} else if (option == 4) {
			findByIdNameAuthor(); // Find by Id or Name or Author
		} else {
			System.out.println(DEFAULT_INVALID_TEXT);
		}
	}

	/**
	 * Find a material by author, id , name
	 */
	public void findByIdNameAuthor() {
		String message = "Material not avaliable";
		System.out.println("Enter Id, Name or Author :");
		String searchKey = input.nextLine();
		for (Materials material : materialList) {
			if (material.getId().equalsIgnoreCase(searchKey) || material.getAuthor().equalsIgnoreCase(searchKey)
					|| material.getName().equalsIgnoreCase(searchKey)) {
				
				System.out.println(material.toString()); // print material with matching id,author or name
				message=" ";
			}
		}
		System.out.println(message);
	}

	/**
	 * Sort by id
	 */
	public void sortByIdAndDisplay() {
		Collections.sort(materialList, new SortMaterialById());
		materialList.stream().filter(x -> x.getId().startsWith("A")).forEach(x -> System.out.println(x+"\n"));
		
		materialList.stream().filter(x -> x.getId().startsWith("P")).forEach(x -> System.out.println(x+"\n"));
		
		materialList.stream().filter(x -> x.getId().startsWith("V")).forEach(x -> System.out.println(x+"\n"));
		

	}

	/**
	 * sort by name of material
	 */
	public void sortByNameAndDisplay() {
		System.out.println("---------------- Sorted by name ----------------\n ");
		Collections.sort(materialList, new SortMaterialByName());
		materialList.stream().forEach(x -> System.out.println(x+"\n"));
		System.out.println();
	}

	/**
	 * sort by date
	 */
	public void sortByDateAndDisplay() {
		System.out.println("---------------- Sorted by date ----------------\n ");
		Collections.sort(materialList, new SortByDate());
		materialList.stream().forEach(x -> System.out.println(x+"\n"));
		System.out.println();

	}

	/**
	 * View materials by catagory - Printed,Audio,Video
	 */
	public void viewMaterialsByCatagory() {
		System.out.println("------------ Printed Books ------------\n");
		materialList.stream().filter(x -> x.getId().startsWith("A")).forEach(x -> System.out.println(x+"\n"));
		System.out.println();
		System.out.println("------------ Audio Books ------------\n");
		materialList.stream().filter(x -> x.getId().startsWith("P")).forEach(x -> System.out.println(x+"\n"));
		System.out.println();
		System.out.println("------------ Video Books ------------\n");
		materialList.stream().filter(x -> x.getId().startsWith("V")).forEach(x -> System.out.println(x+"\n"));
		System.out.println();
	}

	/**
	 * displays Options in admin menu
	 */
	/**
	 * 
	 */
	public void displayAdminOptionMenu() {
		do {
			System.out.println(ADMIN_OPTIONMENU_TEXT);
			option = inputReader.readAndValidateInteger("Option");
			if (option == 1) {
				adminAddMaterial();
			} else if (option == 2) {
				deleteMaterial();
			} else if (option == 3) {
				adminSignUp();
			} else if (option == 4) {
				getViewMaterialOptions();
			} else if (option == 5) {
				System.out.println("Logging off....");
			} else {
				System.out.println(DEFAULT_INVALID_TEXT);
			}
		} while (option != 5);
	}

	/**
	 * Delete the printed book by id given by user
	 */
	public void deleteMaterial() {
		System.out.println("Enter ID of book to delete :");
		inputId = input.nextLine();
		for (Materials material : materialList) {
			if (material.getId().equals(inputId)) {
				materialIndex = materialList.indexOf(material);
				isPresent = true;
			}
		}
		materialList.remove(materialIndex);
		checkIfRemoved();
	}

	/**
	 * To check if the material of chosen id is removed
	 */
	public void checkIfRemoved() {
		if (isPresent) {
			System.out.println("Material removed successfully");
		} else {
			System.out.println("No such material exist");
		}
		isPresent = false;
	}

	/**
	 * Allows admin to add new materials
	 */
	public void adminAddMaterial() {
		System.out.println(TYPE_OF_MATERIAL_TEXT);
		System.out.println("4 - Exit");
		option = inputReader.readAndValidateInteger("Option");
		switch (option) {
		case 1:
			getPrintedMaterialDetails();
			break;
		case 2:
			getAudioMaterialDetails();
			break;
		case 3:
			getVideoMaterialDetails();
			break;
		default:
			System.out.println(DEFAULT_INVALID_TEXT);
		}
	}

	/**
	 * To write materialList to files
	 */
	public void writeMaterialsListToFile() {
		try {
			FileOutputStream writeMaterialData = new FileOutputStream(pathLibraryDetails);
			ObjectOutputStream writeMaterialsStream = new ObjectOutputStream(writeMaterialData);
			writeMaterialsStream.writeObject(materialList);
			writeMaterialsStream.close();
			writeMaterialData.close();
		} catch (IOException e) {
			System.out.println("Cannot write into file");
		}
	}

	/**
	 * To write adminList to file
	 */
	public void writeAdminDetailsToFile() {
		try {

			FileOutputStream writeAdminData = new FileOutputStream(pathAdminDetails);
			ObjectOutputStream writeAdminStream = new ObjectOutputStream(writeAdminData);
			writeAdminStream.writeObject(adminList);
			writeAdminStream.close();
			writeAdminData.close();
		} catch (IOException e) {
			System.out.println("Cannot write into file");
		}
	}

	/**
	 * To write readerList to file
	 */
	public void writeReaderDetailsToFile() {
		try {
			FileOutputStream writeReaderData = new FileOutputStream(pathReaderDetails);
			ObjectOutputStream writeReaderStream = new ObjectOutputStream(writeReaderData);
			writeReaderStream.writeObject(readerList);
			writeReaderStream.close();
			writeReaderData.close();
		} catch (IOException e) {
			System.out.println("Cannot write into file");
		}
	}

	/**
	 * Read materials details from file
	 */
	public void readMaterialsListFromFile() {
		try {
			if (Files.exists(Paths.get(pathLibraryDetails))) {
				FileInputStream readData = new FileInputStream(pathLibraryDetails);
				ObjectInputStream readStream = new ObjectInputStream(readData);
				ArrayList<Materials> loadedMaterialsList = (ArrayList<Materials>) readStream.readObject();
				materialList.addAll(loadedMaterialsList);
				readStream.close();
				readData.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(" ");
		}

	}

	/**
	 * To read user admin details from file
	 */
	public void readAdminsDetailsFromFile() {
		try {
			if (Files.exists(Paths.get(pathAdminDetails))) {
				FileInputStream readAdminData = new FileInputStream(pathAdminDetails);

				ObjectInputStream readAdminStream = new ObjectInputStream(readAdminData);
				ArrayList<Admins> loadedAdminList = (ArrayList<Admins>) readAdminStream.readObject();
				adminList.addAll(loadedAdminList);

				readAdminStream.close();

			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(" ");
		}
	}

	public void readReaderDetailsFromFile() {
		try {
			if (Files.exists(Paths.get(pathReaderDetails))) {
				FileInputStream readReaderData = new FileInputStream(pathReaderDetails);
				ObjectInputStream readReaderStream = new ObjectInputStream(readReaderData);
				ArrayList<Readers> loadedReaderList = (ArrayList<Readers>) readReaderStream.readObject();
				readerList.addAll(loadedReaderList);
				readReaderStream.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(" ");
		}
	}

	/**
	 * Creates a new object of Printed class and add it to materialsList
	 */
	public void getPrintedMaterialDetails() {
		printedList.add(new Printed());
		int currentPrintedindex = printedList.size() - 1;
		System.out.println(MATERIAL_NAME_TEXT);
		printedList.get(currentPrintedindex).setName(input.nextLine());
		System.out.println(MATERIAL_AUTHOR_TEXT);
		printedList.get(currentPrintedindex).setAuthor(inputReader.readAndValidateName("Author Name "));
		printedList.get(currentPrintedindex).setCreatedBy(adminList.get(accIndex).getName());
		printedList.get(currentPrintedindex).generateCreatedAt();
		System.out.println(MATERIAL_STOCK_TEXT);
		printedList.get(currentPrintedindex).setTotalStock(inputReader.readAndValidateInteger("Total Stock"));
		System.out.println("Enter number of pages :");
		printedList.get(currentPrintedindex).setNoOfPages(inputReader.readAndValidateInteger("Page number"));
		printedList.get(currentPrintedindex).generateAndSetId();
		System.out.println("Enter ISBN : ");
		printedList.get(currentPrintedindex).setIsbn(input.nextLine());
		materialList.add(printedList.get(currentPrintedindex));
	}

	/**
	 * Creates a new object of Audio class and add it to materialsList
	 */
	public void getAudioMaterialDetails() {
		audioList.add(new Audio());
		int currentPrintedindex = audioList.size() - 1;
		System.out.println(MATERIAL_NAME_TEXT);
		audioList.get(currentPrintedindex).setName(input.nextLine());
		System.out.println(MATERIAL_AUTHOR_TEXT);
		audioList.get(currentPrintedindex).setAuthor(inputReader.readAndValidateName("Author Name"));
		audioList.get(currentPrintedindex).setCreatedBy(adminList.get(accIndex).getName());
		audioList.get(currentPrintedindex).generateCreatedAt();
		System.out.println(MATERIAL_STOCK_TEXT);
		audioList.get(currentPrintedindex).setTotalStock(inputReader.readAndValidateInteger("Total Stock"));
		audioList.get(currentPrintedindex).generateAndSetId();
		System.out.println("Enter duration :");
		audioList.get(currentPrintedindex).setDuration(inputReader.readAndValidateDouble("Duration"));
		materialList.add(audioList.get(currentPrintedindex));
	}

	/**
	 * Creates a new object of Video class and add it to materialsList
	 */
	public void getVideoMaterialDetails() {
		videoList.add(new Video());
		int currentPrintedindex = videoList.size() - 1;
		System.out.println(MATERIAL_NAME_TEXT);
		videoList.get(currentPrintedindex).setName(input.nextLine());
		System.out.println(MATERIAL_AUTHOR_TEXT);
		videoList.get(currentPrintedindex).setAuthor(inputReader.readAndValidateName("Author Name"));
		videoList.get(currentPrintedindex).setCreatedBy(adminList.get(accIndex).getName());
		videoList.get(currentPrintedindex).generateCreatedAt();
		System.out.println(MATERIAL_STOCK_TEXT);
		videoList.get(currentPrintedindex).setTotalStock(inputReader.readAndValidateInteger("Total Stock"));
		videoList.get(currentPrintedindex).generateAndSetId();
		System.out.println("Enter duration :");
		videoList.get(currentPrintedindex).setDuration(inputReader.readAndValidateDouble("Duration"));
		materialList.add(videoList.get(currentPrintedindex));
	}

}
