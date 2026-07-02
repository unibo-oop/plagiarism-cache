package it.unibo.javajump.model.entities;

/**
 * Interface that represents a GameObject in the game.
 */
public interface GameObject {
    /**
     * Method that updates the GameObject based on the given deltaTime.
     *
     * @param deltaTime the time passed since the last update
     */
    void update(float deltaTime);

    /**
     * Method that checks if the GameObject is colliding with another GameObject.
     *
     * @param other the other GameObject
     */
    void onCollision(GameObject other);

    /**
     * Method that returns the x position of the GameObject.
     *
     * @return the x position of the GameObject
     */
    float getX();

    /**
     * Method that sets the x position of the GameObject.
     *
     * @param x the x position of the GameObject
     */
    void setX(float x);

    /**
     * Method that returns the y position of the GameObject.
     *
     * @return the y position of the GameObject
     */
    float getY();

    /**
     * Method that sets the y position of the GameObject.
     *
     * @param y the y position of the GameObject
     */
    void setY(float y);

    /**
     * Method that returns the width of the GameObject.
     *
     * @return the width of the GameObject
     */
    float getWidth();

    /**
     * Method that returns the height of the GameObject.
     *
     * @return the height of the GameObject
     */
    float getHeight();
}
