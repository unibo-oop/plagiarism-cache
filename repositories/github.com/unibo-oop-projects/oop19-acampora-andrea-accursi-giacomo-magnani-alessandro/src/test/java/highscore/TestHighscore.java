package highscore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import bubbleshooter.model.game.level.LevelType;
import bubbleshooter.model.highscore.HighscoreStore;
import bubbleshooter.model.highscore.HighscoreStoreImpl;
import bubbleshooter.model.highscore.HighscoreStructure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * JUnit Test class to test the {@link HighscoreStoreImpl} of the Game.
 */
public class TestHighscore {

    private final HighscoreStore highscoreStore = new HighscoreStoreImpl();
    private static final int POINTS = 500;
    private static final int LIMIT = 10;
    private static final String PLAYER = "Player";

    /**
     * Method to test if a {@link HighscoreStore} add a new {@link HighscoreStructure}
     * to the basic mode highscores.
     */
    @Test
    public final void testAddABasicModeScore() {
        this.highscoreStore.cleanFile();

        final ObservableList<HighscoreStructure> rightList = FXCollections.observableArrayList();
        final ObservableList<HighscoreStructure> scoreList = FXCollections.observableArrayList();

        this.highscoreStore.addScore(new HighscoreStructure(PLAYER, POINTS, LevelType.BASICMODE));
        scoreList.addAll(this.highscoreStore.getHighscoresForModality(LevelType.BASICMODE));

        rightList.add(new HighscoreStructure(PLAYER, POINTS, LevelType.BASICMODE));

        assertTrue(rightList.get(0).getName().equals(scoreList.get(0).getName()));
        assertEquals(rightList.get(0).getScore(), scoreList.get(0).getScore());
     }

    /**
     * Method to test if a {@link HighscoreStore} add a new {@link HighscoreStructure}
     * to the survival mode highscores.
     */
    @Test
    public final void testAddASurvivalModeScore() {
        this.highscoreStore.cleanFile();

        final ObservableList<HighscoreStructure> rightList = FXCollections.observableArrayList();
        final ObservableList<HighscoreStructure> scoreList = FXCollections.observableArrayList();

        this.highscoreStore.addScore(new HighscoreStructure(PLAYER, POINTS, LevelType.SURVIVALMODE));
        scoreList.addAll(this.highscoreStore.getHighscoresForModality(LevelType.SURVIVALMODE));

        rightList.add(new HighscoreStructure(PLAYER, POINTS, LevelType.SURVIVALMODE));

        assertTrue(rightList.get(0).getName().equals(scoreList.get(0).getName()));
        assertEquals(rightList.get(0).getScore(), scoreList.get(0).getScore());
     }

    /**
     * Method to test if a {@link HighscoreStore} add some new {@link HighscoreStructure}
     * to the basic mode highscores in the correct order.
     */
    @Test
    public final void testOrderBasicModeScore() {
        this.highscoreStore.cleanFile();

        final ObservableList<HighscoreStructure> rightList = FXCollections.observableArrayList();
        final ObservableList<HighscoreStructure> scoreList = FXCollections.observableArrayList();

        this.highscoreStore.addScore(new HighscoreStructure(PLAYER, POINTS * 2, LevelType.BASICMODE));
        this.highscoreStore.addScore(new HighscoreStructure(PLAYER + 1, POINTS, LevelType.BASICMODE));
        this.highscoreStore.addScore(new HighscoreStructure(PLAYER + 2, POINTS * 3, LevelType.BASICMODE));

        scoreList.addAll(this.highscoreStore.getHighscoresForModality(LevelType.BASICMODE));

        rightList.add(new HighscoreStructure(PLAYER + 2, POINTS * 3, LevelType.BASICMODE));
        rightList.add(new HighscoreStructure(PLAYER, POINTS * 2, LevelType.BASICMODE));
        rightList.add(new HighscoreStructure(PLAYER + 1, POINTS, LevelType.BASICMODE));

        for (int i = 0; i < rightList.size(); i++) {
            assertTrue(rightList.get(i).getName().equals(scoreList.get(i).getName()));
            assertEquals(rightList.get(i).getScore(), scoreList.get(i).getScore());
        }
     }

    /**
     * Method to test if a {@link HighscoreStore} add some new {@link HighscoreStructure}
     * to the survival mode highscores in the correct order.
     */
    @Test
    public final void testOrderSurvivalModeScore() {
        this.highscoreStore.cleanFile();

        final ObservableList<HighscoreStructure> rightList = FXCollections.observableArrayList();
        final ObservableList<HighscoreStructure> scoreList = FXCollections.observableArrayList();

        this.highscoreStore.addScore(new HighscoreStructure(PLAYER, POINTS * 2, LevelType.SURVIVALMODE));
        this.highscoreStore.addScore(new HighscoreStructure(PLAYER + 1, POINTS, LevelType.SURVIVALMODE));
        this.highscoreStore.addScore(new HighscoreStructure(PLAYER + 2, POINTS * 3, LevelType.SURVIVALMODE));

        scoreList.addAll(this.highscoreStore.getHighscoresForModality(LevelType.SURVIVALMODE));

        rightList.add(new HighscoreStructure(PLAYER + 2, POINTS * 3, LevelType.SURVIVALMODE));
        rightList.add(new HighscoreStructure(PLAYER, POINTS * 2, LevelType.SURVIVALMODE));
        rightList.add(new HighscoreStructure(PLAYER + 1, POINTS, LevelType.SURVIVALMODE));

        for (int i = 0; i < rightList.size(); i++) {
            assertTrue(rightList.get(i).getName().equals(scoreList.get(i).getName()));
            assertEquals(rightList.get(i).getScore(), scoreList.get(i).getScore());
        }
     }

    /**
     * Method to test if a {@link HighscoreStore} remove {@link HighscoreStructure} 
     * from the basic mode list if there are over than 10 items.
     */
    @Test
    public final void testLimitForBasicMode() {
        this.highscoreStore.cleanFile();

        final ObservableList<HighscoreStructure> rightList = FXCollections.observableArrayList();
        final ObservableList<HighscoreStructure> scoreList = FXCollections.observableArrayList();

        for (int i = 0; i < LIMIT * 2; i++) {
            this.highscoreStore.addScore(new HighscoreStructure(PLAYER + i, POINTS + i, LevelType.BASICMODE));
        }
        scoreList.addAll(this.highscoreStore.getHighscoresForModality(LevelType.BASICMODE));

        for (int j = 0; j < LIMIT; j++) {
            rightList.add(new HighscoreStructure(PLAYER + j, POINTS + j, LevelType.BASICMODE));
        }

        assertFalse(scoreList.size() > LIMIT);
        assertEquals(rightList.size(), scoreList.size());
     }

    /**
     * Method to test if a {@link HighscoreStore} remove {@link HighscoreStructure} 
     * from the survival mode list if there are over than 10 items.
     */
    @Test
    public final void testLimitForSurvivalMode() {
        this.highscoreStore.cleanFile();

        final ObservableList<HighscoreStructure> rightList = FXCollections.observableArrayList();
        final ObservableList<HighscoreStructure> scoreList = FXCollections.observableArrayList();

        for (int i = 0; i < LIMIT * 2; i++) {
            this.highscoreStore.addScore(new HighscoreStructure(PLAYER + i, POINTS + i, LevelType.SURVIVALMODE));
        }
        scoreList.addAll(this.highscoreStore.getHighscoresForModality(LevelType.SURVIVALMODE));

        for (int j = 0; j < LIMIT; j++) {
            rightList.add(new HighscoreStructure(PLAYER + j, POINTS + j, LevelType.SURVIVALMODE));
        }

        assertFalse(scoreList.size() > LIMIT);
        assertEquals(rightList.size(), scoreList.size());
     }
}
