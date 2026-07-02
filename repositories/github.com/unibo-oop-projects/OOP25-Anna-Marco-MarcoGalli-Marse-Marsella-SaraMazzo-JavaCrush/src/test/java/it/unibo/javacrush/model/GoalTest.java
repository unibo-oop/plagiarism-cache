package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.model.api.Goal;
import it.unibo.javacrush.model.api.GoalFactory;
import it.unibo.javacrush.model.impl.GoalFactoryImpl;

/**
 * Test for {@link Goal}.
 */
class GoalTest {

    private static final int INITIAL_TARGET = 10;
    private static final int PROGRESS_TARGET = 6;
    private static final CellType TYPE = CellType.COFFEE_BEAN;
    private Goal goal;

    @BeforeEach
    void initialize() {
        final GoalFactory factory = new GoalFactoryImpl();
        this.goal = factory.createGoal(TYPE, INITIAL_TARGET);
    }

    /**
     * Test that the goal correctly returns its target cell type.
     */
    @Test
    void testGetTargetType() {
        assertNotNull(this.goal.getTargetType());
        assertNotEquals(CellType.CUP, this.goal.getTargetType());
        assertEquals(TYPE, this.goal.getTargetType());
    }

    /**
     * Test that the goal correctly returns its target amount.
     */
    @Test
    void testGetTargetAmount() {
        assertTrue(
            this.goal.getTargetAmount() > 0,
            "We cannot have a negative number as a target"
            );
        assertEquals(INITIAL_TARGET, this.goal.getTargetAmount());
    }

    /**
     * Test that adding progress correctly increments the current amount.
     */
    @Test
    void testAddProgress() {
        this.goal.addProgress(PROGRESS_TARGET);

        assertNotEquals(this.goal.getCurrentAmount(), 0);
        assertEquals(PROGRESS_TARGET, this.goal.getCurrentAmount());
    }

    /**
     * Test that adding negative progress throws an exception.
     */
    @Test
    void testAddNegativeProgress() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.goal.addProgress(-1),
            "We cannot add a negative progress"
        );
    }

    /**
     * Test that adding more progress than the target limits the current amount
     * and marks the goal as reached.
     */
    @Test
    void testAddOverflowProgress() {
        this.goal.addProgress(INITIAL_TARGET + PROGRESS_TARGET);

        assertEquals(INITIAL_TARGET, this.goal.getCurrentAmount());
        assertTrue(this.goal.isReached());
    }

    /**
     * Test that the goal is not reached when it is first created.
     */
    @Test
    void testIsReachedInitialState() {
        assertEquals(0, this.goal.getCurrentAmount());
        assertFalse(this.goal.isReached());
    }

    /**
     * Test that the goal is reached when the target is met.
     */
    @Test
    void testIsReachedWon() {
        this.goal.addProgress(INITIAL_TARGET);

        assertTrue(this.goal.isReached());
    }

}
