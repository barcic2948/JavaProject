package model.exceptions;

/**
 * Represents an exception when parsing fails for a matrix input at a specific position.
 * Extends the Exception class and provides details about the position and the incorrect input value.
 *
 * @author Bartek
 * @version 2.0
 */
public class MatrixParseException extends Exception {

    private final int x; // Represents the column position where parsing failed
    private final int y; // Represents the row position where parsing failed

    /**
     * Constructs a MatrixParseException with the specified coordinates and incorrect input value.
     *
     * @param x     The column position where parsing failed
     * @param y     The row position where parsing failed
     * @param value The incorrect input value causing the parsing failure
     */
    public MatrixParseException(int x, int y, String value) {
        super("Input could not be parsed (invalid character), please correct the mistake." +
                " Position: [" + x + ", " + y + "], character: \"" + value + "\"");
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the column position where the parsing failed.
     *
     * @return The column position where parsing failed
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieves the row position where the parsing failed.
     *
     * @return The row position where parsing failed
     */
    public int getY() {
        return y;
    }
}
