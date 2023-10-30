package controller;

import model.*;
import view.MatrixIOService;

/**
 * Main class of the application responsible for handling data received from IO-service and directing the model.
 * @author Bartek
 * @version 1.3
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

    /**
     * Allows user to fill the matrix with Double values parsed from strings.
     */
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

    /**
     * Method used to select operation. Original version with the use of static enum class methode
     * @deprecated
     * @param input String containing operation name
     * @return MatrixOperationTypeEnum element corresponding to the input
     */
    private MatrixOperationTypeEnum selectOperation(String input) {
        while (true) {
            try {
                return MatrixOperationTypeEnum.getEnum(input);
            } catch (IllegalArgumentException e) {
                matrixIOService.printMessage("There seems to be an error in your command: " + input);
                input = matrixIOService.getValueWithMessage("Please enter your command again: ");
            }
        }
    }

    /**
     * Method used to select operation. Proper version without the use of static methods.
     * @param input String containing the user input operation name (can be incorrect)
     * @return MatrixOperationTypeEnum element corresponding to the input
     */
    private MatrixOperationTypeEnum selectOperation2(String input) {
        while (true) {
            switch (input) {
                case "--determinant":
                    return MatrixOperationTypeEnum.DETERMINANT;
                case "--transpose":
                    return MatrixOperationTypeEnum.TRANSPOSE;
                case "--help":
                    return MatrixOperationTypeEnum.HELP;
                case "--escape":
                    return MatrixOperationTypeEnum.ESCAPE;
                default:
                    matrixIOService.printMessage("There seems to be an error in your command: " + input + ".");
                    input = matrixIOService.getValueWithMessage("Please enter your command again: ");
                    break;
            }
        }
    }

    /**
     * Method that prepares the square matrix (creates new object, sets its dimension)
     * If the input data is incorrect, the program will ask the user to correct it until it is.
     * @param dimension String containing the matrix dimension specified by user input (can be incorrect)
     * @return Matrix class object
     */
    private Matrix prepareSquareMatrix(String dimension) {
        while (true) {
            try {
                return new Matrix(dimension, dimension);
            } catch (MatrixDimensionException e) {
                if (MatrixDimensionEnum.SQUARE.equals(e.getEnum())) {
                    dimension = matrixIOService.getValueWithMessage(
                            "Value for the square matrix dimension is wrong or missing, please correct it: ");
                } else if (MatrixDimensionEnum.SIZE_COLUMNS.equals(e.getEnum())) {
                    dimension = matrixIOService.getValueWithMessage(
                            "The maximum size of the matrix is 5 by 5, please correct the dimension: ");
                }
            }
        }
    }

    /**
     * Method that prepares the matrix (creates new object, sets its dimensions).
     * If the input data is incorrect, the program will ask the user to correct it until it is.
     * @param numberOfColumns String containing the number of columns specified by user input (can be incorrect)
     * @param numberOfRows String containing the number of rows specified by user input (can be incorrect)
     * @return Matrix class object
     */
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
     * The main controller method, used to coordinate View and Model classes and their methods.
     * @param args Command line arguments
     */
    public void run(String[] args) {

        boolean cond = false;
        String input = "", numberOfRows = "", numberOfColumns = "";

        if (args.length == 1) {
            input = args[0];
        } else if (args.length == 2) {
            input = args[0];
            numberOfRows = args[1];
            numberOfColumns = "";
            matrixIOService.printMessage("Use --help to display instruction or --escape to exit the program");
        } else if (args.length == 3) {
            input = args[0];
            numberOfRows = args[1];
            numberOfColumns = args[2];
            matrixIOService.printMessage("Use --help to display instruction or --escape to exit the program");
        } else {
            matrixIOService.printHelp();
            cond = true;
        }

        matrixService = new MatrixService();

        while (true) {
            if (cond) {
                input = matrixIOService.getValueWithMessage("If you wish to select the operation, please enter it now: ");
            } else {
                matrixIOService.printReadArgs(args);
            }
            switch (selectOperation2(input)) { //Here is where the command can be exchanged with the static enum one.
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
                    cond = true;
                    break;
            }
        }
    }
}