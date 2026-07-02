package it.unibo;

import it.unibo.model.ScoreModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link ScoreModel} class.
 */
class ScoreModelTest {
    private ScoreModel scoreModel;

    /**
     * Initializes the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        scoreModel = new ScoreModel();
    }

    /**
     * Tests if the initial score is 0.
     */
    @Test
    void testInitialScoreIsZero() {
        assertEquals(0, scoreModel.getScore(), "The initial score should be 0.");
    }

    /**
     * Tests if the score is updated correctly.
     */
    @Test
    void testSetScore() {
        scoreModel.setScore(10);
        assertEquals(10, scoreModel.getScore(), "The score should be updated to 10.");
        
        scoreModel.setScore(50);
        assertEquals(50, scoreModel.getScore(), "The score should be updated to 50.");
    }
}
