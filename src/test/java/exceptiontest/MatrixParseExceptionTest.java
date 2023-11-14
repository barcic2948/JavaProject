package exceptiontest;

import model.Matrix;
import model.exceptions.MatrixParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertThrows;

public class MatrixParseExceptionTest {

    private Matrix matrix;

    @BeforeEach
    public void prepMatrix() {
        matrix = new Matrix(1, 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1.et", "test", "test.1"})
    public void testParseException(String input) {
        Exception exception = assertThrows(MatrixParseException.class, () -> matrix.setMatrixValueAtPos(0, 0, input));

        String expectedMessage = "Input could not be parsed (invalid character), please correct the mistake. Position: [0, 0], character: \"" + input + "\"";

        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }
}
