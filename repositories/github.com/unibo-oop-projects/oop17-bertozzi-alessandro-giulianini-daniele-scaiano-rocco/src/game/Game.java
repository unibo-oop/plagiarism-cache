package game;

import java.util.List;

/**
 * The basic methods the game will need to work with controller and view.
 */
public interface Game {

    /**
     * Updates all the entities in game.
     */
    void update();

    /**
     * @return all the entities currently in game
     */
    List<GameObject> getEntities();

    /**
     * @return the game mode
     */
    GameMode getMode();

    /**
     * @return the game state
     */
    GameState getState();

    /**
     * The method that checks if two entities are colliding with each other.
     * It needs to be called once every cycle of the game loop.
     */
    void checkCollisions();

    /**
     * @return the current level
     */
    int getLevel();

    /**
     * @return the current score
     */
    int getScore();

    /**
     * @return the player/s
     */
    List<Player> getPlayer();

    /**
     * @return the list of bullets
     */
    List<Bullet> getBullets();
}
