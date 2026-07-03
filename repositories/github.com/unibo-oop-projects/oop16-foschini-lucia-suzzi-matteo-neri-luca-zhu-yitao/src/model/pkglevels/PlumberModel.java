package model.pkglevels;

import java.awt.GridBagConstraints;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.pkgplayer.Player;

/**
 * Model interface.
 * 
 * 
 *
 */
public interface PlumberModel {
    /**
     * method that saves the player's reached level, best score.
     */
    void saveUserInfo();

    /**
     * method that checks if the string is a level number.
     * 
     * @param s
     *            string to check
     * @return true o false
     */
    boolean isALvlNumber(String s);

    /**
     * method to read the top 10 statistics for a level.
     * 
     * @param levelNumber
     *            level to read
     * @param f
     *            file that contains the scores
     * @return number of player readed
     */
    int readTopTenScores(String levelNumber, File f);

    /**
     * Saves the current player score for the current player.
     */
    void saveLevelsScores();

    /**
     * REads the solutions, which is necessary to create the level layout.
     * 
     * @param f
     *            file with the solutions
     */
    void readSolutions(File f);

    /**
     * Method that create the level layout.
     * 
     * @param lines
     *            level grid lines
     * @param columns
     *            level grid columns
     * @param panel
     *            panel that contains the grid
     * @param x
     *            x axis
     * @param y
     *            y axis
     */
    void createLevelDesign(int lines, int columns, JPanel panel, int x, int y);

    /**
     * Returns the solution behaviour.
     * 
     * @return solution listener
     */
    ActionListener solutionListener();

    /**
     * Returns the standard behaviour for a grid button.
     * 
     * @return the listener
     */
    ActionListener basicButtonListener();

    /**
     * Method that sets some standard values.
     * 
     */
    void inizializeCountersStatistics();

    /**
     * Checks if the player has reached a new level.
     * 
     * @param p
     *            current player
     */
    void checkIncreaseLevel(Player p);

    /**
     * Method that enable to access the next level.
     * 
     * @param title
     *            title to show
     * @param message
     *            message to show
     */
    void accessNextLevel(String title, String message);

    /**
     * Visualized if the player has failed the level.
     * 
     * @param title
     *            shown on the GUI
     * @param message
     *            show on the GUI
     */
    void showFailureOptions(String title, String message);

    /**
     * Used to get the grid lines.
     * 
     * @return line number
     */
    int getLines();

    /**
     * Used to get the grid columns.
     * 
     * @return columns number
     */
    int getColumns();

    /**
     * Used to populate the grid at first boot or at failure.
     * 
     * @param index
     *            used to get the image
     * @param btnToset
     *            button which image must be changed
     * @param gb
     *            gridbagconstraint
     * @param p
     *            panel
     * @param setX
     *            x axis
     * @param setY
     *            y axis
     * @param newX
     *            new dimension(width)
     * @param newY
     *            new dimension(height)
     */
    void setLevelStartConfig(int index, JButton btnToset, GridBagConstraints gb, JPanel p, int setX, int setY, int newX,
            int newY);
     void resetMaps();
}
