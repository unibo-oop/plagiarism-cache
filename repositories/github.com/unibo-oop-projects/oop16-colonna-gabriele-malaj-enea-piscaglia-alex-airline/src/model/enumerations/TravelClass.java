package model.enumerations;

/**
 * Defines the travel classes.
 */
public enum TravelClass {

    /**
     * The economy class.
     */
    ECONOMY("Economy class"),

    /**
     * The business class.
     */
    BUSINESS("Business class"),

    /**
     * The first class.
     */
    FIRST("First class");

    private final String actualName;

    TravelClass(final String name) {
        this.actualName = name;
    }

    @Override
    public String toString() {
        return this.actualName;
    }

}
