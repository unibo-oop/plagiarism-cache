package test.it.unibo.oop.manpac.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.model.ManpacImpl;
import it.unibo.oop.manpac.model.ModelForController;

public class TestGetPoints {

    @Test
    public void testPills() {
        ModelForController model = new ManpacImpl(3, 20);

        assertNotNull(model);
        assertTrue(model.getCurrentScore().getValue() == 0);
        assertTrue(model.getHighScore().getValue() == 0);
        assertTrue((model.pacmanCollision(Entity.PILL).equals(Action.PILL_EATEN)));
        assertTrue(model.getCurrentScore().getValue() == 10);
        assertTrue((model.pacmanCollision(Entity.PILL).equals(Action.PILL_EATEN)));
        assertTrue((model.pacmanCollision(Entity.PILL).equals(Action.PILL_EATEN)));
        assertTrue(model.getCurrentScore().getValue() == 30);
        assertTrue(model.getHighScore().getValue() == 30);
    }

    @Test
    public void testPowerPills() {
        ModelForController model = new ManpacImpl(3, 20);

        assertNotNull(model);
        assertTrue(model.getCurrentScore().getValue() == 0);
        assertTrue(model.getHighScore().getValue() == 0);
        assertTrue((model.pacmanCollision(Entity.POWERPILL).equals(Action.POWER_PILL_EATEN)));
        assertTrue(model.getCurrentScore().getValue() == 50);
        assertTrue((model.pacmanCollision(Entity.PILL).equals(Action.PILL_EATEN)));
        assertTrue((model.pacmanCollision(Entity.POWERPILL).equals(Action.POWER_PILL_EATEN)));
        assertTrue(model.getCurrentScore().getValue() == 110);
        assertTrue(model.getHighScore().getValue() == 110);
    }

    @Test
    public void testPhantomPoints() {
        ModelForController model = new ManpacImpl(3, 20);

        assertNotNull(model);
        assertTrue(model.getCurrentScore().getValue() == 0);
        assertTrue(model.getHighScore().getValue() == 0);
        assertTrue((model.pacmanCollision(Entity.CLYDE).equals(Action.PHANTOM_KILLED_PACMAN)));
        assertTrue(model.getLives() == 2);
        assertTrue((model.pacmanCollision(Entity.POWERPILL).equals(Action.POWER_PILL_EATEN)));
        assertTrue(model.getCurrentScore().getValue() == 50);
        assertTrue((model.pacmanCollision(Entity.PILL).equals(Action.PILL_EATEN)));
        assertTrue((model.pacmanCollision(Entity.CLYDE).equals(Action.PACMAN_ATE_PHANTOM)));
        assertTrue(model.getCurrentScore().getValue() == 260);
        assertTrue(model.getHighScore().getValue() == 260);
    }

}
