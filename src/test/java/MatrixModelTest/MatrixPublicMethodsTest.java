package MatrixModelTest;

import model.Matrix;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class MatrixPublicMethodsTest {

    private Matrix matrix;
    @Test
    public void getMatrixValueAtPosition() {
        Double[][] tab = {{5.0}};
        matrix = new Matrix(tab);
        double result = matrix.getMatrixValueAtPos(0,0);
        assertEquals(result, 5.0, 0);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.0, 3.0, 4.0, Double.MAX_VALUE, Double.MIN_VALUE})
    public void setMatrixValueAtPositionDouble(double input) {
        matrix = new Matrix(1, 1);
        matrix.setMatrixValueAtPos(0, 0, input);
        double result = matrix.getMatrixValueAtPos(0,0);
        assertEquals(result, input, 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.0", "2.0", "3.0", "4.0", "1.7976931348623157E308", "4.9E-324"})
    public void setMatrixValueAtPositionString(String input) {
        matrix = new Matrix(1, 1);
        try {
            matrix.setMatrixValueAtPos(0, 0, input);
        } catch (Exception ignore) {}
        double result = matrix.getMatrixValueAtPos(0,0);
        assertEquals(result, Double.parseDouble(input), 0);
    }
}

