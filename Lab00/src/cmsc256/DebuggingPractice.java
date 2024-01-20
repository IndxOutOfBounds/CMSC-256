package cmsc256;
/*
 * This program is used to show how to find errors in your program
 * Programmer 1: Rattandeep Ruprai
 * Programmer 2:
 * CMSC 256 Section: 901
 * Lines with syntax errors: 25, 26, 28, 64
 * Runtime errors: 27, 31
 * ArithmeticException, line attempts to divide by 0
 * Line 31 is calling an index that is not in the scope of the array, array size is 7 so the highest value is 6
 */
import java.util.*;

public class DebuggingPractice {
    public static int getEvenPerfectSquareNumbers() {
        return evenPerfectSquareNumbers;
    }

    private static int evenPerfectSquareNumbers = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //declare variables
        double number; //expected type is double not int
        double average, value;
        String[] daysOfTheWeek = {"Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"};
        int[] nums = {23, 3, 14, -5, 44, 78, 6, 98, 25};
        String myName = "Rattandeep Ruprai";
        value = 3.75; //added a semicolon to the end of the line
        number = value;
        //average = (3 + 5 + 8) / 0;
        System.out.println(" *** I am " + myName + " *** "); //added closing parenthesis and semicolon to end of line
                System.out.println("The first day of the week is: "+ daysOfTheWeek[0]);
        System.out.println("The last day of the week is: "+ daysOfTheWeek[6]);
        average = (3 + 5 + 8)/ 3.0;
        System.out.println("expected average is 5.33, actual average is: " + average);
        System.out.println("expected max is 98, actual max is: " + max(nums));
        System.out.println("expected type of number is double: " + number);
        System.out.println("calling countPerfectSquares(100)");
        countPerfectSquares(100);
    }

    // Returns the maximum value in the array parameter
    public static int max(int[] a) {
        int result = a[0];
        for(int i = 0; i < a.length; i++){
            if(a[i] > result) {
                result = a[i];
            }
        }
        return result;
    }

    // Returns true if all values in the parameter array fall with the
    // range define by parameters low and high
    public static boolean inRange (int[] array , int low , int high ) {
        for (int i = 0 ; i < array.length ; i++) {
            if (array[i] < low || array[i] > high) {
                return false;
            }
        }
        return true ;
    }

    // Displays the counts the total perfect squares and
    // //even perfect squares for a given number
    public static void countPerfectSquares(int num) {
        //int num = 100; (line not needed)
        System.out.println("Total Perfect Squares: " + calculateCount(num));
        System.out.println("Even Perfect Squares : " + evenPerfectSquareNumbers);
    }

    public static int calculateCount(int i) {
        int perfectSquaresCount = 0;
        for (int number = 1; number <= i; number++) {
            if (isPerfectSquare(number)) {
                perfectSquaresCount++;
                if (number % 2 == 0) {
                    evenPerfectSquareNumbers++;
                }
            }
        }
        return perfectSquaresCount;
    }

    private static boolean isPerfectSquare(int number) {
        double sqrt = Math.sqrt(number);
        return sqrt - Math.floor(sqrt) == 0;
    }
}
