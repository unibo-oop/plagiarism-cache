package it.tbt.model.entities.items.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import it.tbt.controller.SimpleLogger;
import it.tbt.model.entities.items.Weapon;

class WeaponFactoryTest {
    private final Logger logger = SimpleLogger.getLogger("WeaponFactoryTest");
    private final WeaponFactory factory1 = WeaponFactory.getInstance();

    @Test
    void testGetInstance() {
        final WeaponFactory factory2 = WeaponFactory.getInstance();
        assertEquals(factory1.hashCode(), factory2.hashCode());
    }

    @Test
    void testGetItems() {
        assertEquals(factory1.getItems().hashCode(), factory1.getItems().hashCode());
        for (final Weapon weapon : factory1.getItems()) {
            logger.info(
                weapon.toString()
                + ", Value: " + weapon.getValue()
            );
        }
    }
}
