package it.unibo.the100dayswar.controller.movementcontroller.api;

/**
 * The interface of the movement controller that provides
 * the methods to control the movement of the soldiers.
 */
public interface MovementController {
    /** 
     * Move the selecetd soldier up.
     */
    void moveUp();

    /** 
     * Move the selecetd soldier down.
     */
    void moveDown();

    /** 
     * Move the selecetd soldier left.
     */
    void moveLeft();

    /** 
     * Move the selecetd soldier right.
     */
    void moveRight();
}
