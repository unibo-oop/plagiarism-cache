package it.tbt.model.entities.items.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import it.tbt.controller.SimpleLogger;
import it.tbt.model.entities.items.Potion;

class PotionFactoryTest {
    private final Logger logger = SimpleLogger.getLogger("PotionFactoryTest");
    private final PotionFactory factory1 = PotionFactory.getInstance();

    @Test
    void testGetInstance() {
        final PotionFactory factory2 = PotionFactory.getInstance();
        assertEquals(factory1.hashCode(), factory2.hashCode());
    }

    @Test
    void testGetItems() {
        assertEquals(factory1.getItems().hashCode(), factory1.getItems().hashCode());
        for (final Potion potion : factory1.getItems()) {
            logger.info(
                potion.toString()
                + ", Value: " + potion.getValue()
            );
        }
    }
}
