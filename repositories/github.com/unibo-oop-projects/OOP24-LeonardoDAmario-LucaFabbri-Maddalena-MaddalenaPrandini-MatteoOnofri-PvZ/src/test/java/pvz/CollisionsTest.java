package pvz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pvz.model.collisions.api.CollisionManager;
import pvz.model.collisions.impl.CollisionManagerImpl;
import pvz.model.bullets.api.Bullet;
import pvz.model.bullets.impl.BulletImpl;
import pvz.model.game.api.EntitiesManager;
import pvz.model.game.impl.EntitiesManagerImpl;
import pvz.model.plants.api.Plant;
import pvz.model.plants.impl.PlantFactoryImpl;
import pvz.model.zombies.api.Zombie;
import pvz.model.zombies.impl.BasicZombie;
import pvz.utilities.Position;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for CollisionManagerImpl collision detection logic.
 */
final class CollisionsTest {

    private static final double X = 100.0;
    private static final double Y1 = 50.0;
    private static final double Y2 = 60.0;

    private CollisionManager collisionManager;
    private EntitiesManager entitiesManager;
    private PlantFactoryImpl plantFactory;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        collisionManager = new CollisionManagerImpl();
        entitiesManager = new EntitiesManagerImpl();
        plantFactory = new PlantFactoryImpl();
    }

    @Test
    void testBulletZombieCollision() {
        final Bullet bullet = new BulletImpl(new Position(X, Y1));
        final Zombie zombie = new BasicZombie(new Position(X, Y1));
        entitiesManager.addEntity(bullet);
        entitiesManager.addEntity(zombie);
        final Optional<?> result = collisionManager.handleCollision(bullet, entitiesManager);
        assertTrue(result.isPresent(), "La collisione tra bullet e zombie dovrebbe essere rilevata");
        assertEquals(zombie, result.get(), "L'entità collisionata dovrebbe essere lo zombie");
    }

    @Test
    void testBulletZombieNoCollisionDifferentY() {
        final Bullet bullet = new BulletImpl(new Position(X, Y1));
        final Zombie zombie = new BasicZombie(new Position(X, Y2));
        entitiesManager.addEntity(bullet);
        entitiesManager.addEntity(zombie);
        final Optional<?> result = collisionManager.handleCollision(bullet, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione tra bullet e zombie su y diverse");
    }

    @Test
    void testBulletZombieNoEntities() {
        final Bullet bullet = new BulletImpl(new Position(X, Y1));
        final Optional<?> result = collisionManager.handleCollision(bullet, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione senza entità");
    }

    @Test
    void testZombiePlantCollision() {
        final Zombie zombie = new BasicZombie(new Position(X, Y1));
        final Plant plant = plantFactory.createPeashooter(new Position(X, Y1));
        entitiesManager.addEntity(zombie);
        entitiesManager.addEntity(plant);
        final Optional<?> result = collisionManager.handleCollision(zombie, entitiesManager);
        assertTrue(result.isPresent(), "La collisione tra zombie e pianta dovrebbe essere rilevata");
        assertEquals(plant, result.get(), "L'entità collisionata dovrebbe essere la pianta");
    }

    @Test
    void testZombiePlantNoCollisionDifferentY() {
        final Zombie zombie = new BasicZombie(new Position(X, Y1));
        final Plant plant = plantFactory.createPeashooter(new Position(X, Y2));
        entitiesManager.addEntity(zombie);
        entitiesManager.addEntity(plant);
        final Optional<?> result = collisionManager.handleCollision(zombie, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione tra zombie e pianta su y diverse");
    }

    @Test
    void testZombiePlantNoEntities() {
        final Zombie zombie = new BasicZombie(new Position(X, Y1));
        final Optional<?> result = collisionManager.handleCollision(zombie, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione senza entità");
    }

    @Test
    void testWallnutZombieCollision() {
        final Plant wallnut = plantFactory.createWallnut(new Position(X, Y1));
        final Zombie zombie = new BasicZombie(new Position(X, Y1));
        entitiesManager.addEntity(wallnut);
        entitiesManager.addEntity(zombie);
        final Optional<?> result = collisionManager.handleCollision(wallnut, entitiesManager);
        assertTrue(result.isPresent(), "La collisione tra wallnut e zombie dovrebbe essere rilevata");
        assertEquals(zombie, result.get(), "L'entità collisionata dovrebbe essere lo zombie");
    }

    @Test
    void testWallnutZombieNoCollisionDifferentY() {
        final Plant wallnut = plantFactory.createWallnut(new Position(X, Y1));
        final Zombie zombie = new BasicZombie(new Position(X, Y2));
        entitiesManager.addEntity(wallnut);
        entitiesManager.addEntity(zombie);
        final Optional<?> result = collisionManager.handleCollision(wallnut, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione tra wallnut e zombie su y diverse");
    }

    @Test
    void testWallnutZombieNoEntities() {
        final Plant wallnut = plantFactory.createWallnut(new Position(X, Y1));
        final Optional<?> result = collisionManager.handleCollision(wallnut, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione senza entità");
    }
}
