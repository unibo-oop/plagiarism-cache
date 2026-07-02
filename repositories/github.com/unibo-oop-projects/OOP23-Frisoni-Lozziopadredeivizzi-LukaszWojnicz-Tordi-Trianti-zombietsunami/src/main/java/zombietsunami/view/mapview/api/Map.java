package zombietsunami.view.mapview.api;

/**
 * This is the principal panel for the game, that allows you to run the map and
 * its game engine.
 */
public interface Map {

    /**
     * This method represents the game engine to be recalled into the run method of
     * the Thread. It updates the game elements and repaints them.
     */
    void gameLoop();

    /**
     * This method calls the Thread and starts it.
     */
    void startGameThread();

    /**
     * This method, which is recalled in the game engine, updates the elements of
     * the game map.
     * 
     */
    void update();

}
