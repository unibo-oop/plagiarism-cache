package view.utility;

import view.controller.ControllerFXML;

/**
 * Interface used to get controller FXML.
 *
 */
public interface LoaderFXML {

    /**
     * Load controller FXML.
     * 
     * @param scene
     *            Scene to load.
     * @return Loaded controller FXML.
     */
    ControllerFXML getFXMLController(SceneType scene);
}
