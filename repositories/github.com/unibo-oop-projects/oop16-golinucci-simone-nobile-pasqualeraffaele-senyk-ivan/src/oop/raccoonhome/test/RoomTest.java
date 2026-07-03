package oop.raccoonhome.test;

import oop.raccoonhome.device.Lamp;
import oop.raccoonhome.device.Shutters;
import oop.raccoonhome.home.Room;
import oop.raccoonhome.home.ERoom;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

/**
 *
 *
 */
public final class RoomTest {

    /**
     * 
     */
    @Test
    public void test1() {
        Room stanza1 = new Room(ERoom.CAMERADALETTO);
        Room stanza2 = new Room(ERoom.CUCINA);
        Room stanza3 = new Room(ERoom.SOGGIORNO);

        stanza1.addDevice(Lamp.getLamp());
        stanza1.addDevice(Lamp.getLamp());
        stanza2.addDevice(Lamp.getLamp());
        stanza2.addDevice(new Shutters());
        stanza3.addDevice(Lamp.getLamp());
        stanza2.addDevice(Lamp.getLamp());
        stanza3.addDevice(Lamp.getLamp());
        stanza3.addDevice(new Shutters());

        assertEquals(stanza1.getRoom(), ERoom.CAMERADALETTO);
        assertNotEquals(stanza1.getRoom(), ERoom.CUCINA);
        assertEquals(stanza2.getRoom(), ERoom.CUCINA);
        assertNotEquals(stanza2.getRoom(), ERoom.CAMERADALETTO);
        assertEquals(stanza3.getRoom(), ERoom.SOGGIORNO);
        assertNotEquals(stanza3.getRoom(), ERoom.CAMERADALETTO);

        assertEquals(stanza1.getDevice().size(), 2);
        assertEquals(stanza3.getDevice().size(), 3);
        assertNotEquals(stanza2.getDevice().size(), 4);

    }

}