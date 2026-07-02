package it.unibo.the100dayswar.commons.utilities.api;

import java.io.Serializable;

/**
 * Interface that rapresent the postion of an object in the game map.
 */
public interface Position extends Serializable {
    /**
     * X getter.
     * @return the value of x
     */
    int getX();

    /**
     * Y getter.
     * @return the value of y
     */
    int getY();

    /**
     * Update x.
     * @param x is the new value of x
     */
    void setX(int x);

    /**
     * Update y.
     * @param y is the new value of y
     */
    void setY(int y);

    /**
     * Add a postion's components to the current postion.
     * @param position the component that must be added to the current position
     */
    void set(Position position);
}
