package model;

/**
 * @author Bartek
 */
public class MatrixDimensionException extends Exception {

    private MatrixDimensionEnum dimensionEnum;

    public MatrixDimensionException(MatrixDimensionEnum dimensionEnum) {
        super("Matrix dimension is not parseable");
        this.dimensionEnum = dimensionEnum;
    }

    public MatrixDimensionEnum getEnum() {
        return this.dimensionEnum;
    }
}