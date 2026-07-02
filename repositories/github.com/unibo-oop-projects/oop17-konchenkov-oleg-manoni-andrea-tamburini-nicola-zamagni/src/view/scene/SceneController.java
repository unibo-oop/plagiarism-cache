package view.scene;

import javafx.scene.Node;

/**
 * Interface for a basic Controller for FXML scenes.
 *
 * @author Andrea Manoni
 *
 */
public interface SceneController {
    /**
     * Gives the controller the possibility to communicate to the MainController.
     *
     * @param mainController
     *            mainController
     */
    void setMainApp(SceneMainController mainController);

    /**
     * Get a specific Node inside the primary Scene.
     *
     * @param mainController
     *            mainController
     * @param idNode
     *            idNode
     * @return Node
     *
     */

    Node getNode(SceneMainController mainController, String idNode);

    /**
     * Get a specific Node inside a specific Pane inside the primary Scene.
     *
     * @param mainController
     *            mainController
     * @param idNode
     *            idNode
     * @param nest
     *            nest
     * @return Node
     */
    Node getNodeNested(SceneMainController mainController, String idNode, String nest);
}
