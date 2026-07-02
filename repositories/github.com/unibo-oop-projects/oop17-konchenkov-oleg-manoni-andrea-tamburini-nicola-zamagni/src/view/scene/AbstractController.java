package view.scene;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author Andrea Manoni
 *
 */
public abstract class AbstractController implements SceneController {

    /**
     * Gives the controller the possibility to communicate to the MainController.
     *
     * @param mainController
     *            mainController
     */
    @Override
    public abstract void setMainApp(SceneMainController mainController);

    /**
     *
     * @param mainController
     *            mainController
     * @param idNode
     *            idNode
     * @return Node
     */
    // Get a specific Node inside the primary Scene
    @Override
    public Node getNode(final SceneMainController mainController, final String idNode) throws IllegalArgumentException {
        for (final Node elem : mainController.getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable()) {
            if (elem.getId() != null && elem.getId().equals(idNode)) {
                return elem;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     *
     * @param mainController
     *            mainController
     * @param idNode
     *            idNode
     * @param nest
     *            nest
     * @return Node
     */
    // Get a specific Node inside a specific Pane inside the primary Scene
    @Override
    public Node getNodeNested(final SceneMainController mainController, final String idNode, final String nest) throws IllegalArgumentException {
        for (final Node parent : mainController.getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable()) {
            if (parent.getId() != null && parent.getId().equals(nest)) {
                for (final Node elem : ((Pane) parent).getChildren()) {
                    if (elem.getId() != null && elem.getId().equals(idNode)) {
                        return elem;
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }
}
