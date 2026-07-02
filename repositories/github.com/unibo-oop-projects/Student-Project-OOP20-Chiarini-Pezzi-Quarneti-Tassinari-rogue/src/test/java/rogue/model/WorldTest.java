package rogue.model;

import static org.junit.Assert.assertNotNull;

import rogue.model.creature.PlayerFactoryImpl;
import rogue.model.world.World;
import rogue.model.world.WorldImpl;

public class WorldTest {

    @org.junit.Test
    public void testWorldCreation() {
        final World w = new WorldImpl(new PlayerFactoryImpl().create());
        w.getTiles().forEach(t -> assertNotNull(t));
        assertNotNull(w.getPlayer());
        w.getEntityMap().forEach((e, t) -> {
            assertNotNull(e);
            assertNotNull(t);
        });
    }
}
