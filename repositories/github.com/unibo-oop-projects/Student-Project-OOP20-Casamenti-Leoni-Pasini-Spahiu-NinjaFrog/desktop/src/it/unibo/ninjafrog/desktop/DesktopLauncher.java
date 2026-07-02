package it.unibo.ninjafrog.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import it.unibo.ninjafrog.game.NinjaFrogGame;

/**
 * DesktopLauncher of the game, which contains the main method.
 */
public final class DesktopLauncher {
    private static final int GAME_WIDTH = 1200;
    private static final int GAME_HEIGHT = 624;

    private DesktopLauncher() {
    }

    /**
     * Main method of the application.
     * 
     * @param arg Command line parameters.
     */
    public static void main(final String[] arg) {
        final Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Super Ninja Frog");
        config.setWindowedMode(GAME_WIDTH, GAME_HEIGHT);
        config.setResizable(true);
        new Lwjgl3Application(new NinjaFrogGame(), config);
    }
}
