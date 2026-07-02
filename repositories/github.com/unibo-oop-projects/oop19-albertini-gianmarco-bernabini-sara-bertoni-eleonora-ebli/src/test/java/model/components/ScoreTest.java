package model.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.components.Score;
import model.components.ScoreImpl;

/**
 * 
 * Tests the functionality of class ScoreImpl.
 *
 */
public class ScoreTest {

    private static final int DEFAULT_POINTS = 100;
    private static final int WITH_COMBO = 1200;
    private static final int WITHOUT_COMBO = 200;
    private static final int DEFAULT_COMBO = 5;
    private Score score;

    /**
     * Creates a new object and sets some default points(100).
     */
    @BeforeEach
    public void before() {
        this.score = new ScoreImpl();
        score.setPoints(DEFAULT_POINTS);
    }

    /**
     * Tests the algorithm which computes the points to add to the general score.
     */
    @Test
    public void testSetPoints() {
        assertEquals(WITHOUT_COMBO, score.getGlobalScore());
    }

    /**
     * Tests the increment of the combo value.
     */
    @Test
    public void testCombo() {
        for (int i = 0; i < DEFAULT_COMBO; i++) {
            score.incCombo();
        }
        score.setPoints(DEFAULT_POINTS);
        assertNotEquals(WITHOUT_COMBO, score.getGlobalScore());
        assertEquals(WITHOUT_COMBO + WITH_COMBO, score.getGlobalScore());
        score.resetCombo();
        score.setPoints(DEFAULT_POINTS);
        assertEquals(WITHOUT_COMBO + WITH_COMBO + WITHOUT_COMBO, score.getGlobalScore());
        assertNotEquals(WITHOUT_COMBO + WITH_COMBO + WITH_COMBO, score.getGlobalScore());
    }

    /**
     * Tests the reset function of the combo and of the score.
     */
    @Test
    public void testReset() {
        assertEquals(WITHOUT_COMBO, score.getGlobalScore());
        score.reset();
        assertEquals(0, score.getGlobalScore());
        score.incCombo();
        score.setPoints(DEFAULT_POINTS);
        assertEquals(WITHOUT_COMBO + WITHOUT_COMBO, score.getGlobalScore());
    }
}
