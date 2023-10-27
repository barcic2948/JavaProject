package model;

/**
 *
 * @author Bartek
 */
public class MatrixDimensionException extends Exception{

    private String value;

    public MatrixDimensionException(String dimension, String value) {
        super("Matrix dimension is not parseable");
        this.value = value;
    }
    public String getDimension() {
        return value;
    }
}