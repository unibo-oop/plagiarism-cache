package model.ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import model.basic_component.StaticPoint2DImpl;

/**
 * Test for the built of the ship.
 */
public class TestBuilderShip {
    /**
     * Ship builder to be tested.
     */
    private transient BuilderShip builderShip;

    /**
     * Initialise a new ship.
     */
    @Before
    public void init() {
        builderShip = new BuilderShipImpl();
    }
    /**
     * Test for the correct construction.
     */
    @Test
    public void testCorrectBuild() {
        builderShip.setSizeShip(2);
        assertEquals("Test for the ship size", builderShip.getSize(), Optional.of(2));
        builderShip.setFirstCoord(new StaticPoint2DImpl(0, 0));
        assertEquals("Test for the first coordinate", builderShip.getFirstCoord(), Optional.of(new StaticPoint2DImpl(0, 0)));
        builderShip.setSecondCoord(new StaticPoint2DImpl(0, 1));
        assertEquals("Test for the second coordinate", builderShip.getSecondCoord(), Optional.of(new StaticPoint2DImpl(0, 1)));
        final Ship ship = builderShip.build();
        assertEquals("Test for the ship composition", ship, new ShipImpl(new StaticPoint2DImpl(0, 1), new StaticPoint2DImpl(0, 0)));
    }
    /**
     * Test on the maximum ship size limit.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testErrorSetSize() {
        builderShip.setSizeShip(Ship.MAX_SHIP_SIZE + 1);
    }
    /**
     * Test a declaration at the wrong time of the second coordinate.
     */
    @Test (expected = IllegalStateException.class)
    public void testSetSecondCoordBeforeFirst() {
        builderShip.setSecondCoord(new StaticPoint2DImpl(1, 1));
    }
    /**
     * test a wrong argument for the second coordinate.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetSecondCoordWrongArgument() {
        builderShip.setFirstCoord(new StaticPoint2DImpl(0, 0));
        builderShip.setSecondCoord(new StaticPoint2DImpl(0, 1));
        assertEquals("Test size after set second coord", builderShip.getSize(), Optional.of(2));
        builderShip.removeSecondCoord();
        assertEquals("Test remove second coord", builderShip.getSecondCoord(), Optional.empty());
        builderShip.setSizeShip(3);
        builderShip.setSecondCoord(new StaticPoint2DImpl(0, 1));
    }
    /**
     * Test build in incorrect conditions.
     */
    @Test (expected = IllegalStateException.class)
    public void testErrorBuild() {
        builderShip.build();
        builderShip.setFirstCoord(new StaticPoint2DImpl(0, 0));
        builderShip.build();
        assertEquals("test the correct size of the ship", builderShip.getSize(), Optional.of(1));
    }
    /**
     * Test for return type size.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSize() {
        Optional<Integer> ship;
        builderShip.setSizeShip(0);
        builderShip.setSizeShip(2);
        ship = builderShip.getSize();
        assertEquals("test the correct size of the ship", ship, Optional.of(2));
        ship = Optional.empty();
        assertNotEquals(ship, builderShip.getSize());
    }
    /**
     * test the wrong sequence of method calls.
     */
    @Test (expected = IllegalStateException.class)
    public void testWrongSequenceCalls() {
        builderShip.setFirstCoord(new StaticPoint2DImpl(0, 0));
        builderShip.setSecondCoord(new StaticPoint2DImpl(0, 1));
        assertEquals("test the correct size of the ship", builderShip.getSize(), Optional.of(2)); 
        builderShip.setSizeShip(3);
        assertEquals("test the correct size of the ship after the attempt of a modify", builderShip.getSize(), Optional.of(2)); 
        builderShip.resetCoord();
        builderShip.setSecondCoord(new StaticPoint2DImpl(0, 0));
        assertEquals("verify the couldn't set the second coordinate before setting the first", builderShip.getSecondCoord(), Optional.empty());
    }
}
