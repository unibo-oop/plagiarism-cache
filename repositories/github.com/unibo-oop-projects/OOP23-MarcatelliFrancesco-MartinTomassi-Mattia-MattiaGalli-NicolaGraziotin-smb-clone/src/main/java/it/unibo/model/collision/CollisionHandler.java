package it.unibo.model.collision;

import it.unibo.model.GameModel;

/**
 * The CollisionHandler rapresents the handler of the CollisonChecker
 * and it comunicate with the controller and the CollisonChecker.
 */
public interface CollisionHandler {

    /**
     * Get the game model.
     * 
     * @return the game model
     */
    GameModel getGameModel();

    /**
     * Is the game loop of the game, it checks if the MeatBoy die 
     * touching a saw or falling off the bottom limit or if the MeatBoy wons.
     */
    void check();

    /**
     * Call the same method in CollisionChecker.
     * 
     * @param keyCode the key pressed
     */
    void moveMeatBoy(int keyCode);

    /**
     * Call the same method in CollisionChecker.
     * 
     * @param keyCode the key released
     */
    void stopMovingMeatBoy(int keyCode);

    /**
     * Call the same method in CollisionChecker.
     */
    void updateMeatBoy();

    /**
     * Initializes the states of the game.
     * This method is responsible for setting up or resetting the various states of the game.
     */
    void initializeStates();
}
