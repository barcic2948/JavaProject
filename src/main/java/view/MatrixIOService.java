/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.Matrix;

import javax.swing.plaf.PanelUI;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Bartek
 */
public class MatrixIOService {

    private Scanner scanner;

    public MatrixIOService() {
        scanner = new Scanner(System.in);
    }

    public String setNumber(int posX, int posY) {
        System.out.print("Set number for matrix position: [" + posX + ", " + posY + "] => ");
        return scanner.nextLine();
    }

    public String getValueWithMessage(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printDeterminant(Double determinant) {
        System.out.println("Your result is: " + determinant);
    }

    public void printMatrix(Matrix matrix) {
        for (Double[] mat : matrix.getMat()) {
            System.out.println(Arrays.toString(mat));
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMatrixFillSchema(int x, int y) {

        System.out.println('\n');

        String[][] tab = new String[y][x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                tab[i][j] = new String("["+ j + "," + i + "]");
            }
        }

        for (String[] s : tab) {
            System.out.println(Arrays.toString(s));
        }
        System.out.println('\n');
    }

    public void printHelp() {
        System.out.println(
                """
                        ||=======================================================================================================||
                        ||  The program could not identify the initial command line arguments.                                   ||
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
