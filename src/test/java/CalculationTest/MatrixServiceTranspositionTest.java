package CalculationTest;

import model.Matrix;
import model.MatrixService;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class MatrixServiceTranspositionTest {

    private MatrixService matrixService;

    @Before
    public void init() {
        matrixService = new MatrixService();
    }

    private boolean compareMatrix(Matrix a, Double[][] tab) {
        for (int i = 0; i < a.getNumberOfRows(); i++) {
            for (int j = 0; j < a.getNumberOfColumns(); j++) {
                if (!Objects.equals(a.getMatrixValueAtPos(j, i), tab[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testTransposition1by1() {
        Double[][] toTranspose = {{1.0}};

        Double[][] result = {{1.0}};

        Matrix mat = new Matrix(toTranspose);

        matrixService.transposeMatrix(mat);

        assertTrue(compareMatrix(mat, result));
    }

    @Test
    public void testTranspositionSquare5by5() {
        Double[][] toTranspose = {{1.0, 2.0, 3.0, 4.0, 5.0},
                                  {5.0, 6.0, 7.0, 8.0, 9.0},
                                  {9.0, 10.0, 11.0, 12.0, 13.0},
                                  {20.0, 21.0, 22.0, 23.0, 24.0},
                                  {20.0, 21.0, 22.0, 23.0, 24.0}};

        Double[][] result = {{1.0, 5.0, 9.0, 20.0, 20.0},
                              {2.0, 6.0, 10.0, 21.0, 21.0},
                              {3.0, 7.0, 11.0, 22.0, 22.0},
                              {4.0, 8.0, 12.0, 23.0, 23.0},
                              {5.0, 9.0, 13.0, 24.0, 24.0}};

        Matrix mat = new Matrix(toTranspose);

        assertTrue(compareMatrix(matrixService.transposeMatrix(mat), result));
    }
    
    @Test
    public void testTranspositionNotSquare1by5() {
        Double[][] toTranspose = {{1.0, 2.0, 3.0, 4.0, 5.0}};
        Double[][] result = {{1.0},
                             {2.0},
                             {3.0},
                             {4.0},
                             {5.0}};

        Matrix mat = new Matrix(toTranspose);

        assertTrue(compareMatrix(matrixService.transposeMatrix(mat), result));

    }

    @Test
    public void testTranspositionNotSquare3by3() {
        Double[][] toTranspose = {{1.0, 2.0, 3.0},
                                  {5.0, 6.0, 7.0},
                                  {9.0, 10.0, 11.0},
                                  {20.0, 21.0, 22.0},
                                  {20.0, 21.0, 22.0}};

        Double[][] result = {{1.0, 5.0, 9.0, 20.0, 20.0},
                             {2.0, 6.0, 10.0, 21.0, 21.0},
                             {3.0, 7.0, 11.0, 22.0, 22.0}};

        Matrix mat = new Matrix(toTranspose);

        assertTrue(compareMatrix(matrixService.transposeMatrix(mat), result));
    }

}

