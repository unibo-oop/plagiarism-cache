package test.it.unibo.oop.manpac.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.model.ManpacImpl;
import it.unibo.oop.manpac.model.ModelForController;

public class TestPacmanDirections {
    
    @Test
    public void testInputs() {
        ModelForController model = new ManpacImpl (20, 3);
        
        assertNotNull(model);
        assertTrue(model.inputDirection(Directions.STOP).equals(Action.NOTHING_HAPPENS));
        assertTrue(model.inputDirection(Directions.UP).equals(Action.DIRECTION_CHANGED_UP));
        assertTrue(model.pacmanCollision(Entity.WALL).equals(Action.DIRECTION_CHANGED_STOP));
        assertTrue(model.inputDirection(Directions.LEFT).equals(Action.DIRECTION_CHANGED_LEFT));
        assertTrue(model.inputDirection(Directions.LEFT).equals(Action.NOTHING_HAPPENS));
        assertTrue(model.inputDirection(Directions.RIGHT).equals(Action.DIRECTION_CHANGED_RIGHT));
        assertTrue(model.inputDirection(Directions.DOWN).equals(Action.DIRECTION_CHANGED_DOWN));
        assertTrue(model.inputDirection(Directions.DOWN).equals(Action.NOTHING_HAPPENS));
        
    }

}
