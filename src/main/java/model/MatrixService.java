package model;

/**
 * @author Bartek
 */
public class MatrixService {

    private Matrix createSubMatrix(Matrix origin, Integer step) {

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

    public Matrix transposeMatrix(Matrix matrix) {

        Matrix result = new Matrix(matrix.getNumberOfRows(), matrix.getNumberOfColumns());

        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColumns(); j++) {
                result.setMatrixValueAtPos(i, j, matrix.getMatrixValueAtPos(j, i));
            }
        }
        return result;
    }
}
