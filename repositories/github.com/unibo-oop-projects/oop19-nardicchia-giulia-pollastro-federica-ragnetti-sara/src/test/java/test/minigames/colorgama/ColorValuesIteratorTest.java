package test.minigames.colorgama;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

import model.DifficultyLevel;
import model.minigames.colorgama.ColorValuesIterator;
import model.minigames.colorgama.ColorGamaSettings;
import model.minigames.colorgama.ColorValuesImpl;
import model.minigames.colorgama.HSBColorValuesCalculator;
import model.minigames.colorgama.ColorGamaSettingsImpl;
import model.minigames.colorgama.ColorValues;
import model.minigames.colorgama.QuestionType;

class ColorValuesIteratorTest {

    private static final int NEGATIVE_SIZE = -5;
    private static final int ZERO_SIZE = -5;
    private ColorValuesIterator iterator;
    private int size;
    private int numColors;

    private void setIterator(final DifficultyLevel difficulty, final QuestionType color) {
        final ColorGamaSettings difficultyValue = new ColorGamaSettingsImpl(difficulty);
        this.size = difficultyValue.getGridSize() * difficultyValue.getGridSize();
        this.numColors = difficultyValue.getNumColor();
        final ColorValues iteratorAggregate = new ColorValuesImpl(size, this.numColors, new HSBColorValuesCalculator(difficulty));
        iteratorAggregate.resetValues(color);
        this.iterator = iteratorAggregate.getIterator();
    }

    @Test
    public void testStandardBehaviour() {
        this.setIterator(DifficultyLevel.EASY, QuestionType.COLOR);
        for (int i = 0; i < this.size; i++) {
            assertTrue(this.iterator.hasNext());
            this.iterator.next();
        }
        assertFalse(this.iterator.hasNext());
        assertFalse(this.iterator.hasNext());
    }

    @Test
    public void testNoSuchElement() {
        this.setIterator(DifficultyLevel.NORMAL, QuestionType.DARKEST);
        for (int i = 0; i < this.size; i++) {
            this.iterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> this.iterator.next());
    }

    @Test
    public void testIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ColorValuesImpl(NEGATIVE_SIZE, this.numColors, new HSBColorValuesCalculator(DifficultyLevel.EASY)));
        assertThrows(IllegalArgumentException.class, () -> new ColorValuesImpl(ZERO_SIZE, this.numColors, new HSBColorValuesCalculator(DifficultyLevel.EASY))); 
        assertThrows(IllegalArgumentException.class, () -> new ColorValuesImpl(this.size, NEGATIVE_SIZE, new HSBColorValuesCalculator(DifficultyLevel.EASY)));
        assertThrows(IllegalArgumentException.class, () -> new ColorValuesImpl(this.size, ZERO_SIZE, new HSBColorValuesCalculator(DifficultyLevel.EASY))); 
    }
}
