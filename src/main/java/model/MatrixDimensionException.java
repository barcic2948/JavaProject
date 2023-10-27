package model;

/**
 *
 * @author Bartek
 */
public class MatrixDimensionException extends Exception{

    private String value;
    private MatrixDimensionEnum dimensionEnum;

    public MatrixDimensionException(MatrixDimensionEnum dimensionEnum, String value) {
        super("Matrix dimension is not parseable");
        this.value = value;
        this.dimensionEnum = dimensionEnum;
    }
    public String getDimension() {
        return value;
    }

    public MatrixDimensionEnum getEnum() {
        return this.dimensionEnum;
    }
}