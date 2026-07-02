package it.unibo.oop.mge.libraries;

/**
 * The variables of a math function.
 */
public enum Variable implements GenericEnum {

    /** The x variable. */
    X("x"),

    /** The y variable. */
    Y("y");

    /** The variable's syntax. */
    private final String syntax;

    /**
     * Instantiates a new variable.
     *
     * @param syntax the variable's syntax
     */
    Variable(final String syntax) {
        this.syntax = syntax;
    }

    /**
     * Gets the variable's syntax.
     *
     * @return the variables's syntax
     */
    public String getSyntax() {
        return this.syntax;
    }
}
