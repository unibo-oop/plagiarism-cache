package controllers.levelController;

import controllers.thread.ThreadManager;
import controllers.timer.GameTime;
import view.render.RenderLevel;

public interface LevelControllerInterface {

    /**
     * @param level number
     * 
     * method to initialize level
     * 
     */
    void initLevel(int level);

    /**
     * method to start level thread.
     */
    void start();

    /**
     * method to pause level.
     */
    void stopLevel();

    /**
     * method to resume level.
     */
    void resumeLevel();

    /**
     * method to interrupt level.
     */
    void interruptLevel();

    /**
     * @return threadManager
     * 
     * method to get ThreadManager
     */
    ThreadManager getThreadManager();

    /**
     * @return gameTime
     * 
     * method to get GameTime
     */
    GameTime getTime();

    /**
     * @return level 
     * 
     * method to get level number
     */
    int getLevel();

    /**
     * @return renderLevel
     * 
     * method to get rendering class
     */
    RenderLevel getRendering();

}
