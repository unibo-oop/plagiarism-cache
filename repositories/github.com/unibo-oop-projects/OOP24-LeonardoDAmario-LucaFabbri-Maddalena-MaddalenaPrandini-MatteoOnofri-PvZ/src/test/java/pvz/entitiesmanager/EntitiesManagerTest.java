package pvz.entitiesmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pvz.model.game.impl.EntitiesManagerImpl;
import pvz.model.entities.api.Entity;
import pvz.model.zombies.impl.BasicZombie;
import pvz.utilities.Position;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EntitiesManagerTest {

    private static final Position ENTITYPOS = new Position(10, 0);
    private EntitiesManagerImpl entitiesManager;
    private Entity entity1;
    private Entity entity2;

    @BeforeEach
    void setUp() {
        entitiesManager = new EntitiesManagerImpl();
        entity1 = new BasicZombie(ENTITYPOS);
        entity2 = new BasicZombie(ENTITYPOS);
    }

    @Test
    void testAddEntity() {
        entitiesManager.addEntity(entity1);
        final Set<Entity> entities = entitiesManager.getEntities();
        assertTrue(entities.contains(entity1));
    }

    @Test
    void testRemoveEntity() {
        entitiesManager.addEntity(entity1);
        entitiesManager.removeEntity(entity1);
        final Set<Entity> entities = entitiesManager.getEntities();
        assertFalse(entities.contains(entity1));
    }

    @Test
    void testGetEntities() {
        entitiesManager.addEntity(entity1);
        entitiesManager.addEntity(entity2);
        final Set<Entity> entities = entitiesManager.getEntities();
        assertEquals(2, entities.size());
        assertTrue(entities.contains(entity1));
        assertTrue(entities.contains(entity2));
    }

    @Test
    void testKillCount() {
        assertEquals(0, entitiesManager.getKillCount());
        entitiesManager.addKill();
        assertEquals(1, entitiesManager.getKillCount());
        entitiesManager.addKill();
        assertEquals(2, entitiesManager.getKillCount());
    }
}
