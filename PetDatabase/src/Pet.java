/* Elise Frigoli 
 * CSC 422 Software Engineering 
 * Professor Susan Furtney
 * 05/15/21
 * Week 01 - Assignment 01 Part 2
 * 
 * This is a class definition for the class Pet. The purpose of this class is to 
 * store the name and age of a pet in the pet database.
 * 
 * 05/16/21 - Added setName() and setAge() methods to update the name and age of a pet object. */

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
	
	// Defining the setName mutator method which requires a String parameter for updating the pet name
	public void setName(String newName) {
		// Assigning the new name parameter value to the current pet to update its name
		NAME = newName;
	}
	
	// Defining the setAge mutator method which requires an int parameter for updating the pet age
	public void setAge(int newAge) {
		// Assigning the new age parameter value to the current pet to update its age
		AGE = newAge;
	}
	
}

