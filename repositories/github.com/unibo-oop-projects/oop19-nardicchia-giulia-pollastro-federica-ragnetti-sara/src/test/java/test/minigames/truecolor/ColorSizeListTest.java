package test.minigames.truecolor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.DifficultyLevel;
import model.minigames.truecolor.DifficultyConfiguration;
import model.minigames.truecolor.DifficultyStrategy;
import model.minigames.truecolor.DifficultyStrategyImpl;
import model.minigames.truecolor.StatusColor;
import model.minigames.truecolor.TrueColorModel;
import model.minigames.truecolor.TrueColorModelImpl;

/**
 * Class to test if the model receive the correct configurations.
 *
 */
public class ColorSizeListTest {

    @Test
    public void testNumberButtonEasyDifficulty() {
        final DifficultyConfiguration configuration = DifficultyConfiguration.getConfiguration(DifficultyLevel.EASY)
                .orElseGet(() -> DifficultyConfiguration.EASY);
        final DifficultyStrategy strategy = new DifficultyStrategyImpl(configuration);
        final TrueColorModel modelGame = new TrueColorModelImpl(DifficultyLevel.EASY, strategy);

        assertEquals(configuration.getMeanButton(), modelGame.getRandomColorMap().get(StatusColor.MEANCOLOR).size());
        assertEquals(configuration.getTrueButton(), modelGame.getRandomColorMap().get(StatusColor.TRUECOLOR).size());
    }

    @Test
    public void testNumberButtonNormalDifficulty() {
        final DifficultyConfiguration configuration = DifficultyConfiguration.getConfiguration(DifficultyLevel.NORMAL)
                .orElseGet(() -> DifficultyConfiguration.EASY);
        final DifficultyStrategy strategy = new DifficultyStrategyImpl(configuration);
        final TrueColorModel modelGame = new TrueColorModelImpl(DifficultyLevel.NORMAL, strategy);

        assertEquals(1, modelGame.getRandomColorMap().get(StatusColor.MEANCOLOR).size());
        assertEquals(2, modelGame.getRandomColorMap().get(StatusColor.TRUECOLOR).size());
        assertEquals(configuration.getMeanButton(), modelGame.getRandomColorMap().get(StatusColor.MEANCOLOR).size());
        assertEquals(configuration.getTrueButton(), modelGame.getRandomColorMap().get(StatusColor.TRUECOLOR).size());
    }

    @Test
    public void testNumberButtonHardDifficulty() {
        final DifficultyConfiguration configuration = DifficultyConfiguration.getConfiguration(DifficultyLevel.HARD)
                .orElseGet(() -> DifficultyConfiguration.EASY);
        final DifficultyStrategy strategy = new DifficultyStrategyImpl(configuration);
        final TrueColorModel modelGame = new TrueColorModelImpl(DifficultyLevel.HARD, strategy);

        assertEquals(1, modelGame.getRandomColorMap().get(StatusColor.MEANCOLOR).size());
        assertEquals(2, modelGame.getRandomColorMap().get(StatusColor.TRUECOLOR).size());
        assertEquals(configuration.getMeanButton(), modelGame.getRandomColorMap().get(StatusColor.MEANCOLOR).size());
        assertEquals(configuration.getTrueButton(), modelGame.getRandomColorMap().get(StatusColor.TRUECOLOR).size());
    }
}
