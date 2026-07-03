package oop.raccoonhome.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import oop.raccoonhome.device.Door;
import oop.raccoonhome.device.EDevice;

/**
 *
 *
 */
public class DoorTest {
    /**
     * 
     */
    @Test
    public void test1() {
        Door door = new Door();
        // apro la porta ma rimane chiusa perchè di default è bloccata
        door.switchOn();
        assertFalse(door.isOn());
        // sblocco la porta
        door.open();
        assertTrue(door.isOpen());
        // apro la porta che si apre perchè non è piu bloccata
        door.switchOn();
        assertTrue(door.isOn());
        // bloco la porta ma non si blocca perché e aperta
        door.close();
        assertTrue(door.isOn());
        assertTrue(door.isOpen());
        // chiudo la porta ma non è bloccata
        door.switchOff();
        assertFalse(door.isOn());
        assertTrue(door.isOpen());
        // blocco la porta
        door.close();
        assertFalse(door.isOn());
        assertFalse(door.isOpen());
        assertEquals(EDevice.DOOR, door.getPropriety());
    }
}
