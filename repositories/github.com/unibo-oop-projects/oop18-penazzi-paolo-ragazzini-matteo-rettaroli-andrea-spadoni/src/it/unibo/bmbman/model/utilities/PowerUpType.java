package it.unibo.bmbman.model.utilities;
/**
 * Possible power-up view choices.
 */
public enum PowerUpType {
    /**
     * Give the hero a bonus life.
    */
    BONUS_LIFE("/powerups/+life.png"),
    /**
     * Give the player the possibility to place more bombs.
     */
    BONUS_BOMB("/powerups/bomb.png"),
    /**
     * Increase the hero's speed.
     */
    BONUS_SPEED("/powerups/speed.png"),
    /**
     * Increase the bomb's explosion range.
     */
    BONUS_RANGE("/powerups/range.png"),
    /**
     * Hero need this to open the door.
     */
    KEY("/powerups/key.png"),
    /**
     * Hero need to reach the door to win the game.
     */
    DOOR("/powerups/door.png"),
    /**
     * Decrease hero's life.
     */
    MALUS_LIFE("/powerups/-life.png"),
    /**
     * Invert the direction of the hero (e.g. if you press UP the hero will go DOWN).
     */
    MALUS_INVERT("/powerups/invert.png"),
    /**
     * Freeze the hero, so he can't move.
     */
    MALUS_FREEZE("/powerups/freeze.png"),
    /**
     * Decrease the hero's speed.
     */
    MALUS_SLOW("/powerups/slow.png");

    private final String name;
    /**
     * Construct a {@code MainMenuOption}.
     * @param name of main menu option
     */
    PowerUpType(final String name) {
        this.name = name;
    }
    /**
     * To string method.
     * @return name associate to each MainMenuOption
     */
    public String toString() {
        return name;
    }
}
