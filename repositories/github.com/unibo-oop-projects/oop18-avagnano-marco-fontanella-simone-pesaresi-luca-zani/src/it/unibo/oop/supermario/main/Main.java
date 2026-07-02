package it.unibo.oop.supermario.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.screens.GameConstant;

/**
 * This is the main class of the game and where all it starts.
 * The resolution is configured.
 */
public final class Main {

    private Main() {
    }

    /**
     * Entry point of the application.
     * @param args the command line parameters
     */
    public static void main(final String[] args) {
        final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //window configuration
        config.width = GameConstant.WIDTH_RESOLUTION;
        config.height = GameConstant.HEIGHT_RESOLUTION;
        new LwjglApplication(new SuperMario(), config);

    }
}