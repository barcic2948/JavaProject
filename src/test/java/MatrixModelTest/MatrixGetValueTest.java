package MatrixModelTest;

import model.Matrix;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatrixGetValueTest {
    @Test
    public void testGetMatrixValueAtPosition() {
        Double[][] tab = {{5.0}};
        Matrix matrix = new Matrix(tab);
        double result = matrix.getMatrixValueAtPos(0, 0);
        assertEquals(result, 5.0, 0);
    }
}
