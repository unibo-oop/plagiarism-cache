package model.pkglevels;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.pkgplayer.Player;

/**
 * Interface for the GameWindowModelClass.
 * 
 * 
 *
 */
public interface GameWindowModel {
    /**
     * Enables the view associated.
     * 
     */
    void enableWindow();

    /**
     * Method used to read all level numbers in the file, both custom and fixed.
     * 
     * @param inputFile
     *            file to read
     * @return the list that contains all level numbers
     */
    List<Integer> readLevels(File inputFile);

    /**
     * MEthod used to create the button level list in the panel given.
     * 
     * @param inputPanel
     *            where to put the buttons
     * @param inputMap
     *            which will contains the pair of buttons and values
     * @param inputLevelList
     *            list of level numbers
     * @param inputFile
     *            file that contains the levels
     * @param isCustom
     *            is a custom level
     */
    void createLevelsButton(JPanel inputPanel, Map<JButton, Integer> inputMap, List<Integer> inputLevelList,
            File inputFile, boolean isCustom);

    /**
     * Action listeners for the customLevel button.
     * 
     * @return the action listener
     */
    ActionListener customLevelClicked();

    /**
     * Starts the custom level creator.
     * 
     */
    void startCustomLevel();

    /**
     * Closes the view associated to this model.
     * 
     */
    void closeWindow();

    /**
     * Return the max level number, used to build the menù panel.
     * 
     * @param custom
     *            is a custom level
     * @return max level
     */
    int getMaxLevel(boolean custom);

    /**
     * Method that checks the playable levels for the current user.
     * 
     * @param p12
     */
    void checkPassedLevel(Player p);

    /**
     * MEthod that starts a standard level.
     * 
     * @param lvl
     *            level number
     * @param custom
     *            state if it's a custom level
     */
    void startGameLevel(int lvl, boolean custom);

}
