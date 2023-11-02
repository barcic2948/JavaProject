package MatrixModelTest;

import model.Matrix;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class MatrixSetValueTest {

    private Matrix matrix;

    @BeforeEach
    public void createMatrix() {
        matrix = new Matrix(1, 1);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.0, 3.0, 4.0, Double.MAX_VALUE, Double.MIN_VALUE})
    public void testSetMatrixValueAtPositionDouble(double input) {
        matrix.setMatrixValueAtPos(0, 0, input);
        double result = matrix.getMatrixValueAtPos(0, 0);
        Assertions.assertEquals(result, input, 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.0", "2.0", "3.0", "4.0", "1.7976931348623157E308", "4.9E-324"})
    public void testSetMatrixValueAtPositionString(String input) {
        try {
            matrix.setMatrixValueAtPos(0, 0, input);
        } catch (Exception ignore) {
        }
        double result = matrix.getMatrixValueAtPos(0, 0);
        Assertions.assertEquals(result, Double.parseDouble(input), 0);
    }
}

