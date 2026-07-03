package oop.lit.view.controller.resize;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oop.lit.view.BoardElementView;

/**
 * Partial implementation of the logic behind resize.
 */
public abstract class AbstractResize {

    /**
     * @param bev
     *            the BoardElementView to be resized.
     */
    public void makeSourceResizable(final BoardElementView bev) {
        bev.getSelectionVisualization().setOnMousePressed(getOnMousePressed());
        bev.getSelectionVisualization().setOnMouseDragged(getOnMouseDragged());
        bev.getSelectionVisualization().setOnMouseMoved(getOnMouseMoved(bev));
        bev.getSelectionVisualization().setOnMouseReleased(getOnMouseReleased());
    }

    /**
     * Start.
     * 
     * @return the specific EventHandler for the method setOnMousePressed.
     */
    protected abstract EventHandler<MouseEvent> getOnMousePressed();

    /**
     * Main method when resize operation is performed.
     * 
     * @return the specific EventHandler for the method setOnMouseDragged.
     */
    protected abstract EventHandler<MouseEvent> getOnMouseDragged();

    /**
     * Cursor setter.
     * 
     * @param bev
     *            the BoardElementView to be resized.
     * @return the specific EventHandler for the method setOnMouseMoved.
     */
    protected abstract EventHandler<MouseEvent> getOnMouseMoved(BoardElementView bev);

    /**
     * End.
     * 
     * @return the specific EventHandler for the method setOnMouseReleased.
     */
    protected abstract EventHandler<MouseEvent> getOnMouseReleased();
}
