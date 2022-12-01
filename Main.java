// Programmer: Kevin Kim
// Instructor: Professor Raheja
// Class: CS 3010-03
// Date: 10/23/2022
// Project: Newton's, Lagrange, and Simplified Form

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static void main(String args[]) throws IOException, InputMismatchException
    {
        String fileName;
        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nPlease enter the name of a text file which has the x and f(x) values: ");
        fileName = keyboard.nextLine();

        // check to see if this text file exists.
        File myFile = new File(fileName);

        if (myFile.exists())
        {
            // if so, read the data from the file because the user
            // entered a valid name for the text file.
            Interpolation interpolatingPolynomial = new Interpolation(fileName);

            // then, create a divided difference table.
            interpolatingPolynomial.createTable();
            interpolatingPolynomial.divideDifference();
            interpolatingPolynomial.print();

            // lastly, use the divided difference table that was created to print the polynomial
            // in three different forms: Newton's, Lagrange, and Simplified Form.
            interpolatingPolynomial.interpolation();
            interpolatingPolynomial.simplifiedPolynomial();
        }
        else
        {
            // otherwise, notify the user that the name of the text file is invalid
            // before terminating the program.
            System.out.println("\nERROR: The file does not exist... Terminating the program...");
        }

        // terminate the program.
        System.out.println("\nThank you for using this program... Good bye!");

    } // end of main.
}