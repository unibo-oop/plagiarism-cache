package controller.loader;

import controller.levels.Levels;

/**
 * Interface to read and write on files.
 */
public interface FileController {

    /**
     * Method to read a level state from file.
     * @param level
     *          the current {@link Levels} to load.
     */
    void loadLevel(Levels level);

    /**
     * Method to save a level state on file.
     */
    // void saveLevel();

}
