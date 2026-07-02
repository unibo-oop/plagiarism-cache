package it.unibo.biscia.desktop;

import it.unibo.biscia.Biscia;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * entry point of desktop application.
 *
 */
public final class DesktopLauncher {

    private DesktopLauncher() {
    }

    /**
     * entry point of desktop application.
     * 
     * @param arg arguments from command line
     */
    public static void main(final String[] arg) {
        final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Biscia.Constants.WINDOW_TITLE;
        config.width = Biscia.Constants.WINDOW_WIDTH;
        config.height = Biscia.Constants.WINDOW_HEIGHT;
        config.resizable = false;
        new LwjglApplication(new Biscia(), config);
    }
}
