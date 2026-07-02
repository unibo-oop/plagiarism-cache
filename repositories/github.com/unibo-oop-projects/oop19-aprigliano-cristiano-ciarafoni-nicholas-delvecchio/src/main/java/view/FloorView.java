package view;

import javafx.scene.shape.Rectangle;

/**
 * This is the interface that models the View of the floor.
 */
public interface FloorView {

    /**
     * Set the Rectangle's width.
     * @param width
     */
    void setWidth(double width);

    /**
     * Set the Rectangle's height.
     * @param height
     */
    void setHeight(double height);

    /**
     * Set the Rectangle's image.
     * @param img
     */
    void setImg(String img);

    /**
     *
     * @return the Rectangle that contains the floor
     */
    Rectangle getFloor();

    /**
     * Set the Rectangle's position in the field.
     * @param x x coordinate
     * @param y y coordinate
     */
    void setPosition(double x, double y);
}
