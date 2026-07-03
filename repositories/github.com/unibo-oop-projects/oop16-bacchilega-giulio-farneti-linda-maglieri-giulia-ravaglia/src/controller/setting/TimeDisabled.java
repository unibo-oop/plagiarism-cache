package controller.setting;

/**
 * this object must be instantiate when the game it is NOT in Timer mode
 * 
 * @author Alex Ravaglia
 */
public class TimeDisabled extends Time{


    /**
     * nothing happen because the timer should NOT start or stop
     */
    public void startTimer(){}

    public void stopTimer(){}


}
