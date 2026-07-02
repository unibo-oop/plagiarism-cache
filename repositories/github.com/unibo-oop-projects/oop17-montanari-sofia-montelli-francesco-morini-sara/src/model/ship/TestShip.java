package model.ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import model.basic_component.Cell;
import model.basic_component.CellImpl;
import model.basic_component.StaticPoint2DImpl;

/**
 * Test on the ship class.
 */
public class TestShip {
    /**
     * Test the creation of a ship of size 1.
     * Also test all getter.
     */
    @Test
    public void createShipSize1() {
        final Ship s = new ShipImpl(new StaticPoint2DImpl(0, 0));
        final List<Cell> cells = new ArrayList<>();
        cells.add(new CellImpl(0, 0, Cell.Status.OCCUPIED));
        assertEquals("We expect a ship of size 1", s.getSize(), 1); 
        assertEquals("The cellSet has to be coherent with the cell set size", s.getCellSet().size(), 1);
        assertEquals("A ship of size 1 can't have orientation", s.getOrientation(), Ship.Orientation.INDEFINABLE);
        assertEquals("The ship must start alive", s.getStatus(), Ship.Status.ALIVE);
    }
    /**
     * Check the provided ship against the provided values.
     * @param ship the given ship
     * @param expectedSize the expected size
     * @param expectedOrientation the expected orientation of the ship
     * @param expectedStatus the expected status of the ship
     */
    private void testShip(final Ship ship, final int expectedSize, final Ship.Orientation expectedOrientation, final Ship.Status expectedStatus) {
        assertEquals("Test on the size", ship.getSize(), expectedSize); 
        assertEquals("The cellSet has to be coherent with the cell set size", ship.getCellSet().size(), expectedSize);
        assertEquals("We expect the ship to be vertical", ship.getOrientation(), expectedOrientation);
        assertEquals("The ship must start alive", ship.getStatus(), expectedStatus);
    }

    /**
     * Test a vertical ship of the given size.
     */
    @Test
    public void testVerticalShip() {
        for (int size = 2; size <= Ship.MAX_SHIP_SIZE; size++) {
            final List<Cell> cellList = new ArrayList<>();
            final Ship sv = new ShipImpl(new StaticPoint2DImpl(0, 0), new StaticPoint2DImpl(0, size - 1));

            IntStream.range(0, size).forEach(i -> cellList.add(new CellImpl(i, 0, Cell.Status.OCCUPIED)));
            testShip(sv, size, Ship.Orientation.VERTICAL, Ship.Status.ALIVE);
        }
    }

    /**
     * Test an horizontal ship of the given size.
     * A ship is horizontal if begin and end have the same Y
     */
    @Test
    public void testHorizontalShip() {
        for (int size = 2; size <= Ship.MAX_SHIP_SIZE; size++) {
            final List<Cell> cellList = new ArrayList<>();
            final Ship sv = new ShipImpl(new StaticPoint2DImpl(0, 0), new StaticPoint2DImpl(size - 1, 0));

            IntStream.range(0, size).forEach(i -> cellList.add(new CellImpl(0, 1, Cell.Status.OCCUPIED)));
            testShip(sv, size, Ship.Orientation.HORIZZONTAL, Ship.Status.ALIVE);
        }
    }

    /**
     * We can't create the ship if the begin and end point are not aligned.
     */
    @Test (expected = IllegalArgumentException.class)
    public void illegalCreate() {
        new ShipImpl(new StaticPoint2DImpl(0, 0), new StaticPoint2DImpl(1, 1));
    }

    /**
     * Test the check on the maximum size of a vertical ship.
     */
    @Test (expected = IllegalArgumentException.class)
    public void oversizedShipVertical() {
        new ShipImpl(new StaticPoint2DImpl(0, 0), new StaticPoint2DImpl(0, Ship.MAX_SHIP_SIZE));
    }

    /**
     * Test the check on the maximum size of a vertical ship.
     */
    @Test (expected = IllegalArgumentException.class)
    public void oversizedShipHorizzontal() {
        new ShipImpl(new StaticPoint2DImpl(0, 0), new StaticPoint2DImpl(Ship.MAX_SHIP_SIZE, 0));
    }

    /**
     * Test for the adjacent method.
     */
    @Test
    public void adjacentShip() {
        final Ship s1 = new ShipImpl(new StaticPoint2DImpl(0, 0), new StaticPoint2DImpl(0, 3));
        final Ship s2 = new ShipImpl(new StaticPoint2DImpl(1, 0), new StaticPoint2DImpl(1, 2));
        assertTrue("the ships's should be adjacent", s1.adjacent(s2));
    }

    /**
     * Test for the adjacent method.
     */
    @Test
    public void adjacentPoint() {
        final Ship s1 = new ShipImpl(new StaticPoint2DImpl(0, 0));
        assertTrue("The point should be adjacent to the ship", s1.adjacent(new StaticPoint2DImpl(1, 1)));
    }

    /**
     * Test for the interaction with the ship.
     */
    @Test
    public void interactSunk() {
        final Ship s1 = new ShipImpl(new StaticPoint2DImpl(0, 0));
        s1.interact(new StaticPoint2DImpl(0, 0));
        assertEquals("the ship should be sunk", s1.getStatus(), Ship.Status.SUNK);
    }

    /**
     * test the interaction that result in a hit ship.
     */
    @Test
    public void interactHit() {
        final Ship s2 = new ShipImpl(new StaticPoint2DImpl(0, 0), new StaticPoint2DImpl(1, 0));
        s2.interact(new StaticPoint2DImpl(0, 0));
        assertEquals("the ship should be hitted", s2.getStatus(), Ship.Status.HIT);
    }

    /**
     * Two interaction on the same cell can't be made.
     */
    @Test (expected = IllegalStateException.class)
    public void illegalInteracionTargeted() {
        final Ship s1 = new ShipImpl(new StaticPoint2DImpl(0, 0));
        s1.interact(new StaticPoint2DImpl(0, 0));
        s1.interact(new StaticPoint2DImpl(0, 0));
    }

    /**
     * An interaction with a cell outside the ship is not possible.
     */
    @Test 
    public void illegalInteractionNotBelong() {
        final Ship s1 = new ShipImpl(new StaticPoint2DImpl(0, 1));
        assertFalse("interaction with a cell outside the ship", s1.interact(new StaticPoint2DImpl(0, 0)));
    }
    /**
     * Test for the equals method on ship.
     */
    @Test
    public void equal() {
        final Ship s1 = new ShipImpl(new StaticPoint2DImpl(0, 0), new StaticPoint2DImpl(0, 3));
        final Ship s2 = new ShipImpl(new StaticPoint2DImpl(0, 0), new StaticPoint2DImpl(0, 3));
        final Ship s3 = new ShipImpl(new StaticPoint2DImpl(0, 3), new StaticPoint2DImpl(0, 0));
        assertEquals("the ship should be the same", s1, s2);
        assertEquals("the ship should be the same because made of the same cells", s1, s3);
    }
}
