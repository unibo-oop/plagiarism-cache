package it.unibo.biscia;

import it.unibo.biscia.view.managers.AssetManagerDecorator;
import it.unibo.biscia.view.screens.Loading;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * This is the core crossplatform starting point of the application. Biscia is a
 * {@link Game} which implement and exposes all the support a game should need
 * like managing screens, refrishing the window, dispose the application and a
 * lot more.
 * 
 * @see Game
 *
 */
//TODO: modificare la controllerUtils per:
// 1 -  eliminare bug vicinanza oggetti quando si sposta il focus, deve partire
//      da metà cosi in questo modo si riesce a distribuire bene le label sulla table nelle settings
//      semplicemente devi togliere lo spostamente con vicinanza, farlo monodirezionale e sequenziale!
// 2 -  far sie che il keydown dell escape actor non triggeri ma sposti solo il focus
public final class Biscia extends Game {
    /**
     * Utility class containing costants for the game.
     * 
     */
    public static final class Constants {
        /**
         * The window title.
         */
        public static final String WINDOW_TITLE = "Biscia v: 1.0.0";

        /**
         * The window width.
         */
        public static final int WINDOW_WIDTH = 800;

        /**
         * The window height.
         */
        public static final int WINDOW_HEIGHT = 600;

        private Constants() {
        }
    }

    private final AssetManagerDecorator manager;

    public Biscia() {
        this.manager = new AssetManagerDecorator();
    }

    @Override
    public void create() {
        // switch to loading screeen
        this.setScreen(new Loading());
    }

    @Override
    public void render() {
        // clears the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // call the screen render method
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        this.manager.dispose();
    }

    public AssetManagerDecorator getAssetManager() {
        return manager;
    }
}
