package oop.raccoonhome.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.Test;
import oop.raccoonhome.home.ERoom;
import oop.raccoonhome.home.House;
import oop.raccoonhome.home.Room;

/**
 *
 *
 */
public class HouseTest {
    /**
     * 
     */
    @Test
    public void test1() {
        House casa = new House();
        casa.addRoom(new Room(ERoom.CUCINA));
        casa.addRoom(new Room(ERoom.SOGGIORNO));
        casa.addRoom(new Room(ERoom.CAMERADALETTO));

        assertEquals(casa.getRoom(ERoom.CUCINA).size(), 1);
        assertEquals(casa.getRooms().size(), 3);
        assertTrue(casa.addRooms(
                Arrays.asList(new Room(ERoom.CANTINA), new Room(ERoom.SALOTTO), new Room(ERoom.CAMERADALETTO))));

        assertEquals(casa.getRoom(ERoom.CAMERADALETTO).size(), 2);

    }
}
