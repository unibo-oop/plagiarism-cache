package controller.setting;

/**
 * this object must be instantiate when the Game is in Timer mode
 * must be instantiate in another thread
 * 
 * @author Alex Ravaglia
 *
 */
public class TimeActive extends Time{

    /**
     * start the timer of the game
     */
    public void startTimer(){
        startTimer2();
    }
    
    /**
     * stop the timer that is running and start the other
     */
    public void stopTimer(){
        if(t1IsRunning()){
            stopTimer1();
        }
        else{
            stopTimer2();		
        }
    }


}
