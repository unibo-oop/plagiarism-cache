package it.tbt.controller.resources;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import it.tbt.controller.SimpleLogger;

class SystemResourceManagerImplTest {
    private final String className = getClass().getName();
    private final Logger logger = SimpleLogger.getLogger(className);
    private final SystemResourceManagerImpl resource = new SystemResourceManagerImpl();

    @Test
    void testGetResourceInputStream() {
        assertDoesNotThrow(() -> resource.getResourceInputStream("empty.json"));

        assertDoesNotThrow(() -> resource.getResourceInputStream("tbt/notempty.json"));

        assertDoesNotThrow(() -> resource.getResourceInputStream("tbt/entities/skills.json"));
    }

    @Test
    void testReadResource() {
        assertDoesNotThrow(() -> resource.readResource("empty.json"));
        try {
            assertEquals(
                "",
                new String(resource.readResource("empty.json"), StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            logger.throwing(className, "testReadResource", e);
        }

        assertDoesNotThrow(() -> resource.readResource("tbt/notempty.json"));
        try {
            assertEquals(
                "{\"name\": \"test\"}",
                new String(resource.readResource("tbt/notempty.json"), StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            logger.throwing(className, "testReadResource", e);
        }

        assertDoesNotThrow(() -> resource.readResource("tbt/entities/skills.json"));
    }
}
