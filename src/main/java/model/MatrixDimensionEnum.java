package model;

/**
 * Enum used to communicate the type of error when the exception is thrown.
 */
public enum MatrixDimensionEnum {

    /**
     * Matrix is not square
     */
    SQUARE,

    /**
     * Matrix columns value is invalid
     */
    SIZE_ROWS,

    /**
     * Matrix rows value is invalid
     */
    SIZE_COLUMNS,

    /**
     * Matrix rows value parse exception
     */
    ROWS,

    /**
     * Matrix columns value parse exception
     */
    COLUMNS;
}
