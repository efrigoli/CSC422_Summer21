/* Elise Frigoli 
 * CSC 422 Software Engineering 
 * Professor Susan Furtney
 * 05/16/21
 * Week 01 - Assignment 01 Part 2
 * 
 * This is a class definition for the class Pet. The purpose of this class is to 
 * store the name and age of a pet in the pet database.*/

// Establishing the class
public class Pet {
	// All instance variables are set to private so they cannot be directly edited by the user.
	// Name of the pet
	private String NAME;
	// Age of the pet
	private int AGE;

	
	/* The constructor for creating a pet object, requiring two parameters - 
	 * for the name and age */
	public Pet(String name, int age) {
		// Assigning the name parameter value to the pet object's name data field
		NAME = name;
		// Assigning the age parameter value to the pet object's age data field
		AGE = age;
	}
	
	// Defining the getName accessor method which returns the current pet's name 
	public String getName() {
		return NAME;
	}
	
	// Defining the getAge accessor method which returns the current pet's age
	public int getAge() {
		return AGE;
	}
	
}

