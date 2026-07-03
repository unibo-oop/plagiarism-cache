package model;


import utility.Direction;


/**
 * @author Paolo
 *
 */
public interface Bar extends Element {

    /**
     * @return the actual bar speed
     */
    int getSpeed();
    /**
     * @param p **the new speed of this bar**
     */
    void setSpeed(int p);
    /**
     * @param d **the direction which the bar have to move**
     */
    void move(Direction d);
}
