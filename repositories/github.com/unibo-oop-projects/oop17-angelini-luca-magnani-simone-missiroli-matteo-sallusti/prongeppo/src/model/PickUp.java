package model;

import view.PongElement;

/**
 * @author Missi
 *
 */
public interface PickUp extends Element {

    /**
     * @param ball **the ball that triggers the pickup effect**
     */
    void trigger(Ball ball);

    /**
     * @return the graphic of this pickup
     */
    PongElement getGraphic();
}
