package casim.utils;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * A utility class for views.
 */
public final class ViewUtils {

    private ViewUtils() {
    }

    /**
     * Set the anchors for the node (if it's contained by an {@link AnchorPane}).
     * 
     * @param node the {@link Node} to anchor.
     */
    public static void fitToAnchorPane(final Node node) {
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }
}
