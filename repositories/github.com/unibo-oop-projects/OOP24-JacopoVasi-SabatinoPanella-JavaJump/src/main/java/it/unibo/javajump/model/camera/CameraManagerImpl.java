package it.unibo.javajump.model.camera;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.character.Character;

import static it.unibo.javajump.utility.Constants.HEIGHT_DIV;
import static it.unibo.javajump.utility.Constants.OFFSET_INIT;
import static it.unibo.javajump.utility.Constants.WIDTH_DIV;

/**
 * Implementation of the CameraManager interface.
 */
public class CameraManagerImpl implements CameraManager {
    /**
     * Field used to store the current offset of the camera during gameplay.
     */
    private float currentOffset;
    /**
     * Field used to store the previous offset reached by the camera during gameplay.
     */
    private float previousOffset;

    /**
     * Field to determine by how much should the points increase per pixel "scrolled".
     */
    private final float scoreFactor;

    /**
     * Constructor for the effective camera manager.
     *  the current score manager
     * @param scoreFactor  a desired game design value to multiply the score, for better game feel
     */
    public CameraManagerImpl(final float scoreFactor) {
        this.scoreFactor = scoreFactor;
        this.currentOffset = OFFSET_INIT;
        this.previousOffset = OFFSET_INIT;
    }

    /**
     * Updates the camera position and increments the score based on player
     * movement.
     *
     * @param model     the game model
     * @param deltaTime the time passed from the last update (in seconds)
     */
    @Override
    public void updateCamera(final GameModel model, final float deltaTime) {
        final Character player = model.getPlayer();
        final float screenHeight = model.getScreenHeight();
        final float desiredOffset = getDesiredOffset(screenHeight, player);

        if (currentOffset < previousOffset) {
            final float deltaOffset = previousOffset - currentOffset;
            final int points = (int) (deltaOffset * scoreFactor);
            model.getScoreManager().addPoints(points);
        }
        previousOffset = currentOffset;
        currentOffset = desiredOffset;
    }

    /**
     * Private method, calculates the desired offset based on the player's
     * position and the screen height. Uses a progressionScreenPoint to
     * determine the desired offset (based on the half of the screen height with
     * tolerance).
     *
     * @param screenHeight the height of the screen
     * @param player       the player character
     * @return the desired offset
     */
    private float getDesiredOffset(final float screenHeight, final Character player) {
        final float progressionScreenPoint = screenHeight / HEIGHT_DIV - screenHeight * WIDTH_DIV;
        float desiredOffset = currentOffset;

        if (player.getY() < progressionScreenPoint - currentOffset) {
            desiredOffset = player.getY() - progressionScreenPoint;
        }

        if (desiredOffset > currentOffset) {
            desiredOffset = currentOffset;
        }
        return desiredOffset;
    }

    /**
     * Resets the camera offset to the initial value.
     */
    @Override
    public void resetCamera() {
        this.currentOffset = OFFSET_INIT;
        this.previousOffset = OFFSET_INIT;
    }

    /**
     * Returns the current camera offset.
     *
     * @return the current camera offset
     */
    @Override
    public float getCurrentOffset() {
        return currentOffset;
    }
}
