package oop.lit.view.controller.rotate;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oop.lit.view.BoardElementView;

/**
 * Partial implementation of the logic behind rotation.
 */
public abstract class AbstractRotate {
    /**
     * @param bev
     *            the BoardElementView that you want to be rotatable.
     */
    public void makeSourceRotatable(final BoardElementView bev) {
        bev.getRotateVisualization().setOnMousePressed(getOnMousePressed(bev));
        bev.getRotateVisualization().setOnMouseReleased(getOnMouseReleased(bev));
        bev.getRotateVisualization().setOnMouseDragged(getOnMouseDragged(bev));
    }

    /**
     * Keep the starting point of the action.
     * 
     * @param bev
     *            the BoardElementView that needs this specific EventHandler.
     * @return the specific EventHandler for the method setOnMousePressed.
     */
    protected abstract EventHandler<MouseEvent> getOnMousePressed(BoardElementView bev);

    /**
     * Changes the cursor when rotation operation is finished.
     * 
     * @param bev
     *            the BoardElementView that needs this specific EventHandler.
     * @return the specific EventHandler for the method setOnMousePressed.
     */
    protected abstract EventHandler<MouseEvent> getOnMouseReleased(BoardElementView bev);

    /**
     * Performs the rotation.
     * 
     * @param bev
     *            the BoardElementView that needs this specific EventHandler.
     * @return the specific EventHandler for the method setOnMousePressed.
     */
    protected abstract EventHandler<MouseEvent> getOnMouseDragged(BoardElementView bev);
}
