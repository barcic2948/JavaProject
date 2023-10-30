package MatrixModelTest;

import model.Matrix;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatrixConstructorTest {

    private Matrix matrix;

    @Test
    public void constructorFromArrayColumnsTest() {
        Double[][] tab = {{1.0,2.0},
                {4.0,5.0},
                {9.0,7.0}};
        matrix = new Matrix(tab);

        assertEquals("Matrix columns size was read incorrectly", matrix.getNumberOfColumns(), 2);
    }

    @Test
    public void constructorFromArrayRowsTest() {
        Double[][] tab = {{1.0,2.0},
                {4.0,5.0},
                {9.0,7.0}};
        matrix = new Matrix(tab);

        assertEquals("Matrix rows size was read incorrectly", matrix.getNumberOfRows(), 3);
    }

    @Test
    public void constructorFromArrayMatrixTest() {
        Double[][] tab = {{1.0,2.0},
                          {4.0,5.0},
                          {9.0,7.0}};

        matrix = new Matrix(tab);

        assertTrue("Matrix was not copied correctly", matrix.compareMatrix(tab));

    }

    @Test
    public void constructorFromStringRowsTest() {
        try {
            matrix = new Matrix("4", "5");
        } catch (Exception ignored) {
        }
        assertEquals("Matrix rows size was read incorrectly", matrix.getNumberOfRows(), 5);
    }

    @Test
    public void constructorFromStringColumnsTest() {
        try {
            matrix = new Matrix("4", "5");
        } catch (Exception ignored) {
        }
        assertEquals("Matrix columns size was read incorrectly", matrix.getNumberOfColumns(), 4);
    }

}
