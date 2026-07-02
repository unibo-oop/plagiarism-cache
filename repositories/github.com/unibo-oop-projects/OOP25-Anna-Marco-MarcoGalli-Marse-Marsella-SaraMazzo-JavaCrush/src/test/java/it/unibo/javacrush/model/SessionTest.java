package it.unibo.javacrush.model;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.model.api.Goal;
import it.unibo.javacrush.model.api.GoalFactory;
import it.unibo.javacrush.model.api.Session;
import it.unibo.javacrush.model.impl.GoalFactoryImpl;
import it.unibo.javacrush.model.impl.SessionImpl;

/**
 * Test for {@link Session}.
 */
class SessionTest {

    private static final int INITIAL_MOVES = 10;
    private static final int TARGET_GOAL = 5;
    private static final GoalFactory FACTORY = new GoalFactoryImpl();
    private Session session;

    @BeforeEach
    void initialize() {
        // Creation of a mock grid
        final Map<CellType, Integer> mockGoals = Map.of(
            CellType.COFFEE_BEAN, TARGET_GOAL,
            CellType.MILK, TARGET_GOAL * 2
        );

        session = new SessionImpl(INITIAL_MOVES, mockGoals, FACTORY);
    }

    /**
     * Test that the session returns the correct number of remaining moves.
     */
    @Test
    void testGetMovesLeft() {
        assertEquals(INITIAL_MOVES, this.session.getMovesLeft());
    }

    /**
     * Test the decreaseMoves method after one and multiple usage.
     */
    @Test
    void testMovesDecrease() {
        this.session.decreaseMoves();

        // Control after a single decrease
        assertTrue(this.session.getMovesLeft() > 0);
        assertEquals(INITIAL_MOVES - 1, this.session.getMovesLeft());

        // Control after multiple decreases
        for (int i = 0; i < INITIAL_MOVES - 1; i++) {
            this.session.decreaseMoves();
        }
        assertEquals(0, this.session.getMovesLeft());
    }

    /**
     * Test that moves decrease when already at zero calls an IllegalStateException.
     */
    @Test
    void testMovesDecreaseWhenAlreadyZero() {
        for (int i = 0; i < INITIAL_MOVES; i++) {
            this.session.decreaseMoves();
        }

        assertThrows(
            IllegalStateException.class, 
            this.session::decreaseMoves,
            "The game cannot have a negative number of moves"
        );
    }

    /**
     * Test getGoals method.
     */
    @Test
    void testGetGoals() {
        final List<Goal> goals = this.session.getGoals();

        assertNotNull(goals);
        assertThrows(
            UnsupportedOperationException.class,
            goals::clear,
            "The list should be unmodifiable"
        );
    }

    /**
     * Test getGameStatus method at the beginning of the session.
     */
    @Test
    void testGamePlaying() {
        assertEquals(GameState.PLAYING, this.session.getGameState());
    }

    /**
     * Test updateGoals method with existing CellType.
     */
    @Test
    void testUpdateGoalsWithExistingType() {
        // We find a goal used in the session
        final Goal targetGoal = this.session.getGoals().stream()
            .findFirst()
            .orElseThrow();

        assertEquals(0, targetGoal.getCurrentAmount());

        this.session.updateGoals(targetGoal.getTargetType(), 3);

        assertEquals(3, targetGoal.getCurrentAmount());
    }

    /**
     * Test updateGoals method without existing CellType.
     */
    @Test
    void testUpdateGoalsWithoutExistingType() {
        this.session.updateGoals(CellType.SUGAR, 3);

        // No goal should be updated
        this.session.getGoals().stream()
            .forEach(
                goal -> assertEquals(0, goal.getCurrentAmount())
            );
    }

    /**
     * Test getGameStatus method when the game is won.
     */
    @Test
    void testGameWon() {
        this.session.getGoals().forEach(goal -> {
            this.session.updateGoals(goal.getTargetType(), goal.getTargetAmount());
        });

        assertTrue(this.session.getMovesLeft() > 0);
        assertEquals(GameState.WON, this.session.getGameState());
    }

    /**
     * Test getGameStatus method when the game is lost.
     */
    @Test
    void testGameLost() {
        for (int i = 0; i < INITIAL_MOVES; i++) {
            this.session.decreaseMoves();
        }

        assertEquals(0, this.session.getMovesLeft());

        final boolean goalCompleted = this.session.getGoals().stream()
            .allMatch(goal -> goal != null && goal.isReached());

        assertFalse(goalCompleted);

        assertEquals(GameState.LOST, this.session.getGameState());
    }
}
