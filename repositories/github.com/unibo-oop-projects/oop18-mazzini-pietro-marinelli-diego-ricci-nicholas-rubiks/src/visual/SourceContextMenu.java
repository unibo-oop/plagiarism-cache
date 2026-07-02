package visual;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;

/**
 * This class is an extension of {@link ContextMenu}, attached to the normal ContextMenu there's
 * a Node, the source node of the ContextMenu.
 */
public class SourceContextMenu extends ContextMenu {

    private final Node sourceNode;

    /**
     * This constructor just sets the source node of the ContextMenu.
     * @param node - Source node.
     */
    public SourceContextMenu(final Node node) {
        super();
        this.sourceNode = node;
    }

    /**
     * Source node getter.
     * @return The source node.
     */
    public Node getSourceNode() {
        return sourceNode;
    }
}
