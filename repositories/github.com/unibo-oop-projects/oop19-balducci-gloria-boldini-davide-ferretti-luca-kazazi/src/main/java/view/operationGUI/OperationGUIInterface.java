package view.operationGUI;

import javax.swing.JTextArea;

/**
 * Controller of GUI.
 *
 */
public interface OperationGUIInterface {

    /**
     * @param name name of the player
     *
     * Load window for select level.
     * 
     */
    void playButton(String name);

    /**
     * @param out the JTextArea on witch the scores will be shown
     * 
     * Reads the file of each scores and show in a JTextArea.
     * 
     */
    void showScores(JTextArea out);

    /**
     * @param out where shows the leaderboard
     *
     * Creates a window with the best time of each level.
     * 
     */
    void showLeaderboard(JTextArea out);

    /**
     * Creates a windowDialog with explanation of the game.
     */
    void showInfo();

    /**
     * Creates a windowDialog with the instruction for playing.
     */
    void showHowToPlay();

    /**
     * Creates a windowDialog with the legend of enemies.
     */
    void showEnemy();

    /**
     * Creates a windowDialog with the legend of powerUp and debuff.
     */
    void showLegend();

    /**
     * Updates score file and returns to main menu.
     */
    void exitButton();

    /**
     * Returns to last menu.
     */
    void goBackButton();

    /**
     * Writes on file the name of the player.
     */
    void setName();

}
