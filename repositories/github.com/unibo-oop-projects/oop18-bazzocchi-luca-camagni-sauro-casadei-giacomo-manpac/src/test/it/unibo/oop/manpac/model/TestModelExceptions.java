package test.it.unibo.oop.manpac.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.model.Manpac;
import it.unibo.oop.manpac.model.ManpacImpl;
import it.unibo.oop.manpac.model.ModelForController;

public final class TestModelExceptions {

    @Test
    public void testConstructorExceptions() {
        try {
            final Manpac model = new ManpacImpl(-6, 3);
            fail();
            assertNull(model);
        } catch (IllegalArgumentException e) {

        }

        try {
            final Manpac model = new ManpacImpl(5, -9);
            fail();
            assertNull(model);
        } catch (IllegalArgumentException e) {

        }

        try {
            final Manpac model = new ManpacImpl(-45, -9);
            fail();
            assertNull(model);
        } catch (IllegalArgumentException e) {

        }

    }

    @Test
    public void testCollisionExceptions() {
        final ModelForController model = new ManpacImpl(20, 3);

        assertNotNull(model);

        try {
            assertTrue(model.inputDirection(Directions.UP).equals(Action.DIRECTION_CHANGED_UP));
            model.inputDirection(null);
            fail();
        } catch (IllegalArgumentException e) {

        }

        try {
            assertTrue(model.pacmanCollision(Entity.WALL).equals(Action.DIRECTION_CHANGED_STOP));
            model.pacmanCollision(null);
            fail();
        } catch (IllegalArgumentException e) {

        }

    }

}
