package model.basic_component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Basic test for the cell interface.
 */
public final class TestCell {
    private Cell c;
    private Cell occupiedCell;

    /**
     * Initialise a standard cell.
     */
    @Before
    public void init() {
        c = new CellImpl(0, 0);
        occupiedCell = new CellImpl(2, 2, Cell.Status.OCCUPIED);
    }

    /**
     * Test for the current initialisation of the cell.
     */
    @Test
    public void testCreationFree() {
        assertTrue("at the beginning the cell should be free", c.getStatus().equals(Cell.Status.FREE));
        assertEquals("test on the getter of the cell's coordinate", c.getCoordinate(), new StaticPoint2DImpl(0, 0));
    }

    /**
     * Test the construction of an occupied cell.
     */
    @Test 
    public void testCreationOccupied() {
        assertTrue("at the beginning the cell should be occupied", occupiedCell.getStatus().equals(Cell.Status.OCCUPIED));
        assertEquals("test on the coordinates", occupiedCell.getCoordinate(), new StaticPoint2DImpl(2, 2));
    }

    /**
     * We can't have  a point with negative coordinates.
     */
    @Test (expected = IllegalArgumentException.class) 
    public void testNegativeArgument() {
        new CellImpl(-1, 0);
    }

    /**
     * Test for the interaction method.
     */
    @Test
    public void testInteractionFromFree() {
        c.interact();
        assertTrue("After the interaction a free cel is targeted", c.getStatus().equals(Cell.Status.TARGETED));
    }

    /**
     * Test the interaction with an occupied cell.
     */
    @Test
    public void testInteractionFromOccupated() {
        occupiedCell.interact();
        assertTrue("an occupied cell after the interaction should be OCCUPIED_AND_TARGETED", occupiedCell.getStatus().equals(Cell.Status.OCCUPIED_AND_TARGETED));
    }

    /**
     * Test the exception throw in the interact method.
     */
    @Test (expected = IllegalStateException.class)
    public void testInteractionExcpetion() {
        c.interact();
        c.interact();
    }

    /**
     * Test for the equals method.
     */
    @Test 
    public void testEquals() {
        final Cell c1 = new CellImpl(0, 0, Cell.Status.FREE);
        assertEquals("two cell with the same coordinate has to be equal", c, c1);
        c.interact();
        assertTrue("the first cell has been hit", c.getStatus().equals(Cell.Status.TARGETED));
        assertTrue("the second cell hasn't been hit", c1.getStatus().equals(Cell.Status.FREE));
        assertEquals("regardless from the state the cell are equals", c, c1);
    }

}
