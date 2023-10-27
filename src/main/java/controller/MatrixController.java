package controller;

import model.*;
import view.MatrixIOService;

import javax.naming.BinaryRefAddr;

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

    private void fillMatrix() {

        matrixIOService.printMatrixFillSchema(matrix.getNumberOfColumns(), matrix.getNumberOfRows());

        for(int i = 0; i < matrix.getNumberOfRows(); i++) {
            for(int j = 0; j < matrix.getNumberOfColumns(); j++) {
                while (true) {
                    try {
                        matrix.setMatrixValueAtPos(j, i, matrixIOService.setNumber(j, i));
                        break;
                    } catch (MatrixParseException e) {
                        matrixIOService.printExceptionMessage(e);
                    }
                }
            }
        }
        matrixIOService.printMessage("\n============================\n");
        matrixIOService.printMatrix(this.matrix);
        matrixIOService.printMessage("\n============================\n");
    }


    /**
     *
     *
     * @param args
     */
    public void run(String[] args) {

        MatrixOperationTypeEnum matrixOperationTypeEnum;
        String input;
        String numberOfRows;
        String numberOfColumns;

        if (args.length == 3) {
            input = args[0];
            numberOfRows = args[1];
            numberOfColumns = args[2];
        } else {
            matrixIOService.printHelp();
            matrixIOService.printMessage("You may select your command now or close the program with \"--escape\"");
            input = matrixIOService.getValueWithMessage("If you wish to select the operation, please enter it now: ");
            numberOfRows = "";
            numberOfColumns = "";
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

        if (MatrixOperationTypeEnum.ESCAPE.equals(matrixOperationTypeEnum)) return;

        while (true) {
            try {
                matrix = new Matrix(numberOfColumns, numberOfRows);
                break;
            } catch (MatrixDimensionException e) {
                if(MatrixDimensionEnum.COLUMNS.equals(e.getEnum())) {
                    numberOfColumns = matrixIOService.getValueWithMessage(
                            "Value for the number of COLUMNS is wrong or missing, please correct it: ");
                } else {
                    numberOfRows = matrixIOService.getValueWithMessage(
                            "Value for the number of ROWS is wrong or missing, please correct it: ");
                }
            }
        }

        fillMatrix();

        matrixService = new MatrixService();

        switch (matrixOperationTypeEnum) {
            case TRANSPOSE:
                matrixIOService.printMatrix(matrixService.transposeMatrix(matrix));
                break;
            case DETERMINANT:
                matrixIOService.printMessage("Result: " + matrixService.calculateDeterminant(matrix));
                break;
        }

    }

    public void serviceMatrix() {

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
