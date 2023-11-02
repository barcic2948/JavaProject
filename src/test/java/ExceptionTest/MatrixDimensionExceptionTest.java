package ExceptionTest;

import model.Matrix;
import model.enums.MatrixDimensionEnum;
import model.exceptions.MatrixDimensionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertThrows;

public class MatrixDimensionExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "1.0", "test"})
    public void testInvalidRows(String input) {
        MatrixDimensionException exception = assertThrows(MatrixDimensionException.class, () -> new Matrix("3", input));

        Assertions.assertEquals(MatrixDimensionEnum.ROWS, exception.getEnum());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "7", "-5"})
    public void testIncorrectRowsSize(String input) {
        MatrixDimensionException exception = assertThrows(MatrixDimensionException.class, () -> new Matrix("3", input));

        Assertions.assertEquals(MatrixDimensionEnum.SIZE_ROWS, exception.getEnum());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1.0", "test"})
    public void testInvalidColumns(String input) {
        MatrixDimensionException exception = assertThrows(MatrixDimensionException.class, () -> new Matrix(input, "3"));

        Assertions.assertEquals(MatrixDimensionEnum.COLUMNS, exception.getEnum());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "7", "-5"})
    public void testIncorrectColumnsSize(String input) {
        MatrixDimensionException exception = assertThrows(MatrixDimensionException.class, () -> new Matrix(input, "3"));

        Assertions.assertEquals(MatrixDimensionEnum.SIZE_COLUMNS, exception.getEnum());
    }
}
