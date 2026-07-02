package it.unibo;

import it.unibo.controller.ScoreController;
import it.unibo.model.ScoreModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


/**
 * Unit tests for the {@link ScoreController} class.
 */
class ScoreControllerTest {
    private ScoreModel scoreModel;
    private ScoreController scoreController;

    /**
     * Initializes the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        scoreModel = mock(ScoreModel.class);
        scoreController = new ScoreController(scoreModel);
    }

    /**
     * Test: adding points should correctly update the score.
     */
    @Test
    void testAddPoints() {
        when(scoreModel.getScore()).thenReturn(10);
        scoreController.addPoints(3);
        verify(scoreModel).setScore(19);
    }

    /**
     * Test: adding zero power should not change the score.
     */
    @Test
    void testAddZeroPoints() {
        when(scoreModel.getScore()).thenReturn(5);
        scoreController.addPoints(0);
        verify(scoreModel).setScore(5);
    }

    /**
     * Test: adding a power of 1 should add exactly 1 point.
     */
    @Test
    void testAddOnePoint() {
        when(scoreModel.getScore()).thenReturn(7);
        scoreController.addPoints(1);
        verify(scoreModel).setScore(8);
    }

    /**
     * Test: adding a large power should correctly apply the formula.
     */
    @Test
    void testAddLargePoints() {
        when(scoreModel.getScore()).thenReturn(50);
        scoreController.addPoints(10);
        verify(scoreModel).setScore(150);
    }
}
