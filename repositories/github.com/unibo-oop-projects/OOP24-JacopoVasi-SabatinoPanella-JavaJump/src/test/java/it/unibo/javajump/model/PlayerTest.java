package it.unibo.javajump.model;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.entities.character.states.InAirState;
import it.unibo.javajump.model.states.ingame.InGameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.javajump.utility.TestConstants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.TestConstants.SCREEN_WIDTH;
import static it.unibo.javajump.utility.TestConstants.COUNTER_START;
import static it.unibo.javajump.utility.TestConstants.DELTA_TIME;
import static it.unibo.javajump.utility.TestConstants.MAX_COUNT_JUMPING;
import static it.unibo.javajump.utility.TestConstants.MAX_COUNT_PACMAN;
import static it.unibo.javajump.utility.TestConstants.MAX_COUNT_PHYSICS;
import static it.unibo.javajump.utility.TestConstants.X_LEFT_SIDE_SCREEN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The Player test.
 */
class PlayerTest {
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
     * Tests the correct player jumping.
     */
    @Test
    void testJumping() {
        int counter = COUNTER_START;
        while (!model.getPlayer().isOnPlatform() && counter < MAX_COUNT_JUMPING) {
            model.update(DELTA_TIME);
            counter++;
        }
        assertTrue(model.getPlayer().isOnPlatform(), "The player is not on the platform");
        model.update(DELTA_TIME);
        assertFalse(model.getPlayer().isOnPlatform(), "Jumping failed");
    }

    /**
     * Tests the pacman-effect.
     */
    @Test
    void testPacman() {
        int counter = COUNTER_START;
        model.getPlayer().setX(X_LEFT_SIDE_SCREEN);
        model.getPlayer().changeState(new InAirState());
        while (model.getPlayer().getX() < SCREEN_WIDTH && counter < MAX_COUNT_PACMAN) {
            model.handleAction(GameAction.MOVE_LEFT);
            model.update(DELTA_TIME);
            counter++;
        }
        assertTrue(model.getPlayer().getX() >= SCREEN_WIDTH, "The player is not looping");
    }

    /**
     * Tests if physics are handled correctly.
     */
    @Test
    void testPhysics() {
        int counter = COUNTER_START;
        final int maxcount = MAX_COUNT_PHYSICS;
        model.getPlayer().changeState(new InAirState());
        while (counter < maxcount) {
            model.handleAction(GameAction.MOVE_LEFT);
            model.update(DELTA_TIME);
            counter++;
        }
        final float tempx = model.getPlayer().getX();
        model.handleAction(GameAction.MOVE_RIGHT);
        model.update(DELTA_TIME);
        assertTrue(model.getPlayer().getX() < tempx, "The player is not decelerating");
        counter = COUNTER_START;
        while (counter < maxcount + maxcount) {
            model.handleAction(GameAction.MOVE_RIGHT);
            model.update(DELTA_TIME);
            counter++;
        }
        assertTrue(model.getPlayer().getX() > tempx, "The player is not accelerating  ");
    }


}
