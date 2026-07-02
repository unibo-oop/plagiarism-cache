package model.navy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Test class for the Build of random ships.
 */
public class TestRandomNavy {

    /**
     * Test each ship against the other in order to determinate if some ship are adjacent.
     * a ship is, of course, not tested against itself
     */
    @Test
    public void classicRandom() {
        final RandomNavyFactory rnf = new RandomNavyFactoryImpl();
        final Navy randomNavy = rnf.getClassicRandomFormation();
        assertEquals("test the correct total size of the navy", randomNavy.getShips().size(), 10);
        randomNavy.getShips().forEach(ship -> {
            randomNavy.getShips().forEach(s -> {
                if (!ship.equals(s)) {
                    assertFalse("test that shipments are never adjacent to each other", ship.adjacent(s));
                }
            });
        });
    }
}
