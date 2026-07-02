package spacesurvival.model.gameobject.moveable.movement;

import spacesurvival.model.gameobject.moveable.MoveableObject;

/**
 * Implements the moving logic based on the game object.
 */
public interface MovementLogic {

    /**
     * Move the object in the next point based on its velocity and direction.
     * 
     * @param moveableObject object to move
     */
    void move(MoveableObject moveableObject);

    /**
     * Starts the game object move.
     * It can be implemented to make possible change directions or other automatic stuffs.
     * 
     * @param moveableObject object which has to initiate moving
     */
    default void startMoving(final MoveableObject moveableObject) {
        moveableObject.setMoving(true);
    }

    /**
     * Stop the game object move.
     * It can be implemented to make more stuffs after stop moving.
     * 
     * @param moveableObject object which has to initiate moving
     */
    default void stopMoving(final MoveableObject moveableObject) {
        moveableObject.setMoving(false);
    }
}
