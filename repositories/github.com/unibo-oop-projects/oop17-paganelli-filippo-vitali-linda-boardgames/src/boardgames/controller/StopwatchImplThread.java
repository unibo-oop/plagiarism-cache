package boardgames.controller;

import javax.swing.SwingUtilities;

import boardgames.model.stopwatch.MatchTimeImpl;
import boardgames.model.stopwatch.StopwatchImpl;
import boardgames.view.game.GameViewImpl;

public class StopwatchImplThread extends Thread {
    
    private StopwatchImpl myStopwatch; 
    /*Volatile attribute is used to make sure the
     * thread will read the current value of this
     * variable and not a cache-stored version.*/
    private volatile boolean shouldStop;
    
    /**
     * Constructor for the class.
     */
    public StopwatchImplThread() {
        super();
        this.myStopwatch = new StopwatchImpl(new MatchTimeImpl(0, 0, 0));
        this.shouldStop = false;
    }

    /**
     * This method sets the behaviour of the executing 
     * thread: it updates the GUI with the current time.
     */
    public void run() {
        while (!shouldStop) {
            try {
                /*Difference: invokeLater() lets you call the thread directly on the EDT. The thread
                 * is put in the event queue, so that the main UI is still able to interact with the
                 * user. On the other hand, invokeAndWait() lets you call the thread on the EDT, but
                 * waits until the thread-task is complete. That means, in the meanwhile the UI will
                 * be frozen. Easly causes a deadlock (especially with Runnable interface).*/
                SwingUtilities.invokeLater(()-> { GameViewImpl.updateGUI(this.myStopwatch.getCurrentTime().getCurrentMatchTime()); this.myStopwatch.advanceOneSecond(); });
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                sleep(1000); /*Waits 1000 milliseconds = 1 seconds*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method set the variable true, in order
     * to stop the working thread. It also creates
     * a new instance of the stopwatch, so that it
     * will be possible to make it start again.
     */
    public void setCounterOff() {
        this.myStopwatch = new StopwatchImpl(new MatchTimeImpl(0, 0, 0));
        this.shouldStop = true;
    }
}