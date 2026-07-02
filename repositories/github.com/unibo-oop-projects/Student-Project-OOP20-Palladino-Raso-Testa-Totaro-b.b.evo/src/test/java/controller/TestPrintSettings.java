package controller;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import controller.utilities.IOSettings;
import model.settings.GameSettingsBuilderImpl;
import model.settings.Settings;

class TestPrintSettings {

    private Settings gameSettings;
    private static final String SEP = System.getProperty("file.separator");
    private static final String RES_PATH = System.getProperty("user.home");
    public static final String SETTINGS_PATH = RES_PATH
                                               + SEP
                                               + ".BrickBreakerEvo" 
                                               + SEP
                                               + "Settings"
                                               + SEP
                                               + "settings.json";

    @BeforeEach
    void initSettings() {
        this.gameSettings = new GameSettingsBuilderImpl().defaultSettings().build();
    }

    @Test
    void testPrintSettings() {
        assertDoesNotThrow(() -> {
            IOSettings.printInJsonFormat(SETTINGS_PATH, gameSettings);
        });
    }

    @Test
    void testReadSettings() {
        assertDoesNotThrow(() -> {
            final var readSettings = IOSettings.readSettings(SETTINGS_PATH);
            assertEquals(this.gameSettings, readSettings);
        });

    }

}
