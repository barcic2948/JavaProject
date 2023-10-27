package model;

/**
 *
 * @author Bartek
 */
public class MatrixParseException extends Exception {

    private int x;
    private int y;

    MatrixParseException(int x, int y, String value) {
        super("Input could not be parsed (invalid character), please correct the mistake. Position: [" + x + ", " + y + "], character: \"" + value + "\"");
        this.x = x;
        this.y = y;
    }

    MatrixParseException(int x, int y) {
        super("Input could not be parsed (empty string), please correct the mistake. Position: [" + x + ", " + y + "]");
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
