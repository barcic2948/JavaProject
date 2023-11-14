package matrixmodeltest;

import model.Matrix;
import model.MatrixService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatrixConstructorTest {

    private Matrix matrix;

    private MatrixService matrixService;

    @Before
    public void prepService() {
        matrixService = new MatrixService();
    }

    @Test
    public void testConstructorFromArrayColumns() {
        Double[][] tab = {{1.0, 2.0},
                {4.0, 5.0},
                {9.0, 7.0}};
        matrix = new Matrix(tab);

        assertEquals("Matrix columns size was read incorrectly", matrix.getNumberOfColumns(), 2);
    }

    @Test
    public void testConstructorFromArrayRows() {
        Double[][] tab = {{1.0, 2.0},
                {4.0, 5.0},
                {9.0, 7.0}};
        matrix = new Matrix(tab);

        assertEquals("Matrix rows size was read incorrectly", matrix.getNumberOfRows(), 3);
    }

    @Test
    public void testConstructorFromArrayMatrix() {
        Double[][] tab = {{1.0, 2.0},
                {4.0, 5.0},
                {9.0, 7.0}};

        matrix = new Matrix(tab);

        assertTrue("Matrix was not copied correctly", matrixService.compareMatrix(matrix, tab));

    }

    @Test
    public void testConstructorFromStringRows() {
        try {
            matrix = new Matrix("4", "5");
        } catch (Exception ignored) {
        }
        assertEquals("Matrix rows size was read incorrectly", matrix.getNumberOfRows(), 5);
    }

    @Test
    public void testConstructorFromStringColumns() {
        try {
            matrix = new Matrix("4", "5");
        } catch (Exception ignored) {
        }
        assertEquals("Matrix columns size was read incorrectly", matrix.getNumberOfColumns(), 4);
    }

}
