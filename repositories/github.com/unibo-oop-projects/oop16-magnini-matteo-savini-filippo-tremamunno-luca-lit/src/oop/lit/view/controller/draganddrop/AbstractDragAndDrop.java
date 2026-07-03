package oop.lit.view.controller.draganddrop;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import oop.lit.model.BoardModel;
import oop.lit.view.BoardElementView;
import oop.lit.view.BoardView;

/**
 * Partial implementation of the logic behind drag and drop.
 */
public abstract class AbstractDragAndDrop {

    /**
     * @param bev
     *            the BoardElementView that you want to be draggable and
     *            droppable.
     * @param boardModel
     *            BoardModel.
     */
    public void makeSourceDraggableAndDroppable(final BoardElementView bev, final BoardModel boardModel) {
        bev.getImageView().setOnDragDetected(getOnDragDetected(bev, boardModel));
        bev.getImageView().setOnDragDone(getOnDragDone(bev));
        bev.getImageView().setOnDragOver(getOnDragOver());
        bev.getImageView().setOnDragDropped(getOnDragDropped());
    }

    /**
     * @param bv
     *            the BoardView that you want to be suitable for drag and drop.
     */
    public void makeTargetFitDragAndDrop(final BoardView bv) {
        bv.getPane().setOnDragOver(getOnDragOver());
        bv.getPane().setOnDragDropped(getOnDragDropped());
    }

    /**
     * Drag and drop active on source.
     * 
     * @param bev
     *            the BoardElementView that needs this specific EventHandler.
     * @param boardModel
     *            BoardModel.
     * @return the specific EventHandler for the method setOnDragDetected.
     */
    protected abstract EventHandler<MouseEvent> getOnDragDetected(BoardElementView bev, BoardModel boardModel);

    /**
     * @param bev
     *            the BoardElementView that needs this specific EventHandler.
     * @return the specific EventHandler for the method setOnDragDone.
     */
    protected abstract EventHandler<DragEvent> getOnDragDone(BoardElementView bev);

    /**
     * Drag and drop active on target.
     * 
     * @return the specific EventHandler for the method setOnDragOver (target).
     */
    protected abstract EventHandler<DragEvent> getOnDragOver();

    /**
     * Drag and drop ended on target.
     * 
     * @return the specific EventHandler for the method setOnDragDropped.
     */
    protected abstract EventHandler<DragEvent> getOnDragDropped();
}
