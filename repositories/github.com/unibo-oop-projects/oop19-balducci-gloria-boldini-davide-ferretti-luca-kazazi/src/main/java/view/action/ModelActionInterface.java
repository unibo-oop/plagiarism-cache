package view.action;

import controllers.levelController.LevelController;

public interface ModelActionInterface {

    /**
     * @param level
     * 
     *              method to initialize given level number
     * 
     */
    void initLevel(int level);

    /**
     * @return levelController
     * 
     *         getter level controller
     * 
     */
    LevelController getLevelController();

    /**
     * method to resume level.
     */
    void resumeLevel();

    /**
     * method to restart level.
     */
    void restartLevel();

    /**
     * method to interrupt level.
     */
    void interruptLevel();

    /**
     * method to load next level.
     */
    void nextLevel();

}
