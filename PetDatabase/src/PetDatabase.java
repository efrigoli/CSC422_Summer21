/* Elise Frigoli 
 * CSC 422 Software Engineering 
 * Professor Susan Furtney
 * 05/16/21
 * Week 01 - Assignment 01 Part 2
 * 
 * This program creates a database to store pet names and ages. It allows the user
 * to view the database, add new pets, remove existing pets, update existing pet information, 
 * and search the database for a pet by either name or age. */

// Importing all standard Java utilities.
import java.util.*;

//Establishing the class.
public class PetDatabase {
	
	
	// Establishing the main method.
	public static void main(String[] args) {
		
		
		// Printing the output
		System.out.println("Pet database program.");
		
		// Creating the arrayList that will store pet information
		ArrayList <Pet> petList = new ArrayList<Pet>();
		
		// Displaying the user selection menu
		displayMenu(petList);
	
	}
	
	/* Defining the displayMenu method which requires an arrayList parameter and prints a menu of options for the user. 
	 * Methods to use on the arrayList are called based on the user selection. */
	public static void displayMenu(ArrayList list) {
		// Printing the list of options
		System.out.println("\nWhat would you like to do?");
		System.out.println("1) View all pets");
		System.out.println("2) Add more pets");
		System.out.println("3) Exit program");
		
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
				break;
			// If they chose to add a new pet to the table
			case 2:
				// Calling the addPet method
				addPet(list);
				break;
			// If they chose to exit the program
			case 3:
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
		
		// Displaying the menu to the user
		displayMenu(list);
		
	}
	
	
	/* Defining the addPet method which requires an arrayList parameter and creates a new pet entry
	 * in the table with data based on user input. */
	public static void addPet(ArrayList<Pet> list) {
		
		// Accepting user input for the pet name and age
		Scanner newPet = new Scanner(System.in);
		System.out.print("Enter the name of the new pet: ");
		String newPetName = newPet.nextLine();
		System.out.print("Enter the age of the new pet: ");
		int newPetAge = newPet.nextInt();
		
		// Adding a new pet object to the arrayList using the data entered by the user
		list.add(new Pet(newPetName, newPetAge));
		
		// Showing the table of pet data, including the new pet information
		showPets(list);
		
		// Displaying the menu to the user
		displayMenu(list);
		
	}

}