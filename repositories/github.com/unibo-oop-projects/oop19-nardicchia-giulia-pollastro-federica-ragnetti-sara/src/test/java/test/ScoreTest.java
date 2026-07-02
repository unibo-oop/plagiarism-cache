package test;

import model.DifficultyLevel;
import model.score.ScoreModel;
import model.score.ScoreModelImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Class to test if {@link ScoreModel} works correctly.
 */
public class ScoreTest {

    private ScoreModel point;

    /**
     * Test basic behavior with easy difficulty.
     */
    @Test
    public void testEasyBasicBehavior() {
        this.point = new ScoreModelImpl(DifficultyLevel.EASY, 5);
        this.point.addPoint(); // 10
        this.point.addPoint(); // 20
        this.point.addPoint(); // 30
        this.point.addPoint(); // 40
        this.point.addPoint(); // 50
        this.point.addPoint(); // 60
        assertEquals(60, this.point.getScore());
    }

    /**
     * Test basic behavior with normal difficulty.
     */
    @Test
    public void testNormalBasicBehavior() {
        this.point = new ScoreModelImpl(DifficultyLevel.NORMAL, 10);
        this.point.addPoint(); // 22
        this.point.addPoint(); // 44
        this.point.addPoint(); // 66
        this.point.addPoint(); // 88
        this.point.addPoint(); // 110
        this.point.addPoint(); // 132
        assertEquals(132, this.point.getScore());
    }

    /**
     * Test basic behavior with hard difficulty.
     */
    @Test
    public void testHardBasicBehavior() {
        this.point = new ScoreModelImpl(DifficultyLevel.HARD, 15);
        this.point.addPoint(); // 34
        this.point.addPoint(); // 68
        this.point.addPoint(); // 102
        this.point.addPoint(); // 136
        this.point.addPoint(); // 170
        this.point.addPoint(); // 204
        assertEquals(204, this.point.getScore());
    }

    /**
     * Test if throws {@link IllegalStateException}.
     */
    @Test
    public void testCheckFinishBehavior() {
        this.point = new ScoreModelImpl(DifficultyLevel.EASY, 5);
        this.point.addPoint(); // 10
        this.point.addPoint(); // 20
        assertEquals(20, this.point.getScore());
        assertThrows(IllegalStateException.class, () -> this.point.getScore());
    }
}
