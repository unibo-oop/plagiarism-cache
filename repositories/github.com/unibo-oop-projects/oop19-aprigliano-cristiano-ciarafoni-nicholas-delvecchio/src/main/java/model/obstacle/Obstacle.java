package model.obstacle;

/**
 * This interface is used to model the Obstacle object model.
 */
public interface Obstacle {

    /**
     * Method to Get the X Coordinate of the Obstacle.
     * @return x coordinate of the obstacle object
     */
    double getPosX();

    /**
     * Method to Get the Y Coordinate of the Obstacle.
     * @return y coordinate of the obstacle object
     */
    double getPosY();

    /**
     * Set the x coordinate of the obstacle object.
     * @param posX , the Position on the X axis
     */
    void setPosX(double posX);

    /**
     * Set the y coordinate of the obstacle object.
     * @param posY , the Position on the Y axis
     */
    void setPosY(double posY);

    /**
     * Method to Get the Height of the Obstacle.
     * @return the height of the obstacle object
     */
    double getHeight();

    /**
     * Method to Get the Width of the Obstacle.
     * @return the width of the obstacle object
     */
    double getWidth();

    /**
     * Method to Get the Path of the Image.
     * @return the image path of the obstacle object
     */
    String getImagePath();

    /**
     * Method to Get the state of the Boolean 'getPowerUp'.
     * @return true or false
     */
    boolean getPowerUp();

    /**
     * Method to Set the Power-Up to Mario.
     */
    void setPowerUp(boolean power);
}
