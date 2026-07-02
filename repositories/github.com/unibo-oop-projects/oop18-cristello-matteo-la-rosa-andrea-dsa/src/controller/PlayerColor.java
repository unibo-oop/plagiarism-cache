package controller;

/**
 * This enumeration is usefull to distinct diferent color suitable for players.
 */
public enum PlayerColor {
    /**
     * Purple color.
     */
    PURPLE("PURPLE"),
    /**
     * Green color.
     */
    GREEN("GREEN"),
    /**
     * Cyano color.
     */
    CYANO("CYANO"),
    /**
     * Orange color.
     */
    ORANGE("ORANGE"),
    /**
     * Red color.
     */
    RED("RED"),
    /**
     * Yellow color.
     */
    YELLOW("YELLOW");
    /**
     * Color of the specific call.
     */
    private String color;

    /**
     * Builder of a class call.
     */
    PlayerColor(final String color) {
        this.color = color;
    }

    /**
     * Return the color of the specific class call.
     * @return the saved color.
     */
    public String color() {
        return this.color;
    }
}
