package it.unibo.oop.mge.libraries;

/**
 * The digits from zero to nine.
 */
public enum Digits implements GenericEnum {

    /** The zero. */
    ZERO("0"), 

    /** The one. */
    ONE("1"), 

    /** The two. */
    TWO("2"), 

    /** The three. */
    THREE("3"), 

    /** The four. */
    FOUR("4"), 

    /** The five. */
    FIVE("5"), 

    /** The six. */
    SIX("6"),

    /** The seven. */
    SEVEN("7"), 

    /** The eight. */
    EIGHT("8"), 

    /** The nine. */
    NINE("9");

    /** The syntax. */
    private final String syntax;

    /**
     * Instantiates a new digits.
     *
     * @param syntax the syntax
     */
    Digits(final String syntax) {
        this.syntax = syntax;
    }

    /**
     * Gets the digit's syntax.
     *
     * @return the digit's syntax
     */
    public String getSyntax() {
        return this.syntax;
    }
}
