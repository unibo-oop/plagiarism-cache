package model.minigames.sizecount;

public enum Operator {

    /**
     * Sum operator.
     */
    SUM("+"),

    /**
     * Subtraction operator.
     */
    SUB("-"),

    /**
     * Multiplication operator.
     */
    MUL("*"),

    /**
     * Division operator.
     */
    DIV("/");

    private String operator;

    Operator(final String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return this.operator;
    }

}
