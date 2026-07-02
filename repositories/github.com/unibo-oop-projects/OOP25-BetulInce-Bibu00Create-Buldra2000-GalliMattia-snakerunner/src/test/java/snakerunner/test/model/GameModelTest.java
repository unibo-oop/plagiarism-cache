package snakerunner.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import snakerunner.model.GameModel;
import snakerunner.model.impl.GameModelImpl;

class GameModelTest {

    private final GameModel gameModel = new GameModelImpl();

    /**
     * Test that eating food increases the score by a positive amount.
     */
    @Test
    void eatingFoodIncreasesScore() {
        final int scoreBefore = gameModel.getScore();

        gameModel.addScore(10); 

        assertTrue(gameModel.getScore() > scoreBefore);
    }

    /**
     * Test that hitting a clock slows down the game by increasing the speed value.
     */
    @Test
    void clockSlowsGame() {
        final int speedBefore = gameModel.getSpeed();

        gameModel.applySlowEffect(); 

        assertTrue(gameModel.getSpeed() > speedBefore);
    }

    /**
     * Test that resetting the game state correctly resets the score and game over status.
     */
    @Test
    void levelResetsCorrectly() {
        gameModel.resetState();

        assertEquals(0, gameModel.getScore());
        assertFalse(gameModel.isGameOver());
    }
}
