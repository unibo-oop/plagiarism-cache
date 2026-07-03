package model;

import java.awt.Point;

/**
 * @author Missi
 *
 */
public interface Ball extends Element {

    /**
     * @author Missi
     *
     */
    enum Combo {
        STRONG,
        FAST,
        ZIGZAG,
        INCREMENTAL,
        NULL;
    }
    /**
     * @return the actual speed of this ball
     */
    Point getSpeed();
    /**
     * @param p **the speed this ball should move expressed with a point**
     */
    void setSpeed(Point p);
    /**
     * @return **the combo type affecting this ball**
     */
    Combo getCombo();
    /**
     * @param combo **the combo this ball get**
     */
    void setCombo(Combo combo);
    /**
     * @param b **is the visibility of ball**
     */
    void setVisible(boolean b);
    /**
     * @return **true if the ball is visible, false otherwise**
     */
    boolean isVisible();
    /**
     * move this ball of the actual speed.
     */
    void move();
}
