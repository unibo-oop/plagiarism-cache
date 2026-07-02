package it.unibo.pacman.model.utilities;

/**
 * Represents the entities in the game with the corresponding value.
 *
 */
public enum EntityType {
    /**
     * Represents a wall.
     *
     */
    WALL('0'),
    /**
     * Represents a pill.
     *
     */
    PILL('1'),
    /**
     * Represents a power pill.
     *
     */
    POWERPILL('2'),
    /**
     * Represents the main character Pacman.
     *
     */
    PACMAN('3'),
    /**
     * Represents an enemy, a phantom.
     *
     */
    BLINKY('4'),
    /**
     * Represents the pink phantom.
     *
     */
    PINKY('5'),
    /**
     * Represents the light blue phantom.
     *
     */
    INKY('6'),
    /**
     * Represents the orange phantom.
     *
     */
    CLYDE('7'),
    /**
     * Represents the empty space.
     * 
     */
    EMPTY('8');
    private final char value;
    /**
     * This constructor permit to associate a EntityType with a value.
     * 
     * @param value of the entity
     */
    EntityType(final char value) {
        this.value = value;
    }
    /**
     * 
     * @return the value of the entity
     */
    public char getValue() {
        return this.value;
    }
    /**
     * @return a String for each EntityType.
     */
    @Override
    public String toString() {
        switch (this) {
        case WALL:
            return "Wall";
        case PILL:
            return "Pill";
        case POWERPILL:
            return "PowerPill";
        case PACMAN:
            return "Pacman";
        case INKY:
            return "Inky";
        case BLINKY:
            return "Blinky";
        case PINKY:
            return "Pinky";
        case CLYDE:
            return "Clyde";
        case EMPTY:
            return "Empty";
        default:
            return "";
        }
    }
}
