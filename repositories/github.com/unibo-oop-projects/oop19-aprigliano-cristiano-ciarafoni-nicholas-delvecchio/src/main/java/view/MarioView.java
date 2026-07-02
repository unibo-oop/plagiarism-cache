package view;

import javafx.scene.shape.Rectangle;

/**
 * This is the interface that model the View of Mario.
 */
public interface MarioView {

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
     * @return the Rectangle that contains Mario
     */
    Rectangle getMario();

    /**
     * Set the Rectangle's position in the field.
     * @param x x coordinate
     * @param y y coordinate
     */
    void setPosition(double x, double y);

    /**
     * Update the position of the Rectangle.
     * @param y y coordinate
     */
    void updatePosition(double y);
}
