package jwhale.commons;
/**
 * Constants for network types.
 * 
 */
public enum NetworkDriver {
    /**
     * Default network driver.
     */
    BRIDGE("bridge"),
    /**
     * Host network without isolation.
     */
    OVERLAY("overlay"),
    /**
     * Macvlan for mac address asignation to container.
     */
    NONE("none");
    private String driver;

    NetworkDriver(final String driver) {
        this.driver = driver;
    }
    /**
     * Getter for section name.
     * @return section name as String
     */
    public String toString() {
        return this.driver;
    }
}
