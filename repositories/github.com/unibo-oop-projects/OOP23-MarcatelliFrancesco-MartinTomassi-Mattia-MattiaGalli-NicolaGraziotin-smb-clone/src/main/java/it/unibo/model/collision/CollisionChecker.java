package it.unibo.model.collision;

/**
 * The CollisionChecker interface rapresents the checker of the
 * interactions between MeatBoy and any obstacols in the level map.
 */
public interface CollisionChecker {

    /**
     * Enum with the possible interactions state of MeatBoy entity.
     */
    enum CollisionState { GROUND, WALL, AIR, SAW, BANDAGE_GIRL, FALL }

    /**
     * Check if MeatBoy collides with any obstacols in the level map.
     */
    void isColliding();

    /**
     * Check if MeatBoy is in the game screen or if is fallen.
     * 
     * @return The state of MeatBoy related to border limit
     */
    CollisionState inWindow();

    /**
     * Update the position of Meatboy and his Hitbox, only 
     * if the movement that he has to do is possible.
     */
    void updateMeatBoy();

    /**
     * Set in which direction the MeatBoy has to move.
     * 
     * @param k the key pressed in input
     */
    void moveMeatBoy(int k);

    /**
     * Set in which direction the MeatBoy has to stop moving.
     * 
     * @param k the key released in input
     */
    void stopMovingMeatBoy(int k);

    /**
     * Returns the state of MeatBoy.
     * 
     * @return state of MeatBoy
     */
    CollisionState getState();

    /**
     * Initializes the states of the game.
     * This method is responsible for setting up or resetting the various states of the game.
     */
    void initializeStates();
}
