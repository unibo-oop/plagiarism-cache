package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.Component;
import ballblast.model.components.ComponentType;
import ballblast.model.components.GravityComponent;
import ballblast.model.gameobjects.BallType;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.gameobjects.GameObjectManagerImpl;
import ballblast.model.helpers.GameObjectHelper;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.SimpleCollisionManager;

/**
 * JUnit test for {@link GravityComponent}.
 */
public class TestGravity {

    private static final Coordinate POSITION = new Coordinate(100, 100);
    private static final Vector2D VELOCITY = new Vector2D(0, 0);
    private static final long ELAPSED = 10;

    private final GameObjectManager gameObjectManager = new GameObjectManagerImpl();
    private final CollisionManager collisionManager = new SimpleCollisionManager();
    private final GameObject ball = GameObjectHelper.createBall(BallType.LARGE, 1, POSITION, VELOCITY, this.collisionManager,
            this.gameObjectManager, null, null);
    private Vector2D gravity;

    /**
     * Initializes the needed objects.
     */
    @Before
    public void initializeEnv() {
        this.ball.getComponents().stream()
                                 .filter(c -> c.getType().equals(ComponentType.MOVEMENT) 
                                         || c.getType().equals(ComponentType.GRAVITY))
                                 .forEach(Component::enable);
        this.ball.getComponents().stream()
                                 .filter(c -> c.getType().equals(ComponentType.GRAVITY))
                                 .findFirst()
                                 .ifPresent(c -> this.gravity = ((GravityComponent) c).getGravity());
    }

    /**
     * Tests the {@link GravityComponent}.
     */
    @Test
    public void testGravity() {
        assertEquals(this.ball.getVelocity(), VELOCITY);
        assertEquals(this.ball.getPosition(), POSITION);
        this.ball.update(ELAPSED);
        assertEquals(this.ball.getVelocity(), VELOCITY.add(new Vector2D(this.gravity.multiply(ELAPSED))));
        assertEquals(this.ball.getPosition(), VELOCITY.multiply(ELAPSED).translate(this.ball.getPosition()));
    }

}
