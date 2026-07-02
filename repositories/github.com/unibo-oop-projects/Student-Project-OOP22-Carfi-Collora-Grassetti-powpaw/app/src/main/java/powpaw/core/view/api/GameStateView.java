package powpaw.core.view.api;

/**
 * GameStateView interface. Every method renderize a determinated state
 * 
 * @author Simone Collorà
 */
public interface GameStateView {

    /**
     * show StartMenu and set GameState to START.
     */
    void showStartMenu();

    /**
     * show stats setting menu and set GameState to STATS.
     */
    void showCharacterCreation();

    /**
     * show the game itself and set GameState to GAME. It also start the gameloop
     * and inizialize the render.
     */
    void showGame();

    /**
     * show GameOver and set GameState to GAMEOVER. It also stop the loop.
     */
    void showGameOver();
}
