package javaclimber.gameobj;

import org.junit.jupiter.api.Test;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.gameobj.platformbuilder.api.PlatformBuilder;
import it.unibo.model.gameobj.platformbuilder.impl.PlatformBuilderImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.physics.platformphysic.api.MovementBehaviour;
import it.unibo.model.physics.platformphysic.impl.HorizontalMovementBehavior;
import it.unibo.model.world.impl.Boundary;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the {@link Platform} game object.
 */
class PlatformsTest {

    private static final double EPSILON = 0.001;

    private static final double X = 10;
    private static final double Y = 20;

    private static final double NEW_X = 20;

    private static final double WIDTH = 50;
    private static final double HEIGHT = 15;

    private static final double X0 = 0;
    private static final double X1 = 100;

    private static final double DS = 10;

    private static final double DT = 1;

    /**
     * Tests the {@link Platform} generation using {@link PlatformBuilder}.
     */
    @Test
    void generatePlatformsTest() {
        final Vector2d position = new Vector2dImpl(X, Y);
        final PlatformBuilder platformBuilder = new PlatformBuilderImpl();

        final Platform platform = platformBuilder
                .at(position)
                .size(WIDTH, HEIGHT)
                .build();

        assertEquals(WIDTH, platform.getWidth(), EPSILON);
        assertEquals(HEIGHT, platform.getHeight(), EPSILON);
        assertEquals(X, platform.getPosX(), EPSILON);
        assertEquals(Y, platform.getPosY(), EPSILON);
    }

    /**
     * Tests the {@link Platform#updatePosition(Vector2d)} method.
     */
    @Test
    void testUpdatePosition() {
        final Vector2d position = new Vector2dImpl(X, Y);
        final Boundary boundary = new Boundary(X0, X1);
        final MovementBehaviour movementBehaviour = new HorizontalMovementBehavior(DS);
        final PlatformBuilder platformBuilder = new PlatformBuilderImpl();

        final Platform platform = platformBuilder
                    .at(position)
                    .size(WIDTH, HEIGHT)
                    .addMovementBehaviour(movementBehaviour)
                    .build();

        platform.updatePosition(DT, boundary);

        assertEquals(NEW_X, platform.getPosX(), EPSILON);
        assertEquals(Y, platform.getPosY(), EPSILON);
    }

    /**
     * Tests the {@link Platform#updatePosition(Vector2d)} method without movement behaviour.
     */
    @Test
    void updatePositionWithoutMovementBehaviourTest() {
        final Vector2d position = new Vector2dImpl(X, Y);
        final Boundary boundary = new Boundary(X0, X1);

        final PlatformBuilder platformBuilder = new PlatformBuilderImpl();

        final Platform platform = platformBuilder
                    .at(position)
                    .size(WIDTH, HEIGHT)
                    .build();

        platform.updatePosition(DT, boundary);

        assertEquals(X, platform.getPosX(), EPSILON);
        assertEquals(Y, platform.getPosY(), EPSILON);
     }
}
