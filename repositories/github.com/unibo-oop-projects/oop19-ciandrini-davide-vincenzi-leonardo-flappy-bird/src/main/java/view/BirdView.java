package view;

import javafx.scene.shape.Rectangle;

/**
 * Bird's View interface.
 */
public interface BirdView {
    /**
     * Set rectangle's position.
     * @param x coordinate X
     * @param y coordinate Y
     */
    void setPosition(double x, double y);

    /**
     * Set rectangle's size.
     * @param height rectangle's height
     * @param width rectangle's width
     */
    void setWidthHeight(int height, int width);

    /**
     * Set rectangle's image.
     * @param image bird image
     */
    void setImage(String image);

    /**
     * Update rectangle's position.
     * @param y coordinate Y
     */
    void updatePosition(double y);

    /**
     * Get rectangle.
     * @return rectangle defined in constructor
     */
    Rectangle getBird();



}
