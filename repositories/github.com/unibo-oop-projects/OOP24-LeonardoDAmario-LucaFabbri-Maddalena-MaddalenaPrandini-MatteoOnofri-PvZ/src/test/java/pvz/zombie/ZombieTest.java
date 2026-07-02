package pvz.zombie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pvz.model.game.api.EntitiesManager;
import pvz.model.game.impl.EntitiesManagerImpl;
import pvz.model.plants.api.Plant;
import pvz.model.plants.api.PlantFactory;
import pvz.model.plants.impl.PlantFactoryImpl;
import pvz.model.zombies.api.Zombie;
import pvz.model.zombies.api.ZombieType;
import pvz.model.zombies.impl.BasicZombie;
import pvz.model.zombies.impl.FastZombie;
import pvz.model.zombies.impl.StrongZombie;
import pvz.model.zombies.impl.BeastZombie;
import pvz.utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for all zombie types and their core behaviors.
 */
class ZombieTest {

    private static final int BASIC_ZOMBIE_DAMAGE = 35;
    private static final int FAST_ZOMBIE_DAMAGE = 35;
    private static final int STRONG_ZOMBIE_DAMAGE = 45;
    private static final int BEAST_ZOMBIE_DAMAGE = 150;
    private static final int DAMAGE_40 = 40;
    private static final int DAMAGE_50 = 50;
    private static final int DAMAGE_60 = 60;
    private static final int DAMAGE_100 = 100;
    private static final int ZOMBIE_X = 5;
    private static final int ZOMBIE_Y1 = 1;
    private static final int ZOMBIE_Y2 = 2;
    private static final int ZOMBIE_Y3 = 3;
    private static final int ZOMBIE_Y4 = 4;
    private static final int UPDATE_ATTACK = 2000;

    private EntitiesManager entitiesManager;
    private PlantFactory plantFactory;

    /**
     * Sets up a fresh EntitiesManager and PlantFactory before each test.
     */
    @BeforeEach
    void setUp() {
        entitiesManager = new EntitiesManagerImpl();
        plantFactory = new PlantFactoryImpl();
    }

    /**
     * Tests the attributes of BasicZombie.
     */
    @Test
    void testBasicZombieAttributes() {
        final Zombie zombie = new BasicZombie(new Position(ZOMBIE_X, ZOMBIE_Y1));
        assertEquals(BASIC_ZOMBIE_DAMAGE, zombie.getDamage());
        assertEquals(ZombieType.BASICZOMBIE, zombie.getType());
        assertTrue(zombie.isAlive());
    }

    /**
     * Tests the attributes of FastZombie.
     */
    @Test
    void testFastZombieAttributes() {
        final Zombie zombie = new FastZombie(new Position(ZOMBIE_X, ZOMBIE_Y2));
        assertEquals(FAST_ZOMBIE_DAMAGE, zombie.getDamage());
        assertEquals(ZombieType.FASTZOMBIE, zombie.getType());
        assertTrue(zombie.isAlive());
    }

    /**
     * Tests the attributes of StrongZombie.
     */
    @Test
    void testStrongZombieAttributes() {
        final Zombie zombie = new StrongZombie(new Position(ZOMBIE_X, ZOMBIE_Y3));
        assertEquals(STRONG_ZOMBIE_DAMAGE, zombie.getDamage());
        assertEquals(ZombieType.STRONGZOMBIE, zombie.getType());
        assertTrue(zombie.isAlive());
    }

    /**
     * Tests the attributes of BeastZombie.
     */
    @Test
    void testBeastZombieAttributes() {
        final Zombie zombie = new BeastZombie(new Position(ZOMBIE_X, ZOMBIE_Y4));
        assertEquals(BEAST_ZOMBIE_DAMAGE, zombie.getDamage());
        assertEquals(ZombieType.BEASTZOMBIE, zombie.getType());
        assertTrue(zombie.isAlive());
    }

    /**
     * Tests that a zombie dies after taking sufficient damage.
     */
    @Test
    void testZombieTakeDamageAndDeath() {
        final Zombie zombie = new BasicZombie(new Position(0, 0));
        zombie.takeDamage(DAMAGE_50);
        assertTrue(zombie.isAlive());
        zombie.takeDamage(DAMAGE_60);
        assertFalse(zombie.isAlive());
    }

    /**
     * Tests the decreaseLife method for zombies.
     */
    @Test
    void testZombieDecreaseLife() {
        final Zombie zombie = new FastZombie(new Position(0, 0));
        zombie.decreaseLife(DAMAGE_40);
        assertTrue(zombie.isAlive());
        zombie.decreaseLife(DAMAGE_100);
        assertFalse(zombie.isAlive());
    }

    /**
     * Tests the forceKill immediately kills the zombie.
     */
    @Test
    void testZombieForceKill() {
        final Zombie zombie = new StrongZombie(new Position(0, 0));
        assertTrue(zombie.isAlive());
        zombie.forceKill();
        assertFalse(zombie.isAlive());
    }

    /**
     * Tests that a zombie attacks and removes a plant after enough updates.
     */
    @Test
    void testZombieAttacksPlantAndRemovesIt() {
        final Zombie zombie = new BasicZombie(new Position(1, 1));
        final Plant plant = plantFactory.createPeashooter(new Position(1, 1));
        entitiesManager.addEntity(zombie);
        entitiesManager.addEntity(plant);

        for (int i = 0; i < 10; i++) {
            zombie.update(UPDATE_ATTACK, entitiesManager);
        }
        assertFalse(entitiesManager.getEntities().contains(plant));
    }

    /**
     * Tests that a zombie moves when there is no plant to attack.
     */
    @Test
    void testZombieMovesWhenNoPlant() {
        final Zombie zombie = new BasicZombie(new Position(ZOMBIE_X, ZOMBIE_Y1));
        final double initialX = zombie.getPosition().x();
        entitiesManager.addEntity(zombie);

        zombie.update(100, entitiesManager);
        assertTrue(zombie.getPosition().x() < initialX);
    }
}
