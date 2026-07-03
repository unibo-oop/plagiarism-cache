package oop.raccoonhome.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import oop.raccoonhome.device.EDevice;
import oop.raccoonhome.device.Lamp;

/**
 *
 *
 */
public class LampTest {
    /**
     * 
     */
    @Test
    public void test1() {
        Lamp lamp = Lamp.getLamp();

        lamp.switchOn();
        assertTrue(lamp.isOn());
        lamp.switchOff();
        assertFalse(lamp.isOn());
        assertTrue(lamp.setIntensity(10));
        assertEquals(10, lamp.getIntensity());
        assertFalse(lamp.setIntensity(1000));
        assertEquals(10, lamp.getIntensity());
        lamp.setPropriety(EDevice.LAMP_L);
        assertEquals(EDevice.LAMP_L, lamp.getPropriety());
    }
}
