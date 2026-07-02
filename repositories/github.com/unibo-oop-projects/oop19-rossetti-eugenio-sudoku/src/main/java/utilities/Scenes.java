package utilities;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

/**
 * 
 * Enumeration that defines scenes of the view.
 *
 */
public enum Scenes {
    /**
     * Home scene.
     */
    HOME("HomeController"),

    /**
     * Game scene.
     */
    GAME("GameController"),

    /**
     * Settings scene.
     */
    SETTINGS("SettingsController");

    private static final String PATH = "layouts/";
    private static final String FORMAT = ".fxml";
    private final String name;

    /**
     * Create a new Scene.
     * @param name of the scene
     */
    Scenes(final String name) {
        this.name = name;
    }

    /**
     * @return the FXMLLoader for a scene
     * @throws IOException
     */
    public FXMLLoader getLoader() throws IOException {
        return new FXMLLoader(ClassLoader.getSystemResource(PATH + name + FORMAT));
    }

    @Override
    public String toString() {
        return this.name;
    }

}
