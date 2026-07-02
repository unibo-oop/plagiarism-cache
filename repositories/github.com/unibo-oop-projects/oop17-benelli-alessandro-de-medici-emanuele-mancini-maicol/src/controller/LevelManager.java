package controller;

import model.level.LevelImpl;

/**
 * LevelManagerImpl interface.
 *
 */
public interface LevelManager {

    /**
     * Chooses which level should be displayed according to levelNumber field.
     * 
     * @return the level that should be displayed
     */
    LevelImpl chooseLevel();

    /**
     * Resets levels clearing the grid and adding again the default level's elements
     * to the cleared grid and root.
     */
    void resetLevel();

    /**
     * Getter of the current level number.
     * 
     * @return levelNumber
     */
    int getLevelNumber();

    /**
     * Setter of levelNumber.
     * 
     * @param levelNumber
     *            of the level that has to be set
     */
    void setLevelNumber(int levelNumber);

    /**
     * Increases current level number in order to become the next one.
     */
    void nextLevel();

    /**
     * Resets the value of current level number to the first one.
     */
    void resetLevelNumber();
}
