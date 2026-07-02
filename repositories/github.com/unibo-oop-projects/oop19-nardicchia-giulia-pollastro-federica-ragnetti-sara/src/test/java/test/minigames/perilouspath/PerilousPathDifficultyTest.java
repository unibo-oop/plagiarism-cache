package test.minigames.perilouspath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DifficultyLevel;
import model.minigames.perilouspath.PerilousPathDifficulty;
import model.minigames.perilouspath.PerilousPathDifficultyBuilder;
import model.minigames.perilouspath.PerilousPathDifficultyBuilderImpl;

/**
 * Class to test if {@link PerilousPathDifficulty} works correctly.
 */
public class PerilousPathDifficultyTest {

    private PerilousPathDifficulty difficulty;
    private PerilousPathDifficultyBuilder db;

    /**
     * Initialize the perilous path difficulty builder at each test.
     */
    @BeforeEach
    public void init() {
        db = new PerilousPathDifficultyBuilderImpl();
    }

    private PerilousPathDifficulty initDifficulty(final DifficultyLevel difficulty) {
        return db.setDifficultyLevel(difficulty).build();
    }

    /**
     * Test the default easy difficulty.
     */
    @Test
    public void easyDefaultDifficultyTest() {
        this.difficulty = this.initDifficulty(DifficultyLevel.EASY);
        assertEquals(5, this.difficulty.getSize());
        assertEquals(10, this.difficulty.getNumMines());
    }

    /**
     * Test the easy difficulty.
     */
    @Test
    public void easyDifficultyTest() {
        this.difficulty = new PerilousPathDifficultyBuilderImpl().setDifficultyLevel(DifficultyLevel.EASY)
                                                                 .setSize(6)
                                                                 .setNumMines(12).build();
        assertEquals(6, this.difficulty.getSize());
        assertEquals(12, this.difficulty.getNumMines());
    }

    /**
     * Test the default normal difficulty.
     */
    @Test
    public void normalDefaultDifficultyTest() {
        this.difficulty = this.initDifficulty(DifficultyLevel.NORMAL);
        assertEquals(6, this.difficulty.getSize());
        assertEquals(20, this.difficulty.getNumMines());
    }

    /**
     * Test the default hard difficulty.
     */
    @Test
    public void hardDefaultDifficultyTest() {
        this.difficulty = this.initDifficulty(DifficultyLevel.HARD);
        assertEquals(7, this.difficulty.getSize());
        assertEquals(30, this.difficulty.getNumMines());
    }

    /**
     * Test exception if the current difficulty level has been initialized to null.
     */
    @Test
    public void difficultyLevelCantBeNullTest() {
        assertThrows(NullPointerException.class, () -> new PerilousPathDifficultyBuilderImpl().setDifficultyLevel(null).build());
    }

    /**
     * Test exception if the current difficulty level has been omitted.
     */
    @Test
    public void difficultyLevelNoSpecifiedTest() {
        assertThrows(IllegalStateException.class, () -> new PerilousPathDifficultyBuilderImpl().build());
    }

    /**
     * Test exception if the same object is used plus of one time.
     */
    @Test
    public void useExceptionDifficultyTest() {
        this.difficulty = this.initDifficulty(DifficultyLevel.EASY);
        assertThrows(IllegalStateException.class, () -> db.setDifficultyLevel(DifficultyLevel.EASY).setSize(6).build());
        assertThrows(IllegalStateException.class, () -> db.setDifficultyLevel(DifficultyLevel.EASY).build());
    }

    /**
     * Test exception if the default value size modified is invalid.
     */
    @Test
    public void invalidSizeExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> db.setDifficultyLevel(DifficultyLevel.EASY).setSize(3).build());
    }

    /**
     * Test exception if the default value numMines modified is invalid.
     */
    @Test
    public void invalidNumMinesExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> db.setDifficultyLevel(DifficultyLevel.EASY).setNumMines(25).build());
    }

    /**
     * Test exception if the size value has been added two times.
     */
    @Test
    public void addSizeTwoTimesExceptionTest() {
        assertThrows(IllegalStateException.class, () -> db.setDifficultyLevel(DifficultyLevel.EASY).setSize(6).setSize(7).build());
    }

    /**
     * Test exception if the numMines value has been added two times.
     */
    @Test
    public void addNumMinesTwoTimesExceptionTest() {
        assertThrows(IllegalStateException.class, () -> db.setDifficultyLevel(DifficultyLevel.EASY).setNumMines(13).setNumMines(15).build());
    }
}
