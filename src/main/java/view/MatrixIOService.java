package view;

import model.Matrix;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Matrix Input Output Service class
 *
 * @author Bartek
 * @version 2.0
 */
public class MatrixIOService {

    /**
     * Scanner object used to obtain user input
     */
    private Scanner scanner;

    /**
     * Class constructor
     */
    public MatrixIOService() {
        scanner = new Scanner(System.in);
    }

    /**
     * Methode responsible for obtaining user input for the selected coordinate
     *
     * @param posX Coordinate for the columns (x-axis)
     * @param posY Coordinate for the rows (y-axis)
     * @return String user input
     */
    public String setNumber(int posX, int posY) {
        System.out.print("Set number for matrix position: [" + posX + ", " + posY + "] => ");
        return scanner.nextLine();
    }

    /**
     * Methode used to receive user input, after presenting them with a message inline
     *
     * @param message Massage that is printed out before allowing input
     * @return String user input
     */
    public String getValueWithMessage(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Methode used to print exception message
     *
     * @param e Exception
     */
    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Methode used to print a 2dimensional array from the Matrix class
     *
     * @param matrix Matrix object to print
     */
    public void printMatrix(Matrix matrix) {
        System.out.print('\n');
        matrix.getMat().forEach(System.out::println);
        System.out.print('\n');
    }

    /**
     * Simple methode for printing message
     *
     * @param message String message to "print"
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Methode used to show the user how the matrix will be filled in the latter steps
     * Prints a mock matrix with coordinates(x, y) for each field
     *
     * @param numberOfColumns Number of columns in the matrix
     * @param numberOfRows    Number of rows in the matrix
     */
    public void printMatrixFillSchema(int numberOfColumns, int numberOfRows) {

        System.out.println("\n This is the corresponding matrix with the coordinates of each element:\n");

        String[][] tab = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                tab[i][j] = new String("[" + j + "," + i + "]");
            }
        }

        for (String[] s : tab) {
            System.out.println(Arrays.toString(s));
        }
    }

    /**
     * Methode used to print the input parameters from the command line
     *
     * @param args Input arguments from the command line
     */
    public void printReadArgs(String[] args) {

        if (args.length == 1) {
            System.out.println("Selected operation: " + args[0]);
        } else if (args.length == 2) {
            System.out.println("Selected operation: " + args[0] +
                    "\nSelected matrix dimension: " + args[1]);
        } else {
            System.out.println("Selected operation: " + args[0] +
                    "\nSelected number of COLUMNS: " + args[1] +
                    "\nSelected number of ROWS:" + args[2]);
        }
    }

    /**
     * Methode used to print the "help page"
     */
    public void printHelp() {
        System.out.println(
                """
                        ||=======================================================================================================||
                        ||  This is a help page for the application. The data when running the program from the command line     ||
                        ||  (command line arguments) is read as follows:                                                         ||
                        ||                                                                                                       ||
                        ||  java Main.java operation number_of_rows number_of_columns                                            ||
                        ||                                                                                                       ||
                        ||  Number_of_rows and number_of_columns should be positive integer values                               ||
                        ||  The operation either one of "--determinant" or "--transpose"                                         ||
                        ||                                                                                                       ||
                        ||  Examples of the commands:                                                                            ||
                        ||  java Main.java --transpose 3 5                                                                       ||
                        ||  java Main.java --determinant 4 4                                                                     ||
                        ||                                                                                                       ||
                        ||  If you wish to close this program enter "--escape" into the command line                             ||
                        ||  If you wish to open help again please enter "--help" into the command line                           ||
                        ||=======================================================================================================||
                        """
        );
    }
}
