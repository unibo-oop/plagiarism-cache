package javaclimber.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.persistence.api.SaveManager;
import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.persistence.impl.SaveManagerImpl;
import javaclimber.TestCostants;

/**
 * Tests for {@link SaveManager}.
 */
class SaveManagerTest {

    private static final String TEST_FILE = "test_save.json";
    private final SaveManager saveManager = new SaveManagerImpl(TEST_FILE);

    /**
     * After tests delete the file if exist.
     */
    @AfterEach
    void tearDown() {
        final File file = new File(TEST_FILE);
        if (file.exists()) {
            final boolean isDeleted = file.delete();
            if (!isDeleted) {
                file.deleteOnExit();
            }
        }
    }

    /**
     * Tests for saving, loading and check the correction of data.
     */
    @Test
    void testSaveAndLoad() {
        final SaveState originalState = new SaveState(TestCostants.COINS_1500, TestCostants.HIGHEST_SCORE_5000,
                Set.of("s_basic", "s_astro"), Map.of("pt_jump1", 3),
                "s_atro", 2, 1);
        saveManager.save(originalState);

        final Optional<SaveState> loadedState = saveManager.load();
        assertTrue(loadedState.isPresent());

        assertEquals(originalState.getCoins(), loadedState.get().getCoins());
        assertEquals(originalState.getHighestScore(), loadedState.get().getHighestScore());
        assertEquals(originalState.getOwnedItems(), loadedState.get().getOwnedItems());
        assertEquals(originalState.getConsumables(), loadedState.get().getConsumables());
        assertEquals(originalState.getSelectedSkin(), loadedState.get().getSelectedSkin());
        assertEquals(originalState.getSelectedJumpLevel(), loadedState.get().getSelectedJumpLevel());
        assertEquals(originalState.getSelectedSpeedLevel(), loadedState.get().getSelectedSpeedLevel());
    }

    /**
     * Tests for load a missing file.
     */
    @Test
    void testLoadMissingFile() {
        final SaveManager missingFileManager = new SaveManagerImpl("non_existent_file.json");
        final Optional<SaveState> result = missingFileManager.load();
        assertTrue(result.isEmpty());
    }
}
