package it.unibo.javajump.model;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.states.gameover.GameOverState;
import it.unibo.javajump.model.states.ingame.InGameState;
import it.unibo.javajump.model.states.menu.MenuState;
import it.unibo.javajump.model.states.pause.PauseState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.javajump.utility.Constants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.Constants.SCREEN_WIDTH;
import static it.unibo.javajump.utility.TestConstants.DELTA_TIME;
import static it.unibo.javajump.utility.TestConstants.HEIGHT_OFF_MULTIPLIER;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Game state test.
 */
class GameStateTest {
    private GameModel model;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        model = new GameModelImpl(SCREEN_WIDTH, SCREEN_HEIGHT);
        model.startGame();


    }

    /**
     * Test states.
     */
    @Test
    void testStates() {
        model.setState(new MenuState());
        assertEquals(MenuState.class, model.getCurrentState().getClass(), "State should be equal to MenuState.");

        model.getCurrentState().handleAction(model, GameAction.CONFIRM_SELECTION);
        assertEquals(InGameState.class, model.getCurrentState().getClass(), "State should be equal to InGameState.");

        model.getCurrentState().handleAction(model, GameAction.PAUSE_GAME);
        assertEquals(PauseState.class, model.getCurrentState().getClass(), "State should be equal to PauseState.");

        model.getCurrentState().handleAction(model, GameAction.CONFIRM_SELECTION);
        assertEquals(InGameState.class, model.getCurrentState().getClass(), "State should be equal to InGameState.");

        model.getPlayer().setY(SCREEN_HEIGHT * HEIGHT_OFF_MULTIPLIER);
        model.update(DELTA_TIME);
        assertEquals(GameOverState.class, model.getCurrentState().getClass(), "State should be equal to GameOverState.");

        model.getCurrentState().handleAction(model, GameAction.CONFIRM_SELECTION);
        assertEquals(MenuState.class, model.getCurrentState().getClass(), "State should be equal to MenuState.");

    }


}
