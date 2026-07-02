package it.unibo.javajump.model.camera;

import it.unibo.javajump.model.GameModel;

/**
 * Interface that defines the camera manager:
 * it manages the camera movement and score increase as the camera moves up.
 */
public interface CameraManager {
    /**
     * Updates the camera position and increments the score based on player movement.
     *
     * @param model     the GameModel to get the position of player and screen sizes
     * @param deltaTime time passed from the last update (in seconds)
     */
    void updateCamera(GameModel model, float deltaTime);

    /**
     * Resets the camera offset.
     */
    void resetCamera();

    /**
     * Returns the current camera offset.
     *
     * @return current camera offset
     */
    float getCurrentOffset();
}
