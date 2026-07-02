/**
 * 
 */
package model.entities;

/**
 * Models {@link Hero}'s movement status.
 */
public enum MovementStatus {
    /**
     * Hero can move.
     */
    AVAILABLE(Constants.DEFAULT_AVAILABLE_TEXT),
    /**
     * Hero is exhausted from moving.
     */
    EXHAUSTED(Constants.DEFAULT_EXHAUSTED_TEXT);

    private String text;

    MovementStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    private static class Constants {
        public static final String DEFAULT_AVAILABLE_TEXT = "Available";
        public static final String DEFAULT_EXHAUSTED_TEXT = "Exhausted";
    }
}
