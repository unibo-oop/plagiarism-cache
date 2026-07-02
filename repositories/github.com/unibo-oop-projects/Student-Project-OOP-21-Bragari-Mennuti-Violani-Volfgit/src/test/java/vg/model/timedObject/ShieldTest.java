package vg.model.timedObject;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShieldTest {
    @Test
    void enableShield() {
        Shield shield = Shield.create(300, false);
        shield.enableShield();

        assertTrue(shield.isActive());
    }


    @Test
    void disableShield() {
        Shield shield = Shield.create(300, true);
        shield.disableShield();

        assertFalse(shield.isActive());
    }

    @Test
    void expiredTimeAutoDisable() {
        Shield shield = Shield.create(300, true);
        shield.updateTimer(200);
        assertTrue(shield.isActive());

        //when timer expired shield must be disabled
        shield.updateTimer(150);
        assertFalse(shield.isActive());
    }
}
