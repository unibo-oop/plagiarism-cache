package javaclimber.worldconstructor.gameobjectspawn.platformspawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Director;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.DirectorImpl;

/**
 * Test for the {@link Director}.
 */
class DirectorTest {

    private static final double X = 50;
    private static final double Y = 50;

    private static final double WIDTH = 100;
    private static final double HEIGHT = 20;

    private static final double DELTA = 0.01;

    private static final double X0 = 0;
    private static final double X1 = 100;

    /**
     * The DirectorImpl instance to test.
     */
    private Director director;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUp() {
        this.director = new DirectorImpl(WIDTH, HEIGHT);
    }

    /**
     * Test for creating a normal platform.
     */
    @Test
    void testNormalPlatformCreation() {
        final Platform platform = director.normalPlatform(new Vector2dImpl(X, Y));
        assertEquals(X, platform.getPosX());
        assertEquals(Y, platform.getPosY());
    }

    /**
     * Test for creating a moving on-touch platform.
     */
    @Test
    void testMovingOnTouchPlatform() {
        final Platform platform = director.movingOnTouchPlatform(new Vector2dImpl(X, Y));
        assertEquals(X, platform.getPosX());
        assertEquals(Y, platform.getPosY());
    }

    /**
     * Test for creating a moving platform.
     */
    @Test
    void testMovingPlatform() {
        final Platform platform = director.movingPlatform(new Vector2dImpl(X, Y));
        platform.updatePosition(DELTA, new Boundary(X0, X1));
    }

}
