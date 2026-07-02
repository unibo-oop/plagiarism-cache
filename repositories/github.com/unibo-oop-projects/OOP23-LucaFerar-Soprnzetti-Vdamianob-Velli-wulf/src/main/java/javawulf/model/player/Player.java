package javawulf.model.player;

import javawulf.model.Direction;
import javawulf.model.Entity;
import javawulf.model.item.AmuletPiece;
import javawulf.model.map.Map;
import javawulf.model.powerup.PowerUpHandler;

/**
 * Player represents the playable character and its statitstics.
 */
public interface Player extends Entity {

    /**
     * PlayerColor defines the current color of the Player character.
     * It changes depending on the current Power-Up the Player has
     */
    enum PlayerColor {
        /**
         * The color the Player changes into when he collects a strength boosting
         * Power-Up.
         */
        STRENGTH("red"),
        /**
         * The color the Player changes into when he collects a invincibility
         * Power-Up.
         */
        INVULNERABILITY("blue"),
        /**
         * The color the Player changes into when he collects a points boosting
         * Power-Up.
         */
        DOUBLE_POINTS("yellow"),
        /**
         * The color the Player changes into when he collects a speed boosting
         * Power-Up.
         */
        SPEED("green"),
        /**
         * The color the Player has by default and has no Power-Up activated.
         */
        NONE("none");

        private final String color;

        PlayerColor(final String color) {
            this.color = color;
        }

        /**
         * @return The string corrisponding to the color
         */
        public String getColor() {
            return this.color;
        }
    }

    /**
     * Activate the sword in order to attack.
     */
    void attack();

    /**
     * Move in the specified direction.
     * 
     * @param direction The direction the player character must move towards
     * @param map The map, whose tiles will be checked in order to
     * understand whether the Player is going towards a wall
     * @throws IllegalStateException If the character can't continue in that direction
     * (due to a wall) 
     */
    void move(Direction direction, Map map);

    /**
     * Adds an amulet piece to the Player's inventory. If it goes over the number
     * of biomes it won't be added to the inventory
     * 
     * @param piece The amulet piece being added to the inventory
     * @throws IllegalStateException If all pieces have been already collected and
     * another is getting added
     */
    void collectAmuletPiece(AmuletPiece piece);

    /**
     * @return The current health of the player character, including also the maximum
     * amount of health currently obtainable and his shield
     */
    PlayerHealth getPlayerHealth();

    /**
     * @return The current Power-Up Player is subject to and for how much
     * longer it will last
     */
    PowerUpHandler getPowerUpHandler();

    /**
     * @return The current point total and point multiplier
     */
    Score getScore();

    /**
     * @return The player's sword
     */
    Sword getSword();

    /**
     * @return The color of the Player
     */
    String getColor();

    /**
     * @param color The color the Player character will now have
     */
    void setColor(PlayerColor color);

    /**
     * @return The amount of amulet pieces Player has collected
     */
    int getNumberOfPieces();

    /**
     * @param map The map the Player is on
     * @return True if the player has collected all 4 pieces and
     * is standing on the portal
     */
    boolean hasPlayerWon(Map map);

}
