package startgame;

import com.badlogic.gdx.Game;

import view.loading.LoadingScreen;
/**
 * It instantiates LoadingScreen and starts the game
 */
public class MainGame extends Game {

    private LoadingScreen loadingscreen;

    /**
     * {@inheritDoc}
     */
    @Override
    public void create() {
        this.loadingscreen = new LoadingScreen(this);
        setScreen(this.loadingscreen);
    }
}
