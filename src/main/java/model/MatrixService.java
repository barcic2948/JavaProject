package model;

import java.util.Objects;

/**
 * Class responsible for handling all mathematical operations on the Matrix object
 *
 * @author Bartek
 * @version 2.0
 */
public class MatrixService {

    /**
     * Helper methode used to create a minor matrix for the element designated by the step argument
     *
     * @param origin original matrix to be split
     * @param step   column with the element to create minor matrix for
     * @return Matrix object containing the minor
     */
    private Matrix createSubMatrix(Matrix origin, int step) {

        Matrix next = new Matrix(origin.getNumberOfColumns() - 1, origin.getNumberOfRows() - 1);

        for (int i = 1; i < origin.getNumberOfColumns(); i++) {
            int j2 = 0;
            for (int j = 0; j < origin.getNumberOfColumns(); j++) {
                if (j == step)
                    continue;
                next.setMatrixValueAtPos(j2, i - 1, origin.getMatrixValueAtPos(j, i));
                j2++;
            }
        }
        return next;
    }

    /**
     * Recursive method used to calculate the determinant of a matrix with the minors methode
     *
     * @param matrix Input matrix for which we want to calculate the determinant
     * @return Double result of the calculation
     */
    public double calculateDeterminant(Matrix matrix) {
        double resoult;

        if (matrix.getNumberOfColumns() == 1) {
            return matrix.getMatrixValueAtPos(0, 0);
        } else if (matrix.getNumberOfColumns() == 2) {
            return matrix.getMatrixValueAtPos(0, 0) * matrix.getMatrixValueAtPos(1, 1) -
                    matrix.getMatrixValueAtPos(0, 1) * matrix.getMatrixValueAtPos(1, 0);
        } else {
            resoult = 0.0;
            for (int j = 0; j < matrix.getNumberOfColumns(); j++) {
                resoult += Math.pow(-1.0, 1.0 + j + 1.0) * matrix.getMatrixValueAtPos(j, 0) * calculateDeterminant(
                        createSubMatrix(matrix, j));
            }
        }
        return resoult;
    }

    /**
     * Methode for transposing matrix
     *
     * @param matrix Input matrix which we want to transpose
     * @return Transposed Matrix object
     */
    public Matrix transposeMatrix(Matrix matrix) {

        Matrix result = new Matrix(matrix.getNumberOfRows(), matrix.getNumberOfColumns());

        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColumns(); j++) {
                result.setMatrixValueAtPos(i, j, matrix.getMatrixValueAtPos(j, i));
            }
        }
        return result;
    }

    /**
     * Methode used to compare the matrix to a two-dimensional array
     * Class designed for Tests and should only be used there
     * Should only be used with correct values - the same matrix size
     *
     * @param tab value to compare to
     * @return true if both arrays has the same elements in the same positions
     */
    public boolean compareMatrix(Matrix mat, Double[][] tab) {
        for (int i = 0; i < mat.getNumberOfRows(); i++) {
            for (int j = 0; j < mat.getNumberOfColumns(); j++) {
                if (!Objects.equals(mat.getMatrixValueAtPos(j, i), tab[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
