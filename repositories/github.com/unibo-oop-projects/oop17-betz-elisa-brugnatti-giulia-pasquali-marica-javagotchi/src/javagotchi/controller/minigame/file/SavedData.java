package javagotchi.controller.minigame.file;

import java.util.List;
import java.util.Map;

import javagotchi.view.minigame.component.GameButton;

/**
 * 
 * @author marica
 *
 */
public interface SavedData {

    /**
     * Gets SaveGame.
     * 
     * @return path of saved game file
     */
    String getSaveGame();

    /**
     * Indicates if a saved game exists.
     * 
     * @return true if exists the file of saved game
     */
    boolean existFileSaveGame();

    /**
     * Saves some elements of the game into a file.
     * 
     * @param score
     *            current score
     * @param gameButtons
     *            current button layout
     * @param sec
     *            current timer
     */
    void writeGame(Integer score, List<GameButton> gameButtons, Integer sec);

    /**
     * Reads the saved game from file.
     * 
     * @return list of object read from file
     */
    List<Object> readGameSaved();

    /**
     * Deletes saved game file if it exists.
     */
    void deleteGameSaved();

    /**
     * Getter for FileBestScore.
     * 
     * @return path of best score file
     */
    String getFileBestScore();

    /**
     * Indicates if file of best score exists.
     * 
     * @return true if file of best score exist
     */
    boolean existFileBestScore();

    /**
     * Reads the best scores of all the javagotchi and sorts them in descending
     * order.
     * 
     * @return ordered map of best scores of all the javagotchi
     */
    Map<String, Integer> readBestScore();

    /**
     * Writes the best scores of all the javagotchi.
     * 
     * @param bestScoreMap
     *            map of best scores of all the javagotchi
     */
    void writeBestScore(Map<String, Integer> bestScoreMap);
}
