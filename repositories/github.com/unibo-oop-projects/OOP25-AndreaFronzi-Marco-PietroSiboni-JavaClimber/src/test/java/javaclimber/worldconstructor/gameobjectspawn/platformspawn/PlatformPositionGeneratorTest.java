package javaclimber.worldconstructor.gameobjectspawn.platformspawn;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.Distance;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformPositionGeneratorImpl;

/**
 * Test for the {@link PlatformPositionGeneratorImpl}.
 */
class PlatformPositionGeneratorTest {

    private static final double MAX_X = 400;
    private static final double MIN_X = 0;

    private static final double MAX_Y = 800;
    private static final double MIN_Y = 0;

    private static final double POS_X = 200;
    private static final double POS_Y = 780;

    private static final double PLATFORM_WIDTH = 50;
    private static final double PLATFORM_HEIGHT = 10;

    private static final double MAX_DISTANCE_Y = 100;
    private static final double MIN_DISTANCE_Y = 50;
    private static final double MAX_DISTANCE_X = 50;

    /**
     * The PlatformPositionGeneratorImpl instance to be tested.
     */
    private PlatformPositionGeneratorImpl platformPositionGenerator;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUp() {
        final var pos = new Vector2dImpl(POS_X, POS_Y);
        final var bound = new BoundWorldImpl(new BoundY(MIN_Y, MAX_Y), new Boundary(MIN_X, MAX_X));
        final var distance = new Distance(MAX_DISTANCE_Y, MIN_DISTANCE_Y, MAX_DISTANCE_X);
        this.platformPositionGenerator = new PlatformPositionGeneratorImpl(bound, pos, distance);
    }

    /**
     * Test for generating a platform position.
     */
    @Test
    void testGeneratePosition() {
        final var newPos = platformPositionGenerator.generatePosition(PLATFORM_WIDTH, PLATFORM_HEIGHT,
                new Vector2dImpl(POS_X, POS_Y));
        assertTrue(newPos.getX() >= MIN_X && newPos.getX() <= MAX_X);
        assertTrue(newPos.getY() >= MIN_Y && newPos.getY() <= MAX_Y);
        assertTrue(newPos.getX() >= POS_X - MAX_DISTANCE_X);
        assertTrue(newPos.getX() <= POS_X + MAX_DISTANCE_X);
        assertTrue(newPos.getY() >= POS_Y - MAX_DISTANCE_Y);
        assertTrue(newPos.getY() <= POS_Y - MIN_DISTANCE_Y);
    }

}
