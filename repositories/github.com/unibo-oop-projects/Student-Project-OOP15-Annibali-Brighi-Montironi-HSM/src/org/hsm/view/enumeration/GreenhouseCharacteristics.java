package org.hsm.view.enumeration;

/**
 * The enum represents all the characteristics of the greenhouse.
 *
 */
public enum GreenhouseCharacteristics {

    /**
     * The greenhouse name.
     */
    NAME("Name"),
    /**
     * The greenhouse dimension (m2).
     */
    DIMENSION("Dimension (m2)"),
    /**
     * The greenhouse used space (m2).
     */
    USED_SPACE("Used Space (m2)"),
    /**
     * The greenhouse free space (m2).
     */
    FREE_SPACE("Free Space (m2)"),
    /**
     * The number of plants inside the greenhouse.
     */
    NUMBER_OF_PLANTS("Number of Plants"),
    /**
     * The cost of the greenhouse.
     */
    COST("Cost (€)"),
    /**
     * The typology of the greenhouse.
     */
    TYPOLOGY("Typology"),
    /**
     * The overall cost of the greenhouse.
     */
    OVERALL_COST("Overall Cost");

    private final String name;

    GreenhouseCharacteristics(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
