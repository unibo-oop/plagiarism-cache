package vg.controller.entity.boss;

import vg.controller.gameBoard.GameBoardController;
import vg.model.entity.Entity;
import vg.utils.Shape;
import vg.utils.V2D;

/**
 * Interface that controls the behavior of a boss.
 */
public interface BossController extends Entity {
    /**
     * Get the speed of the boss.
     * @return the speed of the boss.
     */
    V2D getSpeed();
    /**
     * Returns the shape of this entity.
     * @return the shape of this entity
     * @see Shape
     */
    Shape getShape();
    /**
     * Set the speed of the boss.
     * @param v2dSpeed the speed of the boss.
     */
    void setSpeed(V2D v2dSpeed);
    /**
     * Set the position of the boss.
     * @param v2dPosition the position of the boss.
     */
    void setPosition(V2D v2dPosition);
    /**
     * Set the entity in the parent node.
     * @param gameAreaNode the list of node of the game area.
     */
    void setInParentNode(GameBoardController gameAreaNode);
    /**
     * Move the boss.
     */
    void move();
    /**
     * Save the speed of the boss.
     */
    void saveMySpeed();
    /**
     * Restore the speed of the boss.
     */
    void restoreMySpeed();
    /**
     * Update the next image of the animation.
     */
    void updateAnimation();
}
