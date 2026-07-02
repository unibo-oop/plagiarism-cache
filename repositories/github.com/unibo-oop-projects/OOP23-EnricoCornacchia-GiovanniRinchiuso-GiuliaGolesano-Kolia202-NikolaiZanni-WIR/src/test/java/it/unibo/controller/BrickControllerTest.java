package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.controller.impl.BrickController;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.GamePerformanceImpl;

/**
 * Test Class for the BrickController.
 */
class BrickControllerTest {
    private static final double INITIAL_X_POSITION = 300.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final double SECOND_LEVEL_Y_POSITION = 301.0;
    private static final double THIRD_LEVEL_Y_POSITION = 302.5;
    private static final double HIGH_X_POSITION = 10_000.0;
    private static final double HIGH_Y_POSITION = 300.0;
    private static final int CYCLE = 1_000;

    private BrickController brickController;
    private EntityFactory entityFactory;
    private GameController gameController;

    @BeforeEach
    void setUp() {
        this.initializeBrickController();
    }

    private void initializeBrickController() {
        this.gameController = new GameController();
        final GamePerformance gamePerformance = new GamePerformanceImpl(gameController);
        this.brickController = gameController.getBrickController();
        this.entityFactory = new EntityFactoryImpl(gamePerformance);
    }

    @Test
    void testGetBricks() {
        final Set<Entity> bricks = brickController.getBricks();
        assertEquals(0, bricks.size(), "The initial set of bricks should be empty");
    }

    @Test
    void testFallBricks() {
        final Entity brick = entityFactory.createBrick(new Pair<>(INITIAL_X_POSITION, INITIAL_Y_POSITION));
        this.brickController.addBrick(brick);
        this.brickController.update();
        assertEquals(INITIAL_X_POSITION, brick.getPosition().getX(), "The brick should not have moved horizontally");
        assertEquals(SECOND_LEVEL_Y_POSITION, brick.getPosition().getY(), "The brick should have moved vertically");
        this.gameController.setLevel(2);
        this.brickController.update();
        assertEquals(INITIAL_X_POSITION, brick.getPosition().getX(), "The brick should not have moved horizontally");
        assertEquals(THIRD_LEVEL_Y_POSITION, brick.getPosition().getY(), "The brick should have moved vertically");
    }

    @Test
    void testRemoveBrick() {
        final Entity brick = entityFactory.createBrick(new Pair<>(HIGH_X_POSITION, HIGH_Y_POSITION));
        this.brickController.addBrick(brick);
        assertEquals(1, this.brickController.getBricks().size(), "The set of bricks should contain one brick");
        for (int i = 0; i < CYCLE; i++) {
            this.brickController.update();
        }
        assertEquals(0, this.brickController.getBricks().size(), "The set of bricks should be empty");
    }
}
