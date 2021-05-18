/* Elise Frigoli 
 * CSC 422 Software Engineering 
 * Professor Susan Furtney
 * 05/15/21
 * Week 01 - Assignment 01 Part 2
 * 
 * This program creates a database to store pet names and ages. It allows the user
 * to view the database, add new pets, remove existing pets, update existing pet information, 
 * and search the database for a pet by either name or age. 
 * 
 * 05/15/21 - searchByAge() and searchByName() methods added to allow the user to search the database
 * for pet records that match a given name or age. 
 *  
 * 05/16/21 - updatePet() and removePet() methods added to allow the user to update a pet record
 * or delete a pet record from the database.
 * 
 * 05/17/21 - searchByAge() and searchByName() and updatePet() methods hidden from the menu so 
 * that users can no longer access them. 
 * Refactored addPet() to loop through the addition process until the user enters the word 'done'. 
 * Limiting addPet() to only add up to 5 pets total to the database. 
 * Requiring the user to enter only two tokens when adding a new pet: the name and the age.
 * Limiting the age of new pets to a value between 1-20 years. 
 * Limiting the ID input for deletePet() to only values of array indexes. 
 * 
 * Created the loadFile() method which loads data from a text file line-by-line, parsing the name and age values
 * and using the values to create an ArrayList of pet objects. */

// Importing all standard Java utilities.
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//Establishing the class.
public class PetDatabase {
	
	
	// Establishing the main method.
	public static void main(String[] args) {
		
		
		// Printing the output
		System.out.println("Pet database program.");
		
		// Creating the arrayList that will store pet information, loading the data from a text file
		 ArrayList <Pet> petList = loadFile("petDatabase.txt");
		
		// Displaying the user selection menu
		displayMenu(petList);
	
	}
	
	
	/* Defining the loadFile method, which accepts a String parameter and returns an ArrayList. This method
	 * reads the contents of the file whose name is passed as the parameter, and loads each line of the file 
	 * into an element of an ArrayList. */
	public static ArrayList loadFile(String fileName) {
		// Creating the buffered reader to read the file contents
		BufferedReader fileReader = null;
		
		//  Try/catch statement to read the file
		try {
			fileReader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) { // If no file with the given filename is found
			System.out.println("No file found matching that file name.");
			e.printStackTrace();
		} 
		
		// Creating an arrayList to hold the pet objects loaded from the file
		ArrayList<Pet> petList = new ArrayList<Pet>();
		// Creating an empty String to contain the contents of each line of the file
		String line = null;
		// Try/catch statement to read the first line of the file and store it as a String var
		try {
			line = fileReader.readLine();
		} catch (IOException e) { // If there is an error with the input
			System.out.println("File could not be read.");
			e.printStackTrace();
		} 
		
		// For each line of the file that has text
		while (line != null) { 
			// Splitting the input string into tokens delimited by whitespace
			StringTokenizer loadedInfoTokenizer = new StringTokenizer(line," ");
	    	// Storing the first token as the pet name
			String loadedPetName = loadedInfoTokenizer.nextToken();
			// Storing the next token as the pet age
			String loadedPetAgeString = loadedInfoTokenizer.nextToken();
			// Parsing the pet age String into an int value
			int loadedPetAge = Integer.parseInt(loadedPetAgeString);
			
			// Adding a new pet object to the arrayList using the data entered by the user
			petList.add(new Pet(loadedPetName, loadedPetAge));
			
			// Try/catch statement to read the remaining lines of the file and store them as a String var
			try {
				line = fileReader.readLine();
			} catch (IOException e) { // If there is an error with the input
				System.out.println("File could not be read.");
				e.printStackTrace();
			} 
		} 
		
		// Try/catch statement to close the buffered reader
		try {
			fileReader.close();
		} catch (IOException e) { // If there is an error closing the buffered reader
			System.out.println("File reader could not been closed.");
			e.printStackTrace();
		}
		
		// Return the arrayList of pet objects
		return petList;
	}
	
	
	/* Defining the displayMenu method which requires an arrayList parameter and prints a menu of options for the user. 
	 * Methods to use on the arrayList are called based on the user selection. */
	public static void displayMenu(ArrayList list) {
		// Printing the list of options
		System.out.println("\nWhat would you like to do?");
		System.out.println("1) View all pets");
		System.out.println("2) Add more pets");
		System.out.println("3) Remove an existing pet");
		System.out.println("4) Exit program");
		
		// Accepting the user's menu selection
		Scanner input = new Scanner(System.in);
		int userInput = input.nextInt();
		
		// Displaying their selected option
		System.out.println("Your choice: " + userInput);
		
		// Using a switch based on their selection
		switch(userInput) {
			// If they chose to display the table of pets
			case 1:
				// Calling the showPets method
				showPets(list);
				displayMenu(list);
				break;
			// If they chose to add a new pet to the table
			case 2:
				// Calling the addPet method
				addPet(list);
				displayMenu(list);
				break;
			// If they chose to delete an existing pet
			case 3:
				// Calling the deletePet method
				deletePet(list);
				displayMenu(list);
				break;
			// If they chose to exit the program
			case 4:
				// Print an exit message and terminate the program
				System.out.println("Goodbye!");
				System.exit(0);
			// If they chose anything not on the selection menu
			default:
				// Prompt them to select an existing option
				System.out.println("Please select a number from the menu.");
				// Print the list again
				displayMenu(list);
		}
	}
	
	
	/* Defining the showPets method which requires an arrayList parameter and prints a table
	 * populated by the current arrayList of pet information. */
	public static void showPets(ArrayList<Pet> list) {
		
		/* Setting up table headings for the three columns, using the built-in print formatter. 
		 * Using “-“ left-justifies the headings, the numerical value sets the width in characters, 
		 * and “s” produces a string output type. The format ends with “\n” to create a new line.*/
		System.out.println("+---------------------------+");
		System.out.printf("%-2s%-3s%-2s%-15s%-2s%-4s%-2s\n","|", "ID", "|", "NAME", "|", "AGE", "|");
		System.out.println("+---------------------------+");	
		
		// Initializing a count for the number of rows in the table
		int count = 0;
		
		// If there are rows of pet data in the table
		if (list.size() > 0) {
			// For each pet in the list
			for(int i = 0; i < list.size(); i++ ) {
				// Print a formatted row in the table containing their index, name, and age
				System.out.printf("%-2s%3s%-2s%-15s%-2s%4s%-2s\n","|", i, "|", list.get(i).getName(), "|", list.get(i).getAge(), "|");
		  		// Increase the row counter
				count++;
		       }
			// If there are no rows of pet data in the table
			} else {
				// Inform user that there are no rows to display
				System.out.printf("%-2s%-26s%-2s\n","|", "No pets to display.", "|");
			}
		
		// Printing the table footer and row count
		System.out.println("+---------------------------+");
		System.out.println(count + " rows in set.");
		
	}
	
	
	/* Defining the addPet method which requires an arrayList parameter and creates a new pet entry
	 * in the table with data based on user input. */
	public static void addPet(ArrayList<Pet> list) {
		
	
			// Adding an empty String to contain new pet data
			String newPetInfo = "";
			// Setting a control var for the do/while loop
			Boolean addingPets = true;
			// Counting the number of pets added at this time
			int addedPetCount = 0;
			
			// Do/while loop to add new pet info until the user types 'done' or the maximum number of pets are in the database
			do {
				// Accepting user input for the pet name and age
				Scanner newPet = new Scanner(System.in);
				System.out.print("Add pet (name, age): ");
				newPetInfo = newPet.nextLine();
				
				// If the user does not enter the word 'done' 
				if (!(newPetInfo.matches("done"))) {
					// If there are fewer than 5 entries in the database
					if (list.size() < 5) {
		
						// Splitting the input string into tokens delimited by whitespace
						StringTokenizer petInfoTokenizer = new StringTokenizer(newPetInfo," ");
						// Count the number of tokens entered by the user
					    int tokenCount = petInfoTokenizer.countTokens();
					    
					    // If the user enters anything except a single name and a single age
					    if (tokenCount != 2) {
					    	// Inform them of the error and loop for another pet
					    	System.out.println(newPetInfo + " is not a valid input.");
					    } else { // If the user enters a single name and a single age
					    	// Storing the first token as the pet name
							String petName = petInfoTokenizer.nextToken();
							// Storing the next token as the pet age
							String petAgeString = petInfoTokenizer.nextToken();
							// Parsing the pet age String into an int value
							int petAge = Integer.parseInt(petAgeString);
							
							// If an invalid age is entered
							if (petAge < 1 || petAge > 20) {
								// Inform the user of the error
								System.out.println(petAge + " is not a valid age.");
							} else { // If the age entered is between 1-20 years old
								// Adding a new pet object to the arrayList using the data entered by the user
								list.add(new Pet(petName, petAge));
								// Increment the count of pets added at this time
								addedPetCount++;
							}
					    }
						
					} else { // If there are already 5 entries in the database
						System.out.println("Error: Database is full.");
						// Exit the addPet loop
						break;
					}
				} else { // If the user enters the word 'done'
					// Change the control var to exit the loop
					addingPets = false;
				}
			} while (addingPets == true);

			// Inform the user how many pets were added at this time
			System.out.println(addedPetCount + " pets added.");
		}
		
		
	
	
	/* Defining the updatePet method which requires an arrayList parameter and prompts the user for 
	 * an index value. User is then prompted for a name and age to update the selected pet record. */
	public static void updatePet(ArrayList<Pet> list) {
		
		// If there are pets in the database
		if (list.size() > 0) {
			// Displaying the current list so the user can see the options
			showPets(list);

			Scanner updates = new Scanner(System.in);
			// Accepting user input for which pet to update
			System.out.print("Enter the pet ID to update: ");
			int updateID = updates.nextInt();
			
			// If the pet ID is within the bounds of the arrayList
			if (updateID < list.size()) {
				// Prompting for new name and age information
				updates.nextLine();
				System.out.print("Enter a new name: ");
				String newName = updates.nextLine();
				System.out.print("Enter a new age: ");
				int newAge = updates.nextInt();
				
				// Informing the user that the information is changed
				System.out.println(list.get(updateID).getName() + " " + list.get(updateID).getAge() + " changed to " + newName + " " + newAge + ".");
				
				// Using the set methods for the Pet object to update the name and age
				list.get(updateID).setName(newName);
				list.get(updateID).setAge(newAge);
			} else {
				// If the pet ID chosen is not within the bounds of the arrayList, inform the user
				System.out.println("No matching pet ID found.");
			}	
		} else {
			// If there are no pet records in the database
			System.out.println("No pet records to update.");
		}
		
	}
	
	
	/* Defining the deletePet method which requires an arrayList parameter and prompts the user for 
	 * an index value. The selected pet record is then deleted from the table. */
	public static void deletePet(ArrayList<Pet> list) {
		
		// If there are pets in the database
		if (list.size() > 0) {
			// Displaying the current list so the user can see the options
			showPets(list);
		
			Scanner removePet = new Scanner(System.in);
			// Accepting user input for which pet to remove
			System.out.print("Enter the pet ID to remove: ");
			int removeID = removePet.nextInt();
			
			// If the pet ID is within the bounds of the arrayList
			if (removeID > -1 && removeID < list.size()) {
				// Inform the user which pet has been removed
				System.out.println(list.get(removeID).getName() + " is removed.");
				
				// Remove the pet from the list
				list.remove(removeID);
			} else {
				// If the pet ID chosen is not within the bounds of the arrayList, inform the user
				System.out.println("Error: ID " + removeID + " does not exist.");
			}
			
		} else {
			// If there are no pet records in the database
			System.out.println("No pet records to remove.");
		}
	}
	
	
	/* Defining the searchByName method which requires an arrayList parameter and returns
	 * a printed table with matching entries to the name entered by the user. */
	public static void searchByName(ArrayList<Pet> list) {
		
		// Accepting user input for the pet name to search 
		Scanner newSearch = new Scanner(System.in);
		System.out.print("Enter a name to search: ");
		String searchName = newSearch.nextLine();
		
		// If there are rows of pet data in the table
		if (list.size() > 0) {
			int count = 0;
			
			// Creating the arrayList that will store pet information
			ArrayList <Pet> nameResults = new ArrayList<Pet>();
			
			// For each pet in the list
			for(int i = 0; i < list.size(); i++ ) {
				  // If the name of the pet matches the searched name
		          if (list.get(i).getName().toLowerCase().contains(searchName.toLowerCase())){
		  			  // Adding a new pet object to the arrayList using the data entered by the user
		  			  nameResults.add(new Pet(list.get(i).getName(), list.get(i).getAge()));
		       
		          }
		       }
			// Print out the table of matching results
			showPets(nameResults);
			// If there are no rows of pet data in the table
			} else {
				// Inform user that there are no rows to display
				System.out.printf("%-2s%-26s%-2s\n","|", "No pets to display.", "|");
			}

	}
	
	
	/* Defining the searchByAge method which requires an arrayList parameter and returns
	 * a printed table with matching entries to the age entered by the user. */
	public static void searchByAge(ArrayList<Pet> list) {
		
		// Accepting user input for the pet age to search 
		Scanner newSearch = new Scanner(System.in);
		System.out.print("Enter age to search: ");
		int searchAge = newSearch.nextInt();
      
		// If there are rows of pet data in the table
		if (list.size() > 0) {
			
			// Creating the arrayList that will store pet information
			ArrayList <Pet> ageResults = new ArrayList<Pet>();
			
			// For each pet in the list
			for(int i = 0; i < list.size(); i++ ) {
				 // If the age of the pet matches the searched age
		          if (list.get(i).getAge() == searchAge){
		  			  // Adding a new pet object to the arrayList using the data entered by the user
		  			  ageResults.add(new Pet(list.get(i).getName(), list.get(i).getAge()));
		          }
		       }
			// Print out the table of matching results
			showPets(ageResults);
			// If there are no rows of pet data in the table
			} else {
				// Inform user that there are no rows to display
				System.out.printf("%-2s%-26s%-2s\n","|", "No pets to display.", "|");
			}
		
	}

}