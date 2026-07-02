package vg.model.entity.boss;

import javafx.geometry.Dimension2D;
import vg.model.entity.Entity;
import vg.utils.Shape;
import vg.utils.V2D;

/**
 * This interface represents the boss entities (model).
 */
public interface BossModel extends Entity {
    /**
     * Get the radius of the boss.
     * @return the radius of the boss.
     */
    int getRadius();
    /**
     * Get the dimension of the boss.
     * @return the dimension of the boss.
     */
    Dimension2D getDimension();
    /**
     * Get the speed of the boss.
     * @return the speed of the boss.
     */
    V2D getSpeed();
    /**
     * Get the shape of the boss.
     * @return the shape of the boss.
     */
    Shape getShape();
    /**
     * Set the position of the boss.
     * @param v2dPosition the position of the boss.
     */
    void setPosition(V2D v2dPosition);
    /**
     * Set the speed of the boss.
     * @param v2dSpeed the speed of the boss.
     */
    void setSpeed(V2D v2dSpeed);
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
}
