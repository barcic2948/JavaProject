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
    private int dimX;
    private int dimY;
    private Double[][] mat;
    public int getDimX() {
        return dimX;
    }
    public int getDimY() {
        return dimY;
    }
    public Double[][] getMat() {
        return mat;
    }
    public Matrix() {}
    public Matrix(String dimX, String dimY) throws MatrixDimensionException {
        try {
            this.dimX = Integer.parseInt(dimX);
        } catch (NumberFormatException e) {
            throw new MatrixDimensionException("X", dimX);
        }
        try {
            this.dimY = Integer.parseInt(dimY);
        } catch (NumberFormatException e) {
            throw  new MatrixDimensionException("Y", dimY);
        }
        this.mat = new Double[this.dimY][this.dimX];
    }
    public Matrix(int dimX, int dimY) {
        this.dimX = dimX;
        this.dimY = dimY;
        this.mat = new Double[this.dimY][this.dimX];
    }
    public Matrix(Double[][] mat) {
        this.dimX = mat[0].length;
        this.dimY = mat.length;
        this.mat = new Double[this.dimY][this.dimX];
        System.arraycopy(mat, 0, this.mat, 0, this.dimY);
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