package it.tbt.model.entities.items.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import it.tbt.controller.SimpleLogger;
import it.tbt.model.entities.items.Armor;

class ArmorFactoryTest {
    private final Logger logger = SimpleLogger.getLogger("ArmorFactoryTest");
    private final ArmorFactory factory1 = ArmorFactory.getInstance();

    @Test
    void testGetInstance() {
        final ArmorFactory factory2 = ArmorFactory.getInstance();
        assertEquals(factory1.hashCode(), factory2.hashCode());
    }

    @Test
    void testGetItems() {
        assertEquals(factory1.getItems().hashCode(), factory1.getItems().hashCode());
        for (final Armor armor : factory1.getItems()) {
            logger.info(
                armor.toString()
                + ", Value: " + armor.getValue()
            );
        }
    }
}
