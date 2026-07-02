package test.it.unibo.oop.manpac.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.model.ManpacImpl;
import it.unibo.oop.manpac.model.ModelForController;

public class TestPacmanCollisions {
    
    @Test
    public void testPacmanCollisions(){
        ModelForController model = new ManpacImpl(3, 20);
        assertNotNull(model);
        assertTrue(model.inputDirection(Directions.UP) == Action.DIRECTION_CHANGED_UP);
        assertTrue(model.pacmanCollision(Entity.WALL) == Action.DIRECTION_CHANGED_STOP);
        assertTrue(model.pacmanCollision(Entity.WALL) == Action.NOTHING_HAPPENS);
        assertTrue(model.pacmanCollision(Entity.PILL) == Action.PILL_EATEN);
        assertTrue(model.pacmanCollision(Entity.CLYDE) == Action.PHANTOM_KILLED_PACMAN);
        assertTrue(model.getLives() == 2);
        assertTrue(model.pacmanCollision(Entity.POWERPILL) == Action.POWER_PILL_EATEN);
        assertTrue(model.pacmanCollision(Entity.PORTAL) == Action.PACMAN_EFFECT);
        assertTrue(model.pacmanCollision(Entity.CLYDE) == Action.PACMAN_ATE_PHANTOM);
        assertTrue(model.getLives() == 2);
    }

}
