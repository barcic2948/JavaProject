package model;

/**
 *
 * @author Bartek
 */
public class MatrixService {
    
    private Matrix createSubMatrix(Matrix origin, Integer step) {

        Matrix next = new Matrix(origin.getDimX() - 1, origin.getDimY() - 1);

        for(int i = 1; i < origin.getDimX(); i++) {
            int j2 = 0;
            for(int j = 0; j < origin.getDimX(); j++) {
                if(j == step) 
                    continue;
                next.setMatrixValueAtPos(j2, i-1, origin.getMatrixValueAtPos(j, i));
                j2++;
            }
        }
        return next;
    }
    
    public double calculateDeterminant(Matrix matrix) {
        double resoult;

        if(matrix.getDimX() == 1) {
            return matrix.getMatrixValueAtPos(0, 0);
        } else if (matrix.getDimX() == 2) {
            return matrix.getMatrixValueAtPos(0, 0) * matrix.getMatrixValueAtPos(1, 1) -
                    matrix.getMatrixValueAtPos(0, 1) * matrix.getMatrixValueAtPos(1, 0);
        }
        else {
            resoult = 0.0;
            for (int j = 0; j < matrix.getDimX(); j++) {
                resoult += Math.pow(-1.0, 1.0 + j + 1.0) * matrix.getMatrixValueAtPos(j, 0) * calculateDeterminant(
                        createSubMatrix(matrix, j));
            }
        }
        return resoult;
    }

    public Matrix transposeMatrix(Matrix matrix) {

        Matrix result = new Matrix(matrix.getDimY(), matrix.getDimX());

        for(int i = 0; i < matrix.getDimY(); i++) {
            for (int j = 0; j < matrix.getDimX(); j++) {
                result.setMatrixValueAtPos(i, j, matrix.getMatrixValueAtPos(j, i));
            }
        }

        return result;
    }
}
