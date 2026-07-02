package view.utility;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import view.controller.ControllerFXML;

/**
 * Class that represent proxy for FXML files.
 *
 */
public final class ProxyFXMLLoader implements LoaderFXML {

    private final Map<SceneType, ControllerFXML> loadedMap;
    private final RealFXMLLoader realLoader;
    private static ProxyFXMLLoader proxy;

    private ProxyFXMLLoader() {
        loadedMap = new HashMap<>();
        realLoader = new RealFXMLLoader();
    }

    /**
     * Return ControllerFXML for a scene.
     */
    @Override
    public ControllerFXML getFXMLController(final SceneType scene) {
        if (loadedMap.containsKey(scene)) {
            return loadedMap.get(scene);
        } else {
            loadedMap.put(scene, realLoader.getFXMLController(scene));
            return loadedMap.get(scene);
        }
    }

    /**
     * Singleton to returns only instance of this class.
     * 
     * @return Only instance of this class.
     */
    public static ProxyFXMLLoader get() {
        if (Objects.isNull(proxy)) {
            proxy = new ProxyFXMLLoader();
        }
        return proxy;
    }

    private class RealFXMLLoader implements LoaderFXML {

        private static final String PATH = "/view/";

        /**
         * Get the FXML loaded controller.
         */
        @Override
        public ControllerFXML getFXMLController(final SceneType scene) {
            ControllerFXML controllerFXML;
            final FXMLLoader loader = new FXMLLoader();
            try {
                loader.load(getClass().getResourceAsStream(PATH + scene.getFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            controllerFXML = loader.getController();
            return controllerFXML;
        }

    }
}
