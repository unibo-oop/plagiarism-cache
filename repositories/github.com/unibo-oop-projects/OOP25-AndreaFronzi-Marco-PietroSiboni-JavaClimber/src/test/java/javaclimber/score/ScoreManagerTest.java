package javaclimber.score;

import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.score.impl.ScoreManagerImpl;
import javaclimber.TestCostants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Set;

/**
 * Tests for the {@link ScoreManagerImpl} class.
 */
class ScoreManagerTest {

    private static final double START_Y = 600.0;
    private static final double PLAYER_Y_450 = 450.0;
    private static final double PLAYER_Y_100 = 100.0;
    private static final double PLAYER_Y_300 = 300.0;
    private static final double PLAYER_Y_400 = 400.0;
    private static final double PLAYER_Y_200 = 200.0;
    private static final int SCORE_100 = 100;
    private static final int SCORE_150 = 150;
    private static final int SCORE_200 = 200;
    private static final int SCORE_300 = 300;
    private static final int SCORE_350 = 350;
    private static final int SCORE_500 = 500;
    private static final double DELTA_100 = 100.0;

    private ScoreManagerImpl scoreManager;

    /**
     * Sets up a new ScoreManager instance before each test.
     */
    @BeforeEach
    void setUp() {
        scoreManager = new ScoreManagerImpl();
    }

    /**
     * Tests initial values of the ScoreManager and loads from the file.
     */
    @Test
    void testInitialValuesAndLoad() {
        final SaveState state = new SaveState(TestCostants.COINS_1500, TestCostants.HIGHEST_SCORE_5000, Set.of(), Map.of(),
                "skin_basic", 3, 1);
        scoreManager.loadState(state);

        assertEquals(0, scoreManager.getCurrentScore());
        assertEquals(0, scoreManager.getCoins());
        assertEquals(TestCostants.HIGHEST_SCORE_5000, scoreManager.getHighScore());
    }

    /**
     * Tests the {@link ScoreManager#updateScore(double)} method without camera
     * movement.
     * Score = max(0, |startY - playerY|)
     */
    @Test
    void testUpdateScoreStaticCamera() {
        scoreManager.setStartY(START_Y);

        scoreManager.updateScore(PLAYER_Y_450);
        assertEquals(SCORE_150, scoreManager.getCurrentScore());

        scoreManager.updateScore(PLAYER_Y_100);
        assertEquals(SCORE_500, scoreManager.getCurrentScore());

        scoreManager.updateScore(PLAYER_Y_300);
        assertEquals(SCORE_500, scoreManager.getCurrentScore());
    }

    /**
     * Tests the {@link ScoreManager#updateScore(double)} method with camera
     * movement.
     * Score = max(0, |startY - (playerY - totalCameraDelta)|)
     */
    @Test
    void testUpdateScoreWithCameraDelta() {
        scoreManager.setStartY(START_Y);
        scoreManager.updateScore(PLAYER_Y_300);
        assertEquals(SCORE_300, scoreManager.getCurrentScore());
        scoreManager.update(DELTA_100);
        scoreManager.updateScore(PLAYER_Y_400);
        assertEquals(SCORE_300, scoreManager.getCurrentScore());
        scoreManager.updateScore(PLAYER_Y_200);
        assertEquals(SCORE_500, scoreManager.getCurrentScore());
    }

    /**
     * Tests the {@link ScoreManager#addCoins(int)} and
     * {@link ScoreManager#getCoins()} methods.
     */
    @Test
    void testCoinsLogic() {
        scoreManager.addCoins(TestCostants.PRICE_100);
        assertEquals(TestCostants.PRICE_100, scoreManager.getCoins());
        scoreManager.addCoins(TestCostants.PRICE_200);
        assertEquals(TestCostants.PRICE_300, scoreManager.getCoins());
        scoreManager.addCoins(TestCostants.MINUS_PRICE_50);
        assertEquals(TestCostants.PRICE_300, scoreManager.getCoins());
    }

    /**
     * Tests the {@link ScoreManager#getHighScore()} method.
     */
    @Test
    void testHighScoreLogic() {
        scoreManager.loadState(new SaveState(0, SCORE_100, Set.of(), Map.of(), "", 4, 2));
        scoreManager.setStartY(START_Y);

        scoreManager.updateScore(PLAYER_Y_400);
        assertEquals(SCORE_200, scoreManager.getHighScore());
        scoreManager.update(TestCostants.TOTAL_DELTA_50);
        scoreManager.updateScore(PLAYER_Y_300);
        assertEquals(SCORE_350, scoreManager.getHighScore());
    }
}
