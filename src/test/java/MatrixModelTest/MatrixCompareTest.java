package MatrixModelTest;

import model.Matrix;
import model.MatrixService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MatrixCompareTest {

    private MatrixService matrixService;

    @Before
    public void prepService() {
        matrixService = new MatrixService();
    }

    @Test
    public void testCompare3by5Matrix() {
        Double[][] toCompare = {{1.0, 2.0, 3.0},
                {5.0, 6.0, 7.0},
                {9.0, 10.0, 11.0},
                {20.0, 21.0, 22.0},
                {20.0, 21.0, 22.0}};

        Matrix matrix = new Matrix(toCompare);

        assertTrue(matrixService.compareMatrix(matrix, toCompare));
    }

    @Test
    public void testCompare5by5Matrix() {
        Double[][] toCompare = {{1.0, 2.0, 3.0, 4.0, 5.0},
                {5.0, 6.0, 7.0, 8.0, 9.0},
                {9.0, 10.0, 11.0, 12.0, 13.0},
                {20.0, 21.0, 22.0, 23.0, 24.0},
                {20.0, 21.0, 22.0, 23.0, 24.0}};

        Matrix matrix = new Matrix(toCompare);

        assertTrue(matrixService.compareMatrix(matrix, toCompare));
    }
}
