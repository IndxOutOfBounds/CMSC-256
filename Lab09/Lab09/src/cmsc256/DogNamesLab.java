package cmsc256;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.*;

public class DogNamesLab {
	private static String promptForFileName() {
		System.out.println("Enter the file name: ");
		@SuppressWarnings("resource")
		Scanner keyIn = new Scanner(System.in);
		return keyIn.next();
	}

	private static Scanner openFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		while (!file.exists()) {
			file = new File(promptForFileName());
		}
		return new Scanner(file);
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		// Read data file to build data structure
		ArrayList<Dog> doglist = new ArrayList<>();
		
		try {
			// verify file and create file Scanner
			 Scanner fileReader = openFile("Dog_Names.csv");

			//  Discard header line
			 fileReader.nextLine();
			 
			 while(fileReader.hasNextLine()) {
			 	String line = fileReader.nextLine();
			 	int commaIndex = line.indexOf(',');
			 	String name = line.substring(0, commaIndex).trim();
			 	int count = Integer.parseInt(line.substring(commaIndex+1).trim());
			 	doglist.add(new Dog(name, count));
			 }
			 fileReader.close();
		}
		catch(FileNotFoundException noFile){
			System.out.println("There was an error opening or reading from the file.");
			System.exit(0);
		}

		Scanner readInput = new Scanner(System.in);
		String prompt = "\nWhat do you want to do?\n" 
				+ "\t1. Check a dog name\n" + "\t2. See all the dog names\n"
				 + "\t3. Play a game\n" + "\t4. Exit"
				 		+ ".\n"
				+ "Enter the number corresponding to your choice.";
		
		System.out.println(prompt);
		int option = readInput.nextInt();
		
		switch(option) {
		case 1:
			System.out.println("Enter a dog’s name?");
			String name = in.nextLine();
			int nameCount = getCountForDog(doglist, name);
			System.out.println(name + " is registered " + nameCount + " times.");
			break;
		case 2:
			System.out.println(getDogNamesAlphabetically(doglist));
			break;
		case 3:
			playGuessingGame(doglist, in);
			break;
		default: System.out.println("Invalid option.");
		}
		in.close();
	}

	public static int getCountForDog(ArrayList<Dog> dogs, String name) {
		// TODO: 
		// search the list for the Dog named name 
		// display dogs name and the number of registrations for that name
		int count = 0;
        for (int i = 0; i < dogs.size()-1; i++) {
			Dog tempDog = (dogs.get(i));
			if (tempDog.getDogName().equalsIgnoreCase(name)) {
				count = count + dogs.get(i).getCount();
			}
		}
        return count;	
	}
	
	public static String getDogNamesAlphabetically(ArrayList<Dog> dogs) {
		// TODO Sort the list of Dog by name return
		int size = dogs.size();
		for (int i = 1; i < size; i++) {
			if (dogs.get(i).compareTo(dogs.get(i - 1)) < 0) {
				Dog temp = dogs.get(i);
				dogs.set(i, dogs.get(i - 1));
				dogs.set(i - 1, temp);
				int j = i - 1;
				while (j > 0) {
					if (dogs.get(j).compareTo(dogs.get(j - 1)) < 0) {
						temp = dogs.get(j);
						dogs.set(j - 1, dogs.get(j));
						dogs.set(j, temp);
					}
					j--;
				}
			}

		}
		return dogs.toString();
	}

	public static void playGuessingGame(ArrayList<Dog> dogs, Scanner readIn) {
		// TODO: implement the guessing game.
		  // while not done playing
			// pull two random Dogs from the list
			// display the names and prompt player to pick the more popular name
		    // read player input
			// increment total number of guesses
			// check registration counts to determine if player is correct
				// if correct, respond and increment number of correct answers
				// if wrong, respond
			// ask user if they want to quit
				// if yes, display number of correct out of total number of guesses
				// if no, continue
		boolean stillPlaying = true;
		Scanner in = new Scanner(System.in);
		int correctGuesses = 0;
		int totalGuesses = 0;
		while (stillPlaying) {
			int dogOne = (int) (Math.random() * (dogs.size()-1));
			int dogTwo = (int) (Math.random() * (dogs.size()-1));
			while (dogOne == dogTwo) {
				dogTwo = (int) (Math.random() * (dogs.size()-1));
			}
			System.out.println("Which name is more popular for Anchorage dogs? (Type 1 or 2)");
			Dog firstDog = dogs.get(dogOne);
			Dog secondDog = dogs.get(dogTwo);
			System.out.println("1. " + firstDog.getDogName());
			System.out.println("2. " + secondDog.getDogName());
			int userChoice = in.nextInt();
			totalGuesses = totalGuesses + 1;
			int popularChoice;
			String popularDog;
			if (firstDog.getCount() > secondDog.getCount()) {
				popularChoice = 1;
				popularDog = dogs.get(dogOne).getDogName();
			} else {
				popularChoice = 2;
				popularDog = dogs.get(dogTwo).getDogName();
			}
			if (userChoice == popularChoice) {
				correctGuesses = correctGuesses + 1;
				System.out.println("Yes, that's right.");
				System.out.println("Do you want to play again? (Y/N)");
			} else {
				System.out.println("Nope, the more popular dog name is " + popularDog);
				System.out.println("Do you want to play again? (Y/N)");
			}
			switch (in.next().toUpperCase()) {
				case "Y": stillPlaying = true;
				break;
				case "N": stillPlaying = false;
				System.out.println("You guessed correctly " + correctGuesses + " times out of " + totalGuesses + " times.");
				break;
			}
		}
	}
}