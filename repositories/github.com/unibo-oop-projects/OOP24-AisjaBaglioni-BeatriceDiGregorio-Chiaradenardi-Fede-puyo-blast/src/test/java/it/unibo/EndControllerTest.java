package it.unibo;

import it.unibo.controller.EndController;
import it.unibo.model.Grid;
import it.unibo.model.ScoreModel;
import it.unibo.model.StatusModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link EndController} class.
 */
class EndControllerTest {
    private Grid grid;
    private ScoreModel score;
    private StatusModel gameStatus;
    private EndController endController;

    /**
     * Initializes the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        grid = mock(Grid.class);
        score = mock(ScoreModel.class);
        gameStatus = mock(StatusModel.class);
        endController = new EndController(grid, score, gameStatus);
    }

    /**
     * Test: the game should end with 3 stars when the score reaches or exceeds 500.
     */
    @Test
    void testEndGameWithThreeStars() {
        when(score.getScore()).thenReturn(500);
        endController.onTick();
        verify(gameStatus).setStars(3);
        verify(score).setScore(500);
        verify(gameStatus).setGameEnded();
    }

    /**
     * Test: the game should award 2 stars when the score reaches or exceeds 350.
     */
    @Test
    void testAwardTwoStars() {
        when(score.getScore()).thenReturn(350);
        endController.onTick();
        verify(gameStatus).setStars(2);
        verify(gameStatus, never()).setGameEnded();
    }

    /**
     * Test: the game should award 1 star when the score reaches or exceeds 200.
     */
    @Test
    void testAwardOneStar() {
        when(score.getScore()).thenReturn(200);
        endController.onTick();
        verify(gameStatus).setStars(1);
        verify(gameStatus, never()).setGameEnded();
    }

    /**
     * Test: the game should end when the grid is full.
     */
    @Test
    void testEndGameWhenGridIsFull() {
        when(grid.isGridFull()).thenReturn(true);
        endController.onTick();
        verify(gameStatus).setGameEnded();
    }

    /**
     * Test: the game should not end or assign stars when the score is below 200.
     */
    @Test
    void testNoStarsAndNoEndWhenScoreIsLow() {
        when(score.getScore()).thenReturn(100);
        when(grid.isGridFull()).thenReturn(false);
        endController.onTick();
        verify(gameStatus, never()).setStars(anyInt());
        verify(gameStatus, never()).setGameEnded();
    }
}
