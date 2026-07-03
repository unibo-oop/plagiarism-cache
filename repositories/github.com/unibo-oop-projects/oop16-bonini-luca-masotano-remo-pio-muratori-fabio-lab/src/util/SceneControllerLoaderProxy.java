package util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import view.controller.ControllerFXML;

/**
 * Singleton class used to provide scene controllers and internaly keep/manage
 * them for future uses. This class is meant to be a proxy between the
 * controller user and fxml file loader and controller initializer.
 */
public final class SceneControllerLoaderProxy implements SceneControllerLoader {

    private Map<FxmlID, ControllerFXML> loadedControllers;
    private RealSceneControllerLoader realLoader;
    private static SceneControllerLoaderProxy singleton;

    private SceneControllerLoaderProxy() {
        this.realLoader = new RealSceneControllerLoader();
        this.loadedControllers = new HashMap<>();
    }

    @Override
    public ControllerFXML getController(final FxmlID identifier) {
        if (!loadedControllers.containsKey(identifier)) {
            this.loadedControllers.put(identifier, this.realLoader.getController(identifier));
        }
        return this.loadedControllers.get(identifier);
    }

    /**
     * Getter of the singleton of this class.
     * 
     * @return a ProxySceneControllerLoader object
     */
    public static SceneControllerLoaderProxy get() {
        if (Objects.isNull(singleton)) {
            singleton = new SceneControllerLoaderProxy();
        }
        return singleton;
    }

    /**
     * Class used to load external fxml files; part of proxy pattern. Being both
     * inner class and final this class is accessible only by
     * {@link SceneControllerLoaderProxy}.
     */
    private class RealSceneControllerLoader implements SceneControllerLoader {

        private static final String FILE_PATH = "/view/scene/";

        @Override
        public ControllerFXML getController(final FxmlID identifier) {
            ControllerFXML controller = null;
            FXMLLoader loader = null;
            try {
                loader = new FXMLLoader();
                loader.load(
                        getClass().getResourceAsStream(RealSceneControllerLoader.FILE_PATH + identifier.getFileName()));
                controller = loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return controller;
        }
    }

    /**
     * Identifier of a single fxml file, used to hide fxml real name from it's
     * utilization in "external" code.
     */
    public enum FxmlID {
        /**
         * Credits scene graph fxml identifier.
         */
        CREDITS("CreditsScreen.fxml"),
        /**
         * GameScreen scene graph fxml identifier.
         */
        GAME("GameScreen.fxml"),
        /**
         * HelpScreen scene graph fxml identifier.
         */
        HELP("HelpScreen.fxml"),
        /**
         * MenuScreen scene graph fxml identifier.
         */
        MENU("MenuScreen.fxml"),
        /**
         * OptionsScreen scene graph fxml identifier.
         */
        OPTIONS("OptionsScreen.fxml"),
        /**
         * GameOverScreen scene graph fxml identifier.
         */
        GAME_OVER("GameOverScreen.fxml"),
        /**
         * Pause scene graph fxml identifier.
         */
        PAUSE("PauseScreen.fxml");

        private String file;

        FxmlID(final String file) {
            this.file = file;
        }

        private String getFileName() {
            return file;
        }
    }
}
