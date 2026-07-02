package it.unibo.model.collision;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import it.unibo.commons.Constants;
import it.unibo.controller.GameControllerImpl;
import it.unibo.model.GameModel;
import it.unibo.model.GameModelImpl;
import it.unibo.model.collision.CollisionChecker.CollisionState;

/**
 * JUnit test class for testing collision-related functionality in the game.
 * This class includes tests for MeatBoy's collision with obstacles and falling scenarios.
 */
class TestCollision {

    private GameModel gameModel;
    private CollisionChecker collisionChecker;

    private static final int SAW_X = (int) (300 * Constants.SCALE_PROPORTION);
    private static final int SAW_Y = (int) (35 * Constants.SCALE_PROPORTION);
    private static final int BANDAGE_X = (int) (654 * Constants.SCALE_PROPORTION);
    private static final int BANDAGE_Y = (int) (386 * Constants.SCALE_PROPORTION);
    private static final int FALLING = 2000;

    /**
     * Initializes the game model and collision components before each test.
     */
    @BeforeEach
    void init() {
        this.gameModel = new GameModelImpl(Constants.SOURCE_MAP, new GameControllerImpl());
        assertNotNull(this.gameModel);
        final CollisionHandler collisionHandler = new CollisionHandlerImpl(this.gameModel);
        assertNotNull(collisionHandler);
        this.collisionChecker = new CollisionCheckerImpl(collisionHandler);
        assertNotNull(collisionChecker);
    }

    /**
     * Tests MeatBoy's collision with obstacles, including the ground, saws, and BandageGirl.
     */
    @Test
    void testMeatBoyAndObstacolsCollision() {
        assertEquals(collisionChecker.getState(), CollisionState.GROUND);
        assertEquals(
                List.of(
                        this.gameModel.getMeatBoy().getX(), 
                        this.gameModel.getMeatBoy().getY()), 
                List.of(
                        this.gameModel.getMeatBoyStartCoord().getX(), 
                        this.gameModel.getMeatBoyStartCoord().getY()));
        this.gameModel.getMeatBoy().setY(this.gameModel.getMeatBoy().getY() - 10);
        this.collisionChecker.isColliding();
        assertEquals(CollisionState.AIR, collisionChecker.getState());

        this.gameModel.getMeatBoy().setX(this.gameModel.getMeatBoy().getX() + SAW_X);
        this.gameModel.getMeatBoy().setY(this.gameModel.getMeatBoy().getY() - SAW_Y);
        this.collisionChecker.isColliding();
        assertEquals(CollisionState.SAW, collisionChecker.getState());

        this.gameModel.getMeatBoy().setX(BANDAGE_X);
        this.gameModel.getMeatBoy().setY(BANDAGE_Y);
        this.collisionChecker.isColliding();
        assertEquals(CollisionState.BANDAGE_GIRL, collisionChecker.getState());
    }

    /**
     * Tests MeatBoy falling scenario.
     */
    @Test
    void testMeatBoyFall() {
        this.gameModel.getMeatBoy().setY(FALLING);
        assertEquals(CollisionState.FALL, this.collisionChecker.inWindow());
    }
}
