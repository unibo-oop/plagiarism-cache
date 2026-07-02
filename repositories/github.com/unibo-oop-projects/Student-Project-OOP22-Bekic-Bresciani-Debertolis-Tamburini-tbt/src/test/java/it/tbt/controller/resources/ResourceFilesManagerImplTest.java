package it.tbt.controller.resources;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import it.tbt.controller.SimpleLogger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ResourceFilesManagerImplTest {
    private static final String CLASS_NAME = ResourceFilesManagerImplTest.class.getName();
    private static final Logger LOGGER = SimpleLogger.getLogger(CLASS_NAME);
    private static final String FILENAME = "test.txt";
    private static ResourceFilesManagerImpl resource = new ResourceFilesManagerImpl();

    @Test
    @Order(1)
    void testGetConfigFolderPath() {
        assertNotNull(resource.getConfigFolderPath());
        assertNotEquals("", resource.getConfigFolderPath());
        LOGGER.info("Config folder: " + resource.getConfigFolderPath());
    }

    @Test
    @Order(1)
    void testGetResourceOutputStream() {
        assertDoesNotThrow(() -> resource.getResourceOutputStream(FILENAME).close());
    }

    @Test
    @Order(1)
    void testDirExistsInPath() {
        // "" = the config directory itself
        assertTrue(resource.dirExistsInPath(""));
        // .. = the parent of the config directory
        assertTrue(resource.dirExistsInPath(".."));
        assertFalse(resource.dirExistsInPath("nonexistant"));
    }

    @Test
    @Order(2)
    void testWriteResource() {
        assumeTrue(resource.dirExistsInPath(""));
        assertDoesNotThrow(
            () -> resource.writeResource(FILENAME, "test\n".getBytes(StandardCharsets.UTF_8))
        );
    }

    @Test
    @Order(2)
    void testMakeDirInPath() {
        assertDoesNotThrow(() -> resource.makeDirInPath("tbt/entities/"));
        assertTrue(resource.dirExistsInPath("tbt/entities/"));
    }

    @Test
    @Order(2)
    void testFileExistsInPath() {
        assumeTrue(resource.dirExistsInPath(""));
        assertTrue(resource.fileExistsInPath(FILENAME));
    }

    @Test
    @Order(3)
    void testGetResourceInputStream() {
        assumeTrue(resource.dirExistsInPath(""));
        assertDoesNotThrow(() -> resource.getResourceInputStream(FILENAME).close());
        assertThrows(
            FileNotFoundException.class,
            () -> resource.getResourceInputStream("nonexistant").close()
        );
    }

    @Test
    @Order(3)
    void testReadResource() {
        assumeTrue(resource.dirExistsInPath(""));
        assertDoesNotThrow(() -> resource.readResource(FILENAME));

        try {
            final String str = new String(resource.readResource(FILENAME), StandardCharsets.UTF_8);
            assertEquals("test\n", str);
        } catch (IOException e) {
            LOGGER.throwing(CLASS_NAME, "testReadResource", e);
        }
        assertThrows(
            FileNotFoundException.class,
            () -> resource.readResource("nonexistant")
        );
    }

    @AfterAll
    static void cleanup() {
        assumeTrue(resource.fileExistsInPath(FILENAME));
        final File f = new File(resource.getConfigFolderPath() + File.separator + FILENAME);
        if (f.delete()) {
            LOGGER.info("Test file " + f.getPath() + " deleted successfully");
        } else {
            LOGGER.info("Failed to delete the test file " + f.getPath());
        }
    }
}
