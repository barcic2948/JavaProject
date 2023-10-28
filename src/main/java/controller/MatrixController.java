package controller;

import model.*;
import view.MatrixIOService;

import java.util.PrimitiveIterator;

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
    private final MatrixIOService matrixIOService;

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

    private void fillMatrix() {

        matrixIOService.printMatrixFillSchema(matrix.getNumberOfColumns(), matrix.getNumberOfRows());

        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColumns(); j++) {
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
        matrixIOService.printMatrix(this.matrix);
    }

    private MatrixOperationTypeEnum selectOperation(String input) {
        while (true) {
            try {
                return MatrixOperationTypeEnum.getEnum(input);
            } catch (IllegalArgumentException e) {
                matrixIOService.printMessage("There seems to be an error in your command " + input + ".");
                input = matrixIOService.getValueWithMessage("Please enter your command again: ");
            }
        }
    }

    private Matrix prepareSquareMatrix(String dimension) {
        while (true) {
            try {
                return new Matrix(dimension, dimension);
            } catch (MatrixDimensionException e) {
                if (MatrixDimensionEnum.SQUARE.equals(e.getEnum())) {
                    dimension = matrixIOService.getValueWithMessage("Value for the square matrix dimension is wrong or missing, please correct it: ");
                } else if (MatrixDimensionEnum.SIZE_COLUMNS.equals(e.getEnum())) {
                    dimension = matrixIOService.getValueWithMessage("The maximum size of the matrix is 5 by 5, please correct the dimension: ");
                }
            }
        }
    }

    private Matrix prepareMatrix(String numberOfColumns, String numberOfRows) {
        while (true) {
            try {
                return new Matrix(numberOfColumns, numberOfRows);
            } catch (MatrixDimensionException e) {
                if (MatrixDimensionEnum.COLUMNS.equals(e.getEnum())) {
                    numberOfColumns = matrixIOService.getValueWithMessage(
                            "Value for the number of COLUMNS is wrong, please correct it: ");
                } else if (MatrixDimensionEnum.ROWS.equals(e.getEnum())) {
                    numberOfRows = matrixIOService.getValueWithMessage(
                            "Value for the number of ROWS is wrong, please correct it: ");
                } else if (MatrixDimensionEnum.SIZE_COLUMNS.equals(e.getEnum())) {
                    numberOfColumns = matrixIOService.getValueWithMessage(
                            "Value for the number of COLUMNS is is to high (maximum matrix size is 5 by 5), please correct it: ");
                } else if (MatrixDimensionEnum.SIZE_ROWS.equals(e.getEnum())) {
                    numberOfRows = matrixIOService.getValueWithMessage(
                            "Value for the number of ROWS is to high (maximum matrix size is 5 by 5), please correct it: ");
                }
            }
        }
    }

    /**
     * @param args
     */
    public void run(String[] args) {

        boolean cond;
        String input = "", numberOfRows = "", numberOfColumns = "";

        if (args.length == 2) {
            input = args[0];
            numberOfRows = args[1];
            numberOfColumns = "";
            cond = false;
        } else if (args.length == 3) {
            input = args[0];
            numberOfRows = args[1];
            numberOfColumns = args[2];
            cond = false;
        } else {
            matrixIOService.printHelp();
            cond = true;
        }

        matrixService = new MatrixService();

        while (true) {
            if (cond) {
                input = matrixIOService.getValueWithMessage("If you wish to select the operation, please enter it now: ");
            }
            switch (selectOperation(input)) {
                case ESCAPE:
                    return;
                case DETERMINANT:
                    if (cond) {
                        numberOfRows = matrixIOService.getValueWithMessage("Set the dimension of the matrix => ");
                    }
                    this.matrix = prepareSquareMatrix(numberOfRows);
                    fillMatrix();
                    cond = true;
                    matrixIOService.printMessage("Matrix determinant result: " + matrixService.calculateDeterminant(this.matrix));
                    break;
                case TRANSPOSE:
                    if (cond) {
                        numberOfRows = matrixIOService.getValueWithMessage("Set the number of ROWS => ");
                        numberOfColumns = matrixIOService.getValueWithMessage("Set the number of COLUMNS => ");
                    }
                    this.matrix = prepareMatrix(numberOfColumns, numberOfRows);
                    fillMatrix();
                    cond = true;
                    matrixIOService.printMessage("Result of the Transposition:");
                    matrixIOService.printMatrix(matrixService.transposeMatrix(matrix));
                    break;
                case HELP:
                    matrixIOService.printHelp();
                    break;
            }
        }
    }
}