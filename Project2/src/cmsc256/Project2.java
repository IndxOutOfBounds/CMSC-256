package cmsc256;  // do not remove or comment out this statement

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *  CMSC 256
 *  Project 2
 *  Ruprai, Rattandeep
 */
// place any import statements here
public class Project2 {
    public static void main(String[] args) {
        // Test your program thoroughly before submitting.
        // For example,
        // Display appropriately labeled information for the following:
        // What is tallest height?
        // Which row has the lowest weight?
        // Calculate average height of 20-30 year age range in the data.
    }

    /**
     * Gets the file name from command line argument;
     * If parameter is empty, call promptForFileName() method
     *
     * @param argv String array from command line argument
     * @return the name of the data file
     */
    public String checkArgs(String[] argv) {
        //initializes the String that will be returned
        String fileName;
        //if the argument array contains anything filename will be set equal to it, otherwise it will prompt for file name
        if (argv.length > 0) {
            fileName = argv[0];
        } else {
            fileName = promptForFileName();
        }
        //returns the file name
        return fileName;
    }

    /**
     * Prompt user to enter a file name
     *
     * @return user entered file name
     */
    public String promptForFileName() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter file name");
        return in.next();
    }

    /**
     * Retrieve file with the given file name.
     * Prompts user if file cannot be opened or does not exist.
     *
     * @param fileName The name of the data file
     * @return File object
     * @throws java.io.FileNotFoundException
     */
    public File getFile(String fileName) throws FileNotFoundException {
        //Sets user file input equal to a File object
        File checkFile = new File(fileName);
        //if user file does not exist, tells user it does not exist
        if (!checkFile.exists()) {
            System.out.println("File cannot be opened or does not exist");
        }
        //returns the file object that was created
        return checkFile;
    }

    /**
     * Reads the comma delimited file to extract the number data elements
     * provided in the second argument.
     *
     * @param file       The File object
     * @param numRecords The number of values to read from the input file
     * @return 2D array of data from the File
     * @throws IOException if any lines are missing data
     */
    public String[][] readFile(File file, int numRecords) throws IOException {
        //creates new 2d String array that has 3 rows and numRecords columns
        String[][] endArray = new String[numRecords][3];
        //Scanner object that takes in the file object
        Scanner in = new Scanner(file);
        /* loop that will run from 0 to numRecords and split each line into a String array where the values are put
         into the 2d array with a second for loop if there is no missing data
         */
        int rowNum = 0;
        for (int i = 0; i < numRecords; i++) {
            try {
                String temp = in.nextLine();
                String[] row = temp.split(",");
                if (row.length < 3) {
                    throw new IOException("Line " + (i + 1) + " is missing data");
                }
                int intTest = Integer.parseInt(row[0]);
                for (int j = 0; j < row.length; j++) {
                    endArray[rowNum][j] = String.valueOf(Integer.parseInt(row[j]));
                }
                rowNum++;
            } catch (NumberFormatException e) {
                i++;
                numRecords = numRecords + 2;
            }
        }
        //return the 2d array
        return endArray;
    }

    /**
     * Determines the tallest height in the data set
     * Height is the second field in each row
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Maximum height value
     */
    public int findTallest(String[][] db) {
        //int variable that will return at the end
        int tallGuy = 0;
        /* for loop that will take the String input and check for int, if the value is not an int, it will check the next row,
         * if it is an int, it will compare it to the tallest height so far, and store it in "tallGuy" if it is bigger
         */

        for (int i = 0; i < db.length; i++) {
            try {
                if ((Integer.parseInt(db[i][1])) > tallGuy) {
                    tallGuy = Integer.parseInt(db[i][1]);
                }
            } catch (NumberFormatException e) {
                i++;
            }
        }
        //returns the biggest height
        return tallGuy;
    }

    /**
     * Returns the values in the record that have the lowest weight
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Smallest weight value
     */
    public String[] findLightestRecord(String[][] db) {
        //stores the lowest weight as well as the index that weight is at
        int lowestWeightIndex = 0;
        int lowestWeight = 999999999;
        /* for loop will iterate through the 2d array and will store values lower than "lowestWeight" into that variable
         * and store the index that value is at into "lowestWeightIndex"
         */
        for (int i = 0; i < db.length; i++) {
            try {
                if (Integer.parseInt(db[i][2]) < lowestWeight) {
                    lowestWeight = Integer.parseInt(db[i][2]);
                    lowestWeightIndex = i;
                }
            } catch (NumberFormatException e) {
                i++;
            }
        }
        //returns the array @ lowestWeightIndex
        return db[lowestWeightIndex];
    }

    /**
     * Calculates the average height for all records with the given age range.
     *
     * @param db         2D array of data containing [age] [height] [weight]
     * @param lowerBound youngest age to include in the average
     * @param upperBound oldest age to include in the average
     * @return The average height for the given range or 0 if no
     * records match the filter criteria
     */
    public double findAvgHeightByAgeRange(String[][] db, int lowerBound, int
            upperBound) {
        //data in range will be the total number of rows that are in the range, totalHeight will add all the heights in those rows
        double dataInRange = 0;
        double totalHeight = 0;
        //iterates through the rows to find ages that fall in the range and adds to the relevant variables if one is found
        for (int i = 0; i < db.length; i++) {
            try {
                if (lowerBound <= Integer.parseInt(db[i][0]) && Integer.parseInt(db[i][0]) <= upperBound) {
                    totalHeight += Integer.parseInt(db[i][1]);
                    dataInRange++;
                }
            } catch (NumberFormatException e) {
                    continue;
            }
        }
        //return the average by dividing total by number of people unless dataInRange is 0
        if (dataInRange == 0) {
            return 0;
        }
        return (totalHeight/dataInRange);
    }
}
