package bubbleshooter.model.highscore;

import java.io.File;
import bubbleshooter.model.game.level.LevelType;
import javafx.collections.ObservableList;

/**
 * Interface of the {@link HighscoreStore] of the game. It's used to read, save
 * and modify the scores from a file.
 */
public interface HighscoreStore {

    /**
     * Method for get the file where scores are saved.
     * @return the file where the scores are saved.
     */
    File getFile();

    /**
     * Method for add a score for a game modality.
     * @param score The current {@link HighscoreStructure} to save.
     */
    void addScore(HighscoreStructure score);

    /**
     * Method to have a list of scores for a specific game modality.
     * @param gameMode The game modality which we want the scores.
     * @return the scores for a game modality.
     */
    ObservableList<HighscoreStructure> getHighscoresForModality(LevelType gameMode);

    /**
     * Method used for clean file.
     */
    void cleanFile();

}
