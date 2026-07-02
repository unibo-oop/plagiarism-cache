package core;

/**
 * Class that control all the game mechanism. This method setup the game for the
 * current level, the current mode and the current players.
 */
public interface GameLoop {

    /**
     * Initialize all the parts of the game.
     */
    void setup();

    /**
     * Loop where logic of the game are selected. Call this method starts the game
     * after the setup.
     */
    void mainLoop();

    /**
     * input of the Players and AI.
     */
    void processInput();

    /**
     * This method update the game state.
     * 
     * @param elapsed time elapsed for respect the frame rate. Update the current
     *                state of all game entities in the world.
     * 
     */
    void updateGame(int elapsed);

    /**
     * Render the entities from the world.
     */
    void render();

}
