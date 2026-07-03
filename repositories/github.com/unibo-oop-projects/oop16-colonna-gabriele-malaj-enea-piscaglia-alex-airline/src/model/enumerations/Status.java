package model.enumerations;

/**
 * 
 * Defines the plane's status.
 */
public enum Status {

    /**
     * The plane is at the airport.
     */
    AT_AIRPORT("At the airport"),

    /**
     * The plane is booked (it's inserted in a flight).
     */
    BOOKED("Booked"),

    /**
     * The plane is taken off.
     */
    TAKEN_OFF("Taken off"),

    /**
     * The plane is landed.
     */
    LANDED("Landed");

    private final String actualName;

    Status(final String name) {
        this.actualName = name;
    }

    @Override
    public String toString() {
        return this.actualName;
    }

}
