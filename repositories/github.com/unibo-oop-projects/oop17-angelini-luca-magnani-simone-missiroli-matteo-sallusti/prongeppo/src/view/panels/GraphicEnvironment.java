package view.panels;

import javafx.util.Pair;

/**
 * This interface represents the View of the game.
 * @author Missi
 *
 */
public interface GraphicEnvironment {
    /**
     * Updates the View.
     */
    void render();

    /**
     * Updates the score represented.
     * @param score **Pair(scoreTeam1, scoreTeam2)**
     */
    void updateScore(Pair<Integer, Integer> score);

    /**
     * Closes the game View.
     * @param finalScore **Pair(scoreTeam1, scoreTeam2)**
     * @param numCombo **Pair(numComboTeam1, numComboTeam2)**
     */
    void close(Pair<Integer, Integer> finalScore, Pair<Integer, Integer> numCombo);
}
