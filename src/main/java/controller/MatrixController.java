package controller;

import model.Matrix;
import view.MatrixIOService;
import model.MatrixService;

/**
 * Main class of the application realizing the operation of praising data and calculating matrix determinant.
 *
 * @author Bartek
 * @version 1.1
 */
public class MatrixController {

    /**
     * Matrix service object
     */
    private MatrixIOService matrixIOService;

    /**
     * Matrix service object
     */
    private MatrixService matrixService;
    /**
     * Object of the matrix class used to parse and store the input data.
     */
    private Matrix matrix;

    /**
     * Constructor for the class, to initialise the Input/Output service used in both methods.
     */
    public MatrixController() {
        matrixIOService = new MatrixIOService();
    }

    /**
     * This method is used to provide data and allow for the creation of the Matrix object.
     * It sets the dimension and the two-dimensional array of the matrix object.
     *
     * @param args Input list of strings that should be directly provided form the command line args.
     */
    public void run2(String[] args) {

    }

    /**
     *
     * @param args
     */
    public void run(String[] args) {

        MatrixOperationTypeEnum matrixOperationTypeEnum;
        String input;

        if (args.length == 3) input = args[0];
        else {
            matrixIOService.printHelp();
            matrixIOService.printMessage("You may select your command now or close the program with \"--escape\"");
            input = matrixIOService.getValueWithMessage("If you wish to select the operation, please enter it now: ");
        }

        while (true) {
            try {
                matrixOperationTypeEnum = MatrixOperationTypeEnum.getEnum(input);
                break;
            } catch (IllegalArgumentException e) {
                matrixIOService.printMessage("There seems to be an error in your command " + input + ".");
                input = matrixIOService.getValueWithMessage("Please enter your command again: ");
            }
        }

        //if (MatrixOperationTypeEnum.ESCAPE.equals(matrixOperationTypeEnum)) return;

        switch (matrixOperationTypeEnum) {
            case ESCAPE:
                System.out.println("Escape");
                return;
            case HELP:
                System.out.println("Help");
                break;
            case TRANSPOSE:
                System.out.println("Transpose");
                break;
            case DETERMINANT:
                System.out.println("Determinanat");
                break;

        }


        System.out.println("Should escape");


    }

    private void help() {
        matrixIOService.printHelp();
    }

    /**
     *
     */
    public void calculateDeterminant() {

        matrixIOService.printMatrix(this.matrix);

        matrixService = new MatrixService();

        matrixIOService.printDeterminant(matrixService.calculateDeterminant(this.matrix));
    }

}
