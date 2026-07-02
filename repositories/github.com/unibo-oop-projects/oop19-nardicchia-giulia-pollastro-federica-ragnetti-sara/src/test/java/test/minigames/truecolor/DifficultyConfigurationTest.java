package test.minigames.truecolor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import model.DifficultyLevel;
import model.minigames.truecolor.DifficultyConfiguration;

/**
 * Class to test if {@DifficultyConfiguration} works correctly.
 *
 */
public class DifficultyConfigurationTest {

    private static final int NUM_COLOR = 6;

    /**
     * Test Easy level configuration.
     */
    @Test
    public void testDifficultyConfigurationEasy() {
        final Optional<DifficultyConfiguration> configuration = DifficultyConfiguration
                .getConfiguration(DifficultyLevel.EASY);
        assertEquals(configuration.get().getNumColor(), 4);
        assertEquals(configuration.get().getMeanButton(), 1);
        assertEquals(configuration.get().getTrueButton(), 1);
    }

    /**
     * Test Normal level configuration.
     */
    @Test
    public void testDifficultyConfigurationNormal() {
        final Optional<DifficultyConfiguration> configuration = DifficultyConfiguration
                .getConfiguration(DifficultyLevel.NORMAL);
        assertEquals(configuration.get().getNumColor(), NUM_COLOR);
        assertEquals(configuration.get().getMeanButton(), 1);
        assertEquals(configuration.get().getTrueButton(), 2);
    }

    /**
     * Test Hard level configuration.
     */
    @Test
    public void testDifficultyConfigurationHard() {
        final Optional<DifficultyConfiguration> configuration = DifficultyConfiguration
                .getConfiguration(DifficultyLevel.HARD);
        assertEquals(configuration.get().getNumColor(), 8);
        assertEquals(configuration.get().getMeanButton(), 1);
        assertEquals(configuration.get().getTrueButton(), 2);
    }
}
