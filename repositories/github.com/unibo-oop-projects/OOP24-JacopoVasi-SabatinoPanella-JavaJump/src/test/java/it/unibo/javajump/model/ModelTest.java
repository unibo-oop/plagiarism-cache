package it.unibo.javajump.model;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.menu.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.javajump.utility.TestConstants.DELTA_TIME;
import static it.unibo.javajump.utility.TestConstants.SCORE_POINTS;
import static it.unibo.javajump.utility.TestConstants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.TestConstants.SCREEN_WIDTH;
import static it.unibo.javajump.utility.TestConstants.STARTING_SCORE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The GameModel test.
 */
class ModelTest {

    private GameModelImpl gameModel;

    /**
     * Sets up the environment before each test.
     */
    @BeforeEach
    void setUp() {
        // Instantiate real implementations of the dependencies

        // Create the GameModelImpl instance
        gameModel = new GameModelImpl(
                SCREEN_WIDTH, SCREEN_HEIGHT
        );
    }

    /**
     * Tests that the initial state is menu.
     */
    @Test
    void testInitialStateIsMenu() {
        assertInstanceOf(MenuState.class, gameModel.getCurrentState(), "Initial state should be MenuState");
    }

    /**
     * Tests that the state changes correctly.
     */
    @Test
    void testSetStateChangesState() {
        final GameStateHandler newState = new MenuState();
        gameModel.setState(newState);
        assertEquals(newState, gameModel.getCurrentState(), "State should change correctly");
    }

    /**
     * Tests the correct handling of action.
     */
    @Test
    void testHandleAction() {
        final GameAction action = GameAction.CONFIRM_SELECTION; // Provide an empty implementation
        gameModel.handleAction(action);
        assertNotNull(gameModel.getCurrentState(), "Action should be handled without errors");
    }

    /**
     * Tests the correct update of model, according to delta time.
     */
    @Test
    void testUpdate() {
        final float deltaTime = DELTA_TIME;
        gameModel.update(deltaTime);
        assertEquals(deltaTime, gameModel.getDeltaTime(), "Delta time should be updated correctly");
    }

    /**
     * Tests that the starting of the game initializes entities correctlys.
     */
    @Test
    void testStartGameInitializesEntities() {
        gameModel.startGame();
        final Character player = gameModel.getPlayer();
        assertNotNull(player, "Player should be initialized");
        assertTrue(gameModel.getGameObjects().contains(player), "Player should be part of game objects");
        assertEquals(STARTING_SCORE, gameModel.getScore(), "Score should reset to zero");
    }

    /**
     * Tests the correct sending of observer notifications.
     */
    @Test
    void testObserverNotification() {
        final TestObserver observer = new TestObserver();
        gameModel.addObserver(observer);
        gameModel.notifyObservers();
        assertTrue(observer.updated, "Observer should be notified");
    }

    /**
     * Tests the score management.
     */
    @Test
    void testScoreManagement() {
        gameModel.addPointsToScore(SCORE_POINTS);
        assertEquals(SCORE_POINTS, gameModel.getScore(), "Score should increase correctly");
    }


    /**
     * The type Test observer.
     */
// Inner class to test observer pattern
    static final class TestObserver implements GameModelObserver {
        private boolean updated;

        @Override
        public void onModelUpdate(final GameModel model) {
            updated = true;
        }
    }
}
