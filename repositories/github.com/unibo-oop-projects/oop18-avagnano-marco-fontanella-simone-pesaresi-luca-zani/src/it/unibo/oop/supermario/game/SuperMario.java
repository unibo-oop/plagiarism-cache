package it.unibo.oop.supermario.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.MainMenu;

/**
 * The main class of the game
 */
public class SuperMario extends Game {

    /**
     * Handle the drawings and animations on the screen
     */
    public SpriteBatch batch;
    private GameManager gameManager;


    @Override
    public final void create() {
        batch = new SpriteBatch();
        if (GameManager.instance != null) {
            gameManager = GameManager.instance;
        } else {
            gameManager = new GameManager(this);
        }

       setScreen(new MainMenu(this));
    }
    @Override
    public final void render() {
        super.render();
    }
    @Override
    public final void dispose() {
        super.dispose();
        gameManager.dispose();
    }
}
