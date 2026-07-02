package javawulf.controller;

/**
 * Class that returns info coming from a Player about its status.
 */
public interface PlayerStatus {

    /**
     * @return The amount of life points the Player currently has
     */
    int getHealth();

    /**
     * @return The maximum amount of life points the Player could currently have
     */
    int getMaxHealth();

    /**
     * @return The amount of hit the Shield can withstand
     */
    int getShield();

    /**
     * @return The color Player should have
     */
    String getColor();

    /**
     * @return The direction the Player is facing.
     */
    String getDirection();

    /**
     * @return The X-axis position of the Player on the map
     */
    int getPlayerX();

    /**
     * @return The Y-axis position of the Player on the map
     */
    int getPlayerY();

    /**
     * @return The current point total
     */
    int getScore();

    /**
     * @return The X-axis position of the Sword on the map
     */
    int getSwordX();

    /**
     * @return The Y-axis position of the Sword on the map
     */
    int getSwordY();

    /**
     * @return The width of the collision area of Sword
     */
    int getSwordWidth();

    /**
     * @return The height of the collision area of Sword
     */
    int getSwordHeight();

    /**
     * @return The type of Sword the Player has
     */
    String getSwordType();

    /**
     * @return The number of pieces the Player has
     */
    int getAmuletPieces();

    /**
     * @return The type of collision the Player has
     */
    String getPlayerCollision();

    /**
     * @return The type of collision the Sword has
     */
    String getSwordCollision();

    /**
     * @return True if the Player has won
     */
    boolean hasPlayerWon();
}
