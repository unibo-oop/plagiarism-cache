package model.match;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.enums.ShipType;

public class TestShip {

    @Test
    public void testCreationShip() {

        final Ship carrier = new Ship(ShipType.CARRIER);
        final Ship battleship = new Ship(ShipType.BATTLESHIP);
        final Ship cruiser = new Ship(ShipType.CRUISER);

        assertEquals(carrier.getSize(), 5);
        assertEquals(battleship.getSize(), 4);
        assertEquals(cruiser.getSize(), 3);
    }

    @Test
    public void testDestruction() {

        final Ship carrier = new Ship(ShipType.CARRIER);
        final Ship battleship = new Ship(ShipType.BATTLESHIP);
        final Ship cruiser = new Ship(ShipType.CRUISER);

        for (int i = 0; i < 5; i++) {
            carrier.hit();
        }
        assertTrue(carrier.isDestroyed());

        for (int i = 0; i < 4; i++) {
            battleship.hit();
        }
        assertTrue(battleship.isDestroyed());

        for (int i = 0; i < 3; i++) {
            cruiser.hit();
        }
        assertTrue(cruiser.isDestroyed());

        final Ship submarine = new Ship(ShipType.SUBMARINE);
        submarine.hit();
        assertFalse(submarine.isDestroyed());

        final Ship destroyer = new Ship(ShipType.DESTROYER);
        destroyer.hit();
        assertFalse(destroyer.isDestroyed());

    }

}
