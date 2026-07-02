/**
 * 
 */
package model.entities;

/**
 * Enumeration to represent if an hero can attack or not.
 */
public enum AttackStatus {
    /**
     * The Hero can still attack.
     */
    AVAILABLE(Constants.DEFAULT_AVAILABLE_TEXT),
    /**
     * The Hero is exhausted from attacking.
     */
    EXHAUSTED(Constants.DEFAULT_EXHAUSTED_TEXT);

    private String text;

    AttackStatus(final String text) {
        this.text = text;
    }

    /**
     * Returns a string representation of the enumerator status.
     * 
     * @return the string representing the enumerator status.
     */
    @Override
    public String toString() {
        return this.text;
    }

    private static class Constants {
        public static final String DEFAULT_AVAILABLE_TEXT = "Available";
        public static final String DEFAULT_EXHAUSTED_TEXT = "Exhausted";
    }
}
