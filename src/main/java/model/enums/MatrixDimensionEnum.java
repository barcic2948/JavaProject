package model.enums;

/**
 * Enum used to communicate the type of error when the exception is thrown.
 * @author Bartek
 * @version 1.0
 */
public enum MatrixDimensionEnum {

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
