package model.enums;

/**
 * Enum which contains all the possible operations that can be performed in the program.
 *
 * @author Bartek
 * @version 1.2
 */
public enum MatrixOperationTypeEnum {
    /**
     * Transpose matrix operation
     */
    TRANSPOSE("--transpose"),
    /**
     * Calculate matrix determinant operation
     */
    DETERMINANT("--determinant"),
    /**
     * Print program help page
     */
    HELP("--help"),
    /**
     * Exit the program
     */
    ESCAPE("--escape");
    /**
     * String value used to link each enum value to it's corresponding input string value
     */
    private String value;

    /**
     * Private constructor for enum with value
     *
     * @param value String value for enum
     */
    MatrixOperationTypeEnum(String value) {
        this.value = value;
    }

    /**
     * Getter for the enum value
     *
     * @return String value of the element
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Method selects corresponding enum element when provided with string input
     *
     * @param s String input value
     * @return MatrixOperationTypeEnum element
     * @throws IllegalArgumentException If s input value does not match any of those specified in the value field.
     */
    public static MatrixOperationTypeEnum getEnum(String s) throws IllegalArgumentException {
        for (MatrixOperationTypeEnum me : values())
            if (me.getValue().equalsIgnoreCase(s)) return me;
        throw new IllegalArgumentException();
    }
}
