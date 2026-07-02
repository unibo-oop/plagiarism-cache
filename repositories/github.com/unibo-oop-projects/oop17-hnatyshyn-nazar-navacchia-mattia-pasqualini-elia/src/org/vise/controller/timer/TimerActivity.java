package org.vise.controller.timer;

import org.vise.controller.RemoteController;

import javafx.application.Platform;

/**
 * Represents the timer for the update of the friends' activities.
 *
 */
public class TimerActivity implements Runnable {
    private static final int TIME_WAIT_UPDATE_ACTIVITIES = 30000;
    private boolean running;
    private volatile Thread thread;
    private final RemoteController controller;

    /**
     * 
     * @param controller
     *          The controller.
     */
    public TimerActivity(final RemoteController controller) {
        this.controller = controller;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while (this.isRunning()) {
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.updateActivityFriends();
                    }
                });
                Thread.sleep(TimerActivity.TIME_WAIT_UPDATE_ACTIVITIES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     */
    public void start() {
        if (thread != null) {
            if (!isRunning()) {
                setRunning(true);
            }
        } else {
            thread = new Thread(this, "Activities");
            setRunning(true);
            thread.start();
        }
    }

    /**
     * 
     * @return
     *          If timer is running.
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * 
     * @param running
     *          Timer running.
     */
    public void setRunning(final boolean running) {
        this.running = running;
    }
}
