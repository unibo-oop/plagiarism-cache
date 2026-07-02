package vg.model.levels;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class levelGeneratorTest {

    levelGenerator t = new levelGenerator();

    @Test
    void serializeDefaults() throws IOException {
        t.serializeDefaults();
    }

    @Test
    void deserializeLevel() throws IOException, ClassNotFoundException {
        t.deserializeLevel("1");
    }
}