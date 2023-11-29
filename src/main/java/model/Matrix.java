package model;

import model.enums.MatrixDimensionEnum;
import model.exceptions.MatrixDimensionException;
import model.exceptions.MatrixParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a matrix with dimensions and data.
 * <p>
 * Contains methods to access and manipulate matrix elements.
 *
 * @author Bartek
 * @version 2.0
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
     * Fills the matrix with null values, necessary for set and get functions of the collection.
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
     * Gets the number of columns in the matrix.
     *
     * @return The number of columns.
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * Gets the number of rows in the matrix.
     *
     * @return The number of rows.
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Gets the two-dimensional list representing the matrix.
     *
     * @return The matrix as a two-dimensional list.
     */
    public List<List<Double>> getMat() {
        return mat;
    }

    /**
     * Constructs a matrix with specified dimensions.
     *
     * @param numberOfColumns The number of columns (not greater than 5, non-negative integer).
     * @param numberOfRows    The number of rows (not greater than 5, non-negative integer).
     * @throws MatrixDimensionException Thrown if input parameters are absent or not parsable.
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
     * Constructs a matrix with specified dimensions (for Integer values).
     *
     * @param numberOfColumns The number of columns (integer value).
     * @param numberOfRows    The number of rows (integer value).
     */
    public Matrix(int numberOfColumns, int numberOfRows) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        prepareMatrix();
    }

    /**
     * Constructs a matrix from an already created two-dimensional array with specified size.
     *
     * @param mat The two-dimensional array to construct the matrix from.
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
     * Constructs a matrix from given dimensions and elements.
     *
     * @param numberOfRows    Number of rows.
     * @param numberOfColumns Number of columns.
     * @param elements        List of string elements to populate the matrix.
     * @throws MatrixParseException Thrown if elements cannot be parsed as Double values.
     */
    public Matrix(int numberOfRows, int numberOfColumns, List<String> elements) throws MatrixParseException {


        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;

        prepareMatrix();

        for (int y = 0; y < this.numberOfRows; y++) {
            for (int x = 0; x < this.numberOfColumns; x++) {
                try {
                    this.mat.get(y).set(x, Double.valueOf(elements.get(y * this.numberOfColumns + x)));
                } catch (NumberFormatException e) {
                    throw new MatrixParseException(x, y, elements.get(y * this.numberOfColumns + x));
                }
            }
        }

    }

    /**
     * Sets a value at a specified position in the matrix.
     *
     * @param x     Column position.
     * @param y     Row position.
     * @param value A floating point value represented as a string.
     * @throws MatrixParseException Thrown if the value is not parsable or empty.
     */
    public void setMatrixValueAtPos(int x, int y, String value) throws MatrixParseException {
        try {
            this.mat.get(y).set(x, Double.valueOf(value));
        } catch (NumberFormatException e) {
            throw new MatrixParseException(x, y, value);
        }
    }

    /**
     * Sets a value at a specified position in the matrix.
     *
     * @param x     Column position.
     * @param y     Row position.
     * @param value Value to set.
     */
    public void setMatrixValueAtPos(int x, int y, Double value) {
        this.mat.get(y).set(x, value);
    }

    /**
     * Gets a value from a specified position in the matrix.
     *
     * @param x Column position.
     * @param y Row position.
     * @return Value at the position.
     */
    public Double getMatrixValueAtPos(int x, int y) {
        return this.mat.get(y).get(x);
    }
}