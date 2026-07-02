package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.MovementComponent;
import it.unibo.utilities.Constants;

/**
 * Class to test the Movement Component.
 */
class MovementComponentTest {
    private static final double INITIAL_X = 300.0;
    private static final double INITIAL_Y = 200.0;
    private static final double MOVE_DISTANCE_X = 5.0;
    private static final double MOVE_DISTANCE_Y = 5.0;
    private static final double NEGATIVE_MOVE_X = -1000.0;
    private static final double NEGATIVE_MOVE_Y = -1000.0;
    private static final double MOVE_OUT_RIGHT_X = 2.0;
    private static final double MOVE_OUT_RIGHT_Y = 0.0;
    private static final double MOVE_OUT_LEFT_X = -1.0;
    private static final double MOVE_OUT_LEFT_Y = 0.0;
    private MovementComponent component;
    private Entity entity;

    /**
     * Set the variables.
     */
    @BeforeEach
    void setUp() {
        initializeMovementComponent();
    }

    /**
     * Initializes the MovementComponent and Entity instances.
     */
    private void initializeMovementComponent() {
        component = new MovementComponent();
        final EntityFactoryImpl entityFactoryImpl = new EntityFactoryImpl(new GamePerformanceImpl(null));
        entity = entityFactoryImpl.createRalph(new Pair<>(INITIAL_X, INITIAL_Y));
    }

    /**
     * Test moving the entity within bounds.
     */
    @Test
    void testMoveWithinBounds() {
        final double initialX = entity.getPosition().getX();
        final double initialY = entity.getPosition().getY();
        component.move(MOVE_DISTANCE_X, MOVE_DISTANCE_Y, entity);
        assertEquals(new Pair<>(initialX + MOVE_DISTANCE_X, initialY + MOVE_DISTANCE_Y), entity.getPosition());
    }

    /**
     * Test moving the entity out of the right wall bounds.
     */
    @Test
    void testMoveOutOfRightWall() {
        entity.setPosition(new Pair<>(Constants.GameEdges.RIGHT_WALL - 1, 0.0));
        component.move(MOVE_OUT_RIGHT_X, MOVE_OUT_RIGHT_Y, entity);
        assertEquals(new Pair<>(Constants.GameEdges.RIGHT_WALL - 1, 0.0), entity.getPosition());
    }

    /**
     * Test that the entity cannot move when given extreme negative values.
     */
    @Test
    void testCanMoveFalseWhenStopped() {
        final boolean canMove = component.canMove(NEGATIVE_MOVE_X, NEGATIVE_MOVE_Y, entity);
        assertFalse(canMove);
    }

    /**
     * Test that the entity can move within bounds.
     */
    @Test
    void testCanMoveTrueWithinBounds() {
        final boolean canMove = component.canMove(MOVE_DISTANCE_X, MOVE_DISTANCE_Y, entity);
        assertTrue(canMove);
    }

    /**
     * Test that the entity cannot move out of the left wall bounds.
     */
    @Test
    void testCanMoveFalseOutOfLeftWall() {
        entity.setPosition(new Pair<>(Constants.GameEdges.LEFT_WALL, 0.0));
        final boolean canMove = component.canMove(MOVE_OUT_LEFT_X, MOVE_OUT_LEFT_Y, entity);
        assertFalse(canMove);
    }
}
