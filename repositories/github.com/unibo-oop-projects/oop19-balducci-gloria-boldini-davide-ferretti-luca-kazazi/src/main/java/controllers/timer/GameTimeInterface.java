package controllers.timer;

import controllers.camera.Camera;

public interface GameTimeInterface {

    /**
     * 
     * @param level
     * @param camera
     * method called to set the number of the level and to set the camera
     */
    void setLevel(int level, Camera camera);

    /**
     * method to start the game timer.
     */
    void start();

    /**
     * method to pause the game time.
     */
    void pause();

    /**
     * called to reset hours, mins and secs.
     */
    void reset();

    /**
     * called to stop the timer.
     */
    void stop();

    /**
     * 
     * @return getStarsGained
     * returned the number of the stars gained according to the score
     */
    int getStarsGained();

    /**
     * 
     * @return getTimerTask
     * method to get the timertask
     */
    MyTimerTask getTimerTask();

}
