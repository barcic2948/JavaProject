package model;

import model.enums.MatrixDimensionEnum;
import model.exceptions.MatrixDimensionException;
import model.exceptions.MatrixParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class that contains all the information about the matrix dimensions and the data
 *
 * @author Bartek
 * @version 1.2
 */
public class Matrix {
    /**
     * The first dimension of the matrix
     */
    private final int numberOfColumns;

    /**
     * The second dimension of the matrix
     */
    private final int numberOfRows;

    /**
     * The two-dimensional array containing the data of the matrix
     */
    private List<List<Double>> mat;

    /**
     * Method for filling the matrix with null values. Necessary for set and get functions of the collection.
     */
    private void prepareMatrix() {
        this.mat = new ArrayList<>(this.numberOfRows);

        for (int i = 0; i < this.numberOfRows; i++) {
            mat.add(new ArrayList<>(this.numberOfColumns));
            for (int j = 0; j < this.numberOfColumns; j++) {
                mat.get(i).add(null);
            }
        }
    }

    /**
     * Getter for the first dimension
     *
     * @return Integer numberOfColumns
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * Getter for the second dimension
     *
     * @return Integer numberOfColumns
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Getter for the matrix two-dimensional array
     *
     * @return Two-dimensional array
     */
    public List<List<Double>> getMat() {
        return mat;
    }

    /**
     * Matrix custom constructor
     *
     * @param numberOfColumns Non-negative integer, not greater than 5
     * @param numberOfRows    Non-negative integer, not greater than 5
     * @throws MatrixDimensionException Thrown if input parameters are absent or not parsable
     */
    public Matrix(String numberOfColumns, String numberOfRows) throws MatrixDimensionException {

        try {
            this.numberOfColumns = Integer.parseInt(numberOfColumns);
        } catch (NumberFormatException e) {
            throw new MatrixDimensionException(MatrixDimensionEnum.COLUMNS);
        }

        try {
            this.numberOfRows = Integer.parseInt(numberOfRows);
        } catch (NumberFormatException e) {
            throw new MatrixDimensionException(MatrixDimensionEnum.ROWS);
        }

        if (this.numberOfColumns > 5 || this.numberOfColumns < 1) {
            throw new MatrixDimensionException(MatrixDimensionEnum.SIZE_COLUMNS);
        } else if (this.numberOfRows > 5 || this.numberOfRows < 1) {
            throw new MatrixDimensionException(MatrixDimensionEnum.SIZE_ROWS);
        }
        prepareMatrix();
    }

    /**
     * Matrix custom constructor for Integer values
     * Class designed for MatrixService and should only be used there
     * Should only be used with correct values
     *
     * @param numberOfColumns Integer value
     * @param numberOfRows    Integer value
     */
    public Matrix(int numberOfColumns, int numberOfRows) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        prepareMatrix();
    }

    /**
     * Matrix custom constructor for already created two-dimensional array with specified size
     * Class designed for CalculationTest package and should only be used there
     * Should only be used with correct values
     *
     * @param mat Two-dimensional array
     */
    public Matrix(Double[][] mat) {
        this.numberOfColumns = mat[0].length;
        this.numberOfRows = mat.length;
        this.mat = new ArrayList<>();
        for (Double[] arr : mat) {
            this.mat.add(Arrays.asList(arr));
        }
    }

    /**
     * Matrix custom constructor for copying already existing list with specified size
     * Should only be used with correct values
     *
     * @param mat Two-dimensional array
     */
    private Matrix(List<List<Double>> mat) {
        this.numberOfColumns = mat.get(0).size();
        this.numberOfRows = mat.size();
        this.mat = new ArrayList<>(mat);
    }

    /**
     * Methode used to set a value at a selected position in a matrix
     *
     * @param x     Position along the columns
     * @param y     Position along the rows
     * @param value A floating point value represented as a string
     * @throws MatrixParseException Thrown if the value is not parsable or empty
     */
    public void setMatrixValueAtPos(int x, int y, String value) throws MatrixParseException {
        try {
            this.mat.get(y).set(x, Double.valueOf(value));
        } catch (NumberFormatException e) {
            throw new MatrixParseException(x, y, value);
        }
    }

    /**
     * Methode used to set a value at a selected position in the matrix
     * Class designed for MatrixService and should only be used there
     * Should only be used with correct values
     *
     * @param x     Position along the columns
     * @param y     Positions along the rows
     * @param value Value to set
     */
    public void setMatrixValueAtPos(int x, int y, Double value) {
        this.mat.get(y).set(x, value);
    }

    /**
     * Methode used to obtain the value at a selected position from the matrix
     * Class designed for MatrixService and should only be used there
     * Should only be used with correct values
     *
     * @param x Position along the columns
     * @param y Position along the rows
     * @return Value at the position
     */
    public Double getMatrixValueAtPos(int x, int y) {
        return this.mat.get(y).get(x);
    }
}