package model.exceptions;

import model.enums.MatrixDimensionEnum;

/**
 * Dimension exception thrown when the user input for the dimension is invalid
 *
 * @author Bartek
 * @version 1.2
 */
public class MatrixDimensionException extends Exception {

    /**
     * Enum containing possible exception reasons
     */
    private final MatrixDimensionEnum dimensionEnum;

    /**
     * Exception constructor
     *
     * @param dimensionEnum MatrixDimension enum element to specify the type of exception
     */
    public MatrixDimensionException(MatrixDimensionEnum dimensionEnum) {
        super("Matrix dimension is not parsable");
        this.dimensionEnum = dimensionEnum;
    }

    /**
     * Getter for the dimensionEnum
     *
     * @return MatrixDimensionEnum element
     */
    public MatrixDimensionEnum getEnum() {
        return this.dimensionEnum;
    }
}