/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Bartek
 */
public class Matrix {
    private int numberOfColumns;
    private int numberOfRows;
    private Double[][] mat;
    public int getNumberOfColumns() {
        return numberOfColumns;
    }
    public int getNumberOfRows() {
        return numberOfRows;
    }
    public Double[][] getMat() {
        return mat;
    }
    public Matrix() {}
    public Matrix(String numberOfColumns, String numberOfRows) throws MatrixDimensionException {
        try {
            this.numberOfColumns = Integer.parseInt(numberOfColumns);
        } catch (NumberFormatException e) {
            throw new MatrixDimensionException(MatrixDimensionEnum.COLUMNS, numberOfColumns);
        }
        try {
            this.numberOfRows = Integer.parseInt(numberOfRows);
        } catch (NumberFormatException e) {
            throw  new MatrixDimensionException(MatrixDimensionEnum.ROWS, numberOfRows);
        }
        this.mat = new Double[this.numberOfRows][this.numberOfColumns];
    }
    public Matrix(int numberOfColumns, int numberOfRows) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.mat = new Double[this.numberOfRows][this.numberOfColumns];
    }
    public Matrix(Double[][] mat) {
        this.numberOfColumns = mat[0].length;
        this.numberOfRows = mat.length;
        this.mat = new Double[this.numberOfRows][this.numberOfColumns];
        System.arraycopy(mat, 0, this.mat, 0, this.numberOfRows);
    }
    public void setMatrixValueAtPos(int x, int y, String value) throws MatrixParseException {
        try {
            this.mat[y][x] = Double.valueOf(value);
        } catch (NumberFormatException e) {
            throw new MatrixParseException(x, y, value);
        }
    }
    public void setMatrixValueAtPos(int x, int y, Double value) {
        this.mat[y][x] = value;
    }
    public Double getMatrixValueAtPos(int x, int y) {
        return this.mat[y][x];
    }
}