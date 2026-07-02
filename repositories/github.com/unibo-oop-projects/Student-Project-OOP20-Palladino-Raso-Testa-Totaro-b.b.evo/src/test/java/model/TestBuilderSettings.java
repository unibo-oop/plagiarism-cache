package model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.settings.GameSettingsBuilderImpl;
import model.settings.GameSettingsImpl;
import model.utilities.Difficulty;

class TestBuilderSettings {

    private GameSettingsBuilderImpl builder;

    @BeforeEach
    void initBuilder() {
        this.builder = new GameSettingsBuilderImpl();
    }

    @Test
    void testDefaultSettings() {
        this.builder.defaultSettings();
        this.builder.build();
    }

    @Test
    void testCorrectBuild() {
        assertDoesNotThrow(() -> {
            this.builder.enableSoundFx(true);
            this.builder.enableMusic(true);
            this.builder.upAndDown(false);
            this.builder.leftAndRight(true);
            this.builder.difficulty(Difficulty.NORMAL);
            final var settings = this.builder.build();
            final GameSettingsImpl gameSettings = new GameSettingsImpl(true, true, true, false, Difficulty.NORMAL);
            assertEquals(gameSettings, settings);
        });
    }

    @Test
    void testUncorrectBuild() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.builder.enableSoundFx(true);
            this.builder.enableMusic(true);
            this.builder.upAndDown(true);
            this.builder.leftAndRight(true);
            this.builder.difficulty(Difficulty.NORMAL);
            this.builder.build();
        });
    }

    @Test
    void testBuilderWhitNull() {
        assertDoesNotThrow(() -> {
            this.builder.difficulty(Difficulty.HARD);
            this.builder.build();
        });
    }

    @Test
    void testDifficultyNull() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.builder.difficulty(null);
            this.builder.build();
        });
    }

    @Test
    void testNotNullableBuilder() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.builder.build();
        });
    }

}
