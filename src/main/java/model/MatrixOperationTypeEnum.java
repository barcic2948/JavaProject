package model;

public enum MatrixOperationTypeEnum {
    TRANSPOSE("--transpose"),
    DETERMINANT("--determinant"),
    HELP("--help"),
    ESCAPE("--escape");

    private String value;

    private MatrixOperationTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    //Don't know how to get around this static field here :(
    public static MatrixOperationTypeEnum getEnum(String value) {
        for (MatrixOperationTypeEnum me : values())
            if (me.getValue().equalsIgnoreCase(value)) return me;
        throw new IllegalArgumentException();
    }


}
