package oop.lit.view.controller;

import oop.lit.controller.Controller;
import oop.lit.view.controller.draganddrop.AbstractDragAndDrop;
import oop.lit.view.controller.draganddrop.DragAndDropImpl;
import oop.lit.view.controller.resize.AbstractResize;
import oop.lit.view.controller.resize.ResizeImpl;
import oop.lit.view.controller.rotate.AbstractRotate;
import oop.lit.view.controller.rotate.RotateImpl;
import oop.lit.view.controller.selectionandaction.AbstractSelectionAndActions;
import oop.lit.view.controller.selectionandaction.SelectionAndActionsImpl;

/**
 * Implementation of ViewControllerManager.
 */
public class ViewControllerManagerImpl implements ViewControllerManager {

    private final AbstractSelectionAndActions selectionAndAction;
    private final AbstractDragAndDrop dragAndDrop;
    private final AbstractRotate rotate;
    private final AbstractResize resize;

    /**
     * Constructor.
     * 
     * @param controller
     *            Controller.
     */
    public ViewControllerManagerImpl(final Controller controller) {
        final Status status = new Status();
        this.selectionAndAction = new SelectionAndActionsImpl(controller, status);
        this.dragAndDrop = new DragAndDropImpl(controller);
        this.rotate = new RotateImpl(controller, status);
        this.resize = new ResizeImpl(controller, status);
    }

    @Override
    public AbstractSelectionAndActions getSelectionAndAction() {
        return this.selectionAndAction;
    }

    @Override
    public AbstractDragAndDrop getDragAndDrop() {
        return this.dragAndDrop;
    }

    @Override
    public AbstractRotate getRotate() {
        return this.rotate;
    }

    @Override
    public AbstractResize getResize() {
        return this.resize;
    }

}
