package util;

import util.SceneControllerLoaderProxy.FxmlID;
import view.controller.ControllerFXML;

/**
 * Provides a method to get a ControllerFXML object linked to it's fxml file
 * using the proxy design pattern. The real fxml controller initialization
 * algorith is meant to be decoupled from the user class as well as the fxml
 * controller object managing policy.
 */
public interface SceneControllerLoader {

    /**
     * Getter of an Image object.
     * 
     * @param identifier
     *            the FXML file identifier used in the FxmlID enum to identify a
     *            specific file
     * @return an initialized javafx controller relative to the specific fxml
     */
    ControllerFXML getController(FxmlID identifier);
}
