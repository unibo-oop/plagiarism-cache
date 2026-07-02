package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.model.components.Component;
import ballblast.model.components.ComponentType;
import ballblast.model.gameobjects.BallType;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.helpers.GameObjectHelper;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.gameobjects.GameObjectManagerImpl;
import ballblast.model.inputs.InputManagerImpl;
import ballblast.model.inputs.InputManager.PlayerTag;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.SimpleCollisionManager;
import ballblast.model.powerups.Power;
import ballblast.model.powerups.PowerFactory;
import ballblast.model.powerups.PowerFactoryImpl;
import ballblast.model.powerups.ShieldPower;

/**
 * JUnit test for {@link Power}.
 */
public class TestPower {
    private static final Coordinate POSITION = new Coordinate(100, 100);
    private static final Coordinate DEFAULT = new Coordinate(0, 0);
    private static final Vector2D VELOCITY = new Vector2D(0, 0);

    private final GameObjectManager gameObjectManager = new GameObjectManagerImpl();
    private final CollisionManager collisionManager = new SimpleCollisionManager();
    private final PowerFactory factory = new PowerFactoryImpl();
    private GameObject player;

    /**
     * Gets the environment ready for the tests.
     */
    @Before
    public void initializeEnv() {
        this.player = GameObjectHelper.createPlayer(this.gameObjectManager, new InputManagerImpl(), PlayerTag.FIRST,
                this.collisionManager, VELOCITY, POSITION, null, null);
        this.player.getComponents().stream().filter(c -> c.getType().equals(ComponentType.COLLISION)).findFirst()
                .ifPresent(Component::enable);
    }

    /**
     * Test ShieldPower.
     */
    @Test
    public void testShieldPower() {
        Power power;
        do {
            power = this.factory.createPower(VELOCITY, DEFAULT, this.collisionManager);
        } while (!(power instanceof ShieldPower));
        power.activate(this.player);
        assertTrue(power.isActive());
        final GameObject ball = GameObjectHelper.createBall(BallType.LARGE, 1, POSITION, VELOCITY,
                this.collisionManager, this.gameObjectManager, null, null);
        ball.getComponents().stream().filter(c -> c.getType().equals(ComponentType.COLLISION)).findFirst()
                .ifPresent(Component::enable);
        this.collisionManager.checkLoop();
        assertFalse(this.player.isDestroyed());
        power.deactivate();
        assertFalse(power.isActive());
        this.collisionManager.checkLoop();
        assertTrue(this.player.isDestroyed());
    }

}
