package pvz.lawnmower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pvz.model.game.impl.EntitiesManagerImpl;
import pvz.model.lawnmower.api.LawnMower;
import pvz.model.lawnmower.impl.LawnMowerImpl;
import pvz.model.zombies.impl.BasicZombie;
import pvz.utilities.Position;
import pvz.model.collisions.impl.HitBoxFactory;
import pvz.model.zombies.api.Zombie;
import pvz.model.game.api.EntitiesManager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LawnMowerTest {

    private LawnMower lawnMower;
    private EntitiesManager entitiesManager;
    private Zombie zombie;

    @BeforeEach
    void setUp() {
        lawnMower = new LawnMowerImpl(new Position(0, 1), HitBoxFactory.HitBoxType.ZOMBIE);
        entitiesManager = new EntitiesManagerImpl();
        zombie = new BasicZombie(new Position(0, 1));
        entitiesManager.addEntity(zombie);
    }

    @Test
    void testMove() {
        final double initialX = lawnMower.getPosition().x();
        lawnMower.update(0, entitiesManager);
        assertTrue(lawnMower.getPosition().x() > initialX);
    }

    @Test
    void testUpdateKillsZombieOnCollision() {
        assertTrue(zombie.isAlive());
        lawnMower.update(0, entitiesManager);
        assertFalse(zombie.isAlive());
        assertTrue(entitiesManager.getKillCount() > 0);
        assertFalse(entitiesManager.getEntities().contains(zombie));
    }

}
