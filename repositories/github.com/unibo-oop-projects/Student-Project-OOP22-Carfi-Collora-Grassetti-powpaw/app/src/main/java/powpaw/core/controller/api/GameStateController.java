package powpaw.core.controller.api;

/**
 * GameStateController. Every method set a determinated state:
 * Start,Character Creation, Game and Game over.
 * 
 * @author Simone Collorà
 */
public interface GameStateController {

    /**
     * Set State to START.
     */
    void start();

    /**
     * Set State to STATS.
     */
    void characterCreation();

    /**
     * Set State to GAME.
     */
    void game();

    /**
     * Set State to GAMEOVER.
     */
    void gameOver();
}
