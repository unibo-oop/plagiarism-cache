package it.unibo.oop.mge.libraries;

/**
 * The function properties.
 */
public enum Properties implements GenericEnum {

    /** The function maximum point. */
    MAX("Max"), 

    /** The function minimum point. */
    MIN("Min");

    /** The syntax. */
    private final String syntax;

    /**
     * Instantiates a new property.
     *
     * @param syntax the property syntax
     */
    Properties(final String syntax) {
        this.syntax = syntax;
    }

    /**
     * Gets the property syntax.
     *
     * @return the property syntax
     */
    public String getSyntax() {
        return this.syntax;
    }
}
