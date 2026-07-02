package it.unibo.pacman.controller.utilities;

import it.unibo.pacman.model.entities.Movable;
import it.unibo.pacman.model.utilities.Status;
/**
 * Class for handle the change of entity's status.
 */
public class MyTimer extends Thread {
    private final Movable entity;
    private static final int TIMECHASED = 3;
    private static final int TIMECHASEDEND = 5;
    private boolean isRunning;
    /**
     * Create a {@link MyTimer}.
     * @param entity the entity to be syncronized.
     */
    public MyTimer(final Movable entity) {
        this.entity = entity;
        this.isRunning = false;
    }
    /**
     * Start the MyTimer.
     */
    public final void startMyTimer() {
        if (!this.isRunning) {
            this.isRunning = true;
            this.start();
        }
    }
    /**
     * Stop the MyTimer.
     */
    public final void stopMyTimer() {
        if (this.isRunning) {
            this.isRunning = false;
        }
    }
    @Override
    public final void run() {
            if (isRunning) {
            this.entity.setStatus(Status.CHASED);
            }
            try {
                Thread.sleep(TIMECHASEDEND * 1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if (this.entity.getStatus().equals(Status.CHASED) && isRunning) {
            entity.setStatus(Status.CHASED_END);
            }
            try {
                Thread.sleep(TIMECHASED * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.entity.getStatus().equals(Status.CHASED_END) && isRunning) {
            entity.setStatus(Status.CHASING);
            this.isRunning = false;
            }
    }
    public final boolean isRunning() {
        return this.isRunning;
    }
}
