package it.unibo.biscia.core;
/**
 * the possible type of entities.
 *
 */
public enum EntityType {
    /**
     * if biscia crush over this, die.
     */
    WALL,
    /**
     * if biscia crush over this, eat.
     */
    FOOD,
    /**
     * principal friend of players.
     */
    SNAKE;
}
