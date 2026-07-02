package view;

import javafx.scene.shape.Rectangle;

/**
 * Interface for the view of an Obstacle.
 */
public interface ObstacleView {

    /**
     * Set obstacle position.
     * @param x coordinate X
     * @param y coordinate Y
     */
    void setObstaclePosition(double x, double y);

    /**
     * Set rectangle's dimensions.
     * @param width rectangle's width
     * @param height rectangle's height
     */
    void setObstacleDimension(double width, double height);

    /**
     * Set Obstacle Image.
     * @param img Obstacle Image
     */
    void setImg(String img);

    /**
     * Method to Get the Obstacle.
     * @return the Rectangle of the obstacle object
     */
    Rectangle getObstacle();

    /**
     * This method allows us to show the obstacle rectangles with a distance between each other.
     * @param x , the distance between obstacles
     */
    void updatePos(double x);
}
