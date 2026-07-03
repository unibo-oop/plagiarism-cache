package view;

import javafx.scene.layout.Region;

/**
 * Manage the scene contents display on a StackPane root element.
 */
public interface SceneManager {

    /**
     * Sets the principal scene graph content, everything else is removed from the
     * scene graph, except the scene root.
     * 
     * @param firstLayer
     *            the layer to set as first and only layer of the stack
     */
    void setStackHead(Region firstLayer);

    /**
     * Add the specified Region to the scene graph. Since the scene graph root is a
     * StackPane the specified Region will be added as a stack element.
     * 
     * Adding elements automatically scale them so that it will be rendered
     * proportionally to the scene's sizes.
     * 
     * @param layer
     *            the layer to be added to the stack
     */
    void pushLayer(Region layer);

    /**
     * Remove the element at the top of the StackPane collection.
     */
    void popLayer();

    /**
     * Force the scene graph root ro resize it's contents as well as adapting the
     * window to the new resolution.
     */
    void scaleRootContents();
}
