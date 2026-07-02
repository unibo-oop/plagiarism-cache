package it.unibo.javajump.model;

import it.unibo.javajump.model.states.ingame.InGameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.javajump.utility.Constants.OFFSET_INIT;
import static it.unibo.javajump.utility.Constants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.Constants.SCREEN_WIDTH;
import static it.unibo.javajump.utility.TestConstants.CAMERA_DECREASING_OFFSET;
import static it.unibo.javajump.utility.TestConstants.CAMERA_INCREASING_OFFSET;
import static it.unibo.javajump.utility.TestConstants.DELTA_TIME;
import static it.unibo.javajump.utility.TestConstants.DIV_TO_CENTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The Camera test.
 */
class CameraTest {

    private GameModel model;

    /**
     * Sets up the environment before each test.
     */
    @BeforeEach
    void setUp() {

        model = new GameModelImpl(SCREEN_WIDTH, SCREEN_HEIGHT);
        model.startGame();
        model.setState(new InGameState());

    }

    /**
     * Tests initial offset.
     */
    @Test
    void testInitialOffset() {
        assertEquals(OFFSET_INIT, model.getCameraManager().getCurrentOffset(), "Initial offset should be equal to OFFSET_INIT.");
    }

    /**
     * Tests that updating camera increases score when moving up.
     */
    @Test
    void testUpdateCameraIncreasesScoreWhenMovingUp() {
        final float initialOffset = model.getCameraManager().getCurrentOffset();
        final float initialScore = model.getScoreManager().getCurrentScore();

        // Simulate player moving upwards
        model.getPlayer().setY(initialOffset - CAMERA_INCREASING_OFFSET);

        model.update(DELTA_TIME);
        model.getCameraManager().updateCamera(model, DELTA_TIME); // Assume a frame update of ~16ms

        assertTrue(model.getScoreManager().getCurrentScore() > initialScore, "Score should increase when the camera moves up.");
    }

    /**
     * Tests that updating camera does not decrease offset.
     */
    @Test
    void testUpdateCameraDoesNotDecreaseOffset() {
        final float initialOffset = model.getCameraManager().getCurrentOffset();


        model.getPlayer().setY((float) SCREEN_HEIGHT / DIV_TO_CENTER + CAMERA_DECREASING_OFFSET);
        model.update(DELTA_TIME);
        model.getCameraManager().updateCamera(model, DELTA_TIME);

        assertEquals(initialOffset, model.getCameraManager().getCurrentOffset(),
                "Camera offset should not increase when the player moves down.");
    }

    /**
     * Tests the correct resetting of camera.
     */
    @Test
    void testResetCamera() {
        model.getCameraManager().resetCamera();
        assertEquals(OFFSET_INIT, model.getCameraManager().getCurrentOffset(), "Camera offset should be reset to OFFSET_INIT.");
    }
}
