package it.unibo.plantsfarm.model.camera.api;

/**
 * Create the Model Camera.
 */
public interface Camera {

    /**
     * Updates the camera position by centering it on the player.
     * The camera position is clamped so that it does not move outside
     * the world boundaries, ensuring the player remains visible.
     *
     * @param playerPosX the X coordinate of the player's position in world space
     * @param playerPosY the Y coordinate of the player's position in world space
     */
    void followPlayer(int playerPosX, int playerPosY);

    /**
     * Returns the X coordinate of the camera.
     *
     * @return the camera X position in world space
     */
    int getCameraPosX();

    /**
     * Returns the Y coordinate of the camera.
     *
     * @return the camera Y position in world space
     */
    int getCameraPosY();

}
