package it.unibo.javajump.model;

import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;
import it.unibo.javajump.model.states.ingame.InGameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.unibo.javajump.utility.Constants.HARD_MAX;
import static it.unibo.javajump.utility.Constants.HELL_MAX;
import static it.unibo.javajump.utility.Constants.MEDIUM_MAX;
import static it.unibo.javajump.utility.Constants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.Constants.SCREEN_WIDTH;
import static it.unibo.javajump.utility.Constants.VERY_HARD_MAX;
import static it.unibo.javajump.utility.TestConstants.DELTA_TIME;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * The Score test.
 */
class DifficultyTest {
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
     * Tests the difficulty setting.
     */
    @Test
    void testDifficulty() {
        model.update(DELTA_TIME);
        assertEquals(DifficultyState.EASY, model.getDifficultyManager().getCurrentDifficulty(), "Difficulty should be EASY.");
        model.getScoreManager().addPoints(MEDIUM_MAX);
        model.update(DELTA_TIME);
        assertEquals(DifficultyState.MEDIUM, model.getDifficultyManager().getCurrentDifficulty(), "Difficulty should be MEDIUM");
        model.getScoreManager().addPoints(HARD_MAX - MEDIUM_MAX);
        model.update(DELTA_TIME);
        assertEquals(DifficultyState.HARD, model.getDifficultyManager().getCurrentDifficulty(), "Difficulty should be HARD");
        model.getScoreManager().addPoints(VERY_HARD_MAX - HARD_MAX);
        model.update(DELTA_TIME);
        assertEquals(DifficultyState.VERY_HARD,
                model.getDifficultyManager().getCurrentDifficulty(), "Difficulty should be VERY HARD");
        model.getScoreManager().addPoints(HELL_MAX - VERY_HARD_MAX);
        model.update(DELTA_TIME);
        assertEquals(DifficultyState.HELL, model.getDifficultyManager().getCurrentDifficulty(), "Difficulty should be HELL");

    }

}
