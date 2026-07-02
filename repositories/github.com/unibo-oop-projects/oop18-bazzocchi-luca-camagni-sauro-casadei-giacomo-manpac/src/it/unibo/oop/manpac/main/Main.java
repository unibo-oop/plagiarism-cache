package it.unibo.oop.manpac.main;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * The Main class of ManPac.
 */
public final class Main {

    private static final String ICON_PATH = "Sprites/" + "icon_32x32.png";
    private static final String TITLE = "Man-Pac";

    private Main() {
    }

    /**
     * The starting point of the application.
     * 
     * @param args Unused.
     */
    public static void main(final String[] args) {
        final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = TITLE;
        config.addIcon(ICON_PATH, FileType.Internal);
        new LwjglApplication(new ManpacGame(), config);
    }

}
