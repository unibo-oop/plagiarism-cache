package it.tbt.model.entities.items.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import it.tbt.controller.SimpleLogger;
import it.tbt.model.entities.items.Antidote;

class AntidoteFactoryTest {
    private final Logger logger = SimpleLogger.getLogger("AntidoteFactoryTest");
    private final AntidoteFactory factory1 = AntidoteFactory.getInstance();

    @Test
    void testGetInstance() {
        final AntidoteFactory factory2 = AntidoteFactory.getInstance();
        assertEquals(factory1.hashCode(), factory2.hashCode());
    }

    @Test
    void testGetItems() {
        assertEquals(factory1.getItems().hashCode(), factory1.getItems().hashCode());
        assertEquals(
            factory1.getItems().iterator().next().hashCode(),
            factory1.getAntidote().hashCode()
        );
    }

    @Test
    void testGetAntidote() {
        assertEquals(factory1.getAntidote().hashCode(), factory1.getAntidote().hashCode());
        final Antidote antidote = factory1.getAntidote();
        logger.info(
            antidote.toString()
            + ", Value: " + antidote.getValue()
        );
    }
}
