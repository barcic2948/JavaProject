package controller;

/**
 * Main class from which the program is run. This could be also done from within the MatrixController class
 * but for the sake of readability I have decided to split them
 *
 * @author Bartek
 * @version 1.0
 */
public class CichyBartlomiejex1prototype {
    /**
     * the main function from which the program runs
     *
     * @param args command line arguments
     */

    public static void main(String[] args) {
        System.out.println("Hello World!");
        MatrixController matrixController = new MatrixController();

        matrixController.run(args);
    }
}
