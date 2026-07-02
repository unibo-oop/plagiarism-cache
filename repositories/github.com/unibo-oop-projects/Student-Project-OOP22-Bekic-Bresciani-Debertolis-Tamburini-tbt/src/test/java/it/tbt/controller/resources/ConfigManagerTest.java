package it.tbt.controller.resources;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class ConfigManagerTest {

    @Test
    void testParseJsonConfig() {
        assertNotEquals(
            ConfigManager.parseJsonConfig("tbt/entities/skills.json", List.class),
            Optional.empty()
        );
    }
}
