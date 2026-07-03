package oop.lit.view.controller;

import oop.lit.view.controller.draganddrop.AbstractDragAndDrop;
import oop.lit.view.controller.resize.AbstractResize;
import oop.lit.view.controller.rotate.AbstractRotate;
import oop.lit.view.controller.selectionandaction.AbstractSelectionAndActions;

/**
 * ViewControllerManager is a bridge between View and Controller.
 */
public interface ViewControllerManager {

    /**
     * Getter for AbstractSelectionAndActions.
     * 
     * @return a AbstractSelectionAndActions.
     */
    AbstractSelectionAndActions getSelectionAndAction();

    /**
     * Getter for AbstractDragAndDrop.
     * 
     * @return a AbstractDragAndDrop.
     */
    AbstractDragAndDrop getDragAndDrop();

    /**
     * Getter for AbstractRotate.
     * 
     * @return a AbstractRotate.
     */
    AbstractRotate getRotate();

    /**
     * Getter for AbstractResize.
     * 
     * @return a AbstractResize.
     */
    AbstractResize getResize();
}
