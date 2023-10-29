package model;

/**
 * Parse exception thrown when the input for the matrix at a certain position is not parsable.
 * @author Bartek
 * @version 1.1
 */
public class MatrixParseException extends Exception {

    /**
     * Exception constructor
     * @param x Position with respect to the columns
     * @param y Position with respect to the rows
     * @param value Incorrect input value to inform the user
     */
    MatrixParseException(int x, int y, String value) {
        super("Input could not be parsed (invalid character), please correct the mistake. Position: [" + x + ", " + y + "], character: \"" + value + "\"");
    }
}
