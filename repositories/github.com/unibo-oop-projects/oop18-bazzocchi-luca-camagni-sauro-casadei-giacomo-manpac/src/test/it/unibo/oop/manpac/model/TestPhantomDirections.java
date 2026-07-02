package test.it.unibo.oop.manpac.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Test;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.model.ManpacImpl;
import it.unibo.oop.manpac.model.ModelForController;
import it.unibo.oop.manpac.model.entities.PhantomDirectionGenerator;
import it.unibo.oop.manpac.model.entities.PhantomModelImpl;
import it.unibo.oop.manpac.model.score.PointsImpl;

public class TestPhantomDirections {

    @Test
    public void testPhantomDirections() {

        PhantomModelImpl phant = new PhantomModelImpl(new PointsImpl<>(10), Entity.BLINKY);
        Directions dir;
        Directions dir2;

        assertNotNull(phant);
        dir = phant.getDirection();
        assertTrue(dir.equals(Directions.STOP));
        PhantomDirectionGenerator p = new PhantomDirectionGenerator() {
            @Override
            public Directions generateDirection() {
                return Directions.values()[new Random().nextInt(4)];
            }
        };
        assertFalse(phant.generateDirection(p).equals(Action.NOTHING_HAPPENS));
        dir2 = phant.getDirection();
        assertFalse(dir2.equals(dir));
        assertFalse(phant.generateDirection(p).equals(Action.NOTHING_HAPPENS));
        dir = phant.getDirection();
        assertFalse(dir.equals(dir2));
        assertFalse(phant.generateDirection(p).equals(Action.NOTHING_HAPPENS));
        dir2 = phant.getDirection();
        assertFalse(dir2.equals(dir));
    }

    @Test
    public void testExceptions() {
        ModelForController model = new ManpacImpl(20, 3);

        assertNotNull(model);
        try {
            model.phantomCollision(null, Entity.WALL);
            fail();
        } catch (IllegalArgumentException e) {
        }

        try {
            model.phantomCollision(Entity.CLYDE, null);
            fail();
        } catch (IllegalArgumentException e) {
        }

        try {
            model.phantomCollision(null, null);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

}
