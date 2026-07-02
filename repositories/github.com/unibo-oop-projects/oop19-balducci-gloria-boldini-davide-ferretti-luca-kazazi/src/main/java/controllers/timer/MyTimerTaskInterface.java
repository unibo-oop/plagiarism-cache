package controllers.timer;

import java.awt.Graphics;

public interface MyTimerTaskInterface {

    /**
     * 
     * @return getHours spent
     */
    int getHours();

    /**
     * 
     * @return getMins mins field
     */
    int getMins();

    /**
     * 
     * @return getSecs secs field
     */
    int getSecs();

    /**
     * 
     * @param hours hours to set
     */
    void setHours(int hours);

    /**
     * 
     * @param mins to set
     */
    void setMins(int mins);

    /**
     * 
     * @param secs to set
     */
    void setSecs(int secs);

    /**
     * 
     * @param g object that allows to draw the time on the screen
     */
    void drawTime(Graphics g);

}
