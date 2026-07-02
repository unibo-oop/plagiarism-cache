package javawulf.model;

import java.awt.Point;

/**
 * Coordinate represents the current position of a GameElement in
 * both the x and y axis.
 */
public interface Coordinate {

    /**
     * @return The exact position of a GameElement
     */
    Point getPosition();

    /**
     * @param x The position on the x-axis of a GameElement
     * @param y The position on the y-axis of a GameElement
     */
    void setPosition(int x, int y);

    /**
     * @return The position on the x-axis of a GameElement
     */
    Integer getX();

    /**
     * @return The position on the y-axis of a GameElement
     */
    Integer getY();
}
