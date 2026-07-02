package org.vise.controller.timer;

import org.vise.controller.PlayerController;
import org.vise.controller.UpdatableUIPlayer;
import org.vise.model.player.Player;

/**
 * Represents the timer for the update of the player slider.
 *
 */
public class TimerPlayer implements Runnable {
    private final PlayerController controller;
    private final UpdatableUIPlayer ui;
    private final Player player;
    private boolean running;
    private volatile Thread thread;
    private int lenght;

    /**
     * 
     * @param controller
     *      .
     * @param ui
     *      .
     * @param player
     *      .
     */
    public TimerPlayer(final PlayerController controller, final UpdatableUIPlayer ui, final Player player) {
        super();
        this.controller = controller;
        this.player = player;
        this.ui = ui;
    }

    /**
     * 
     */
    @Override
    public void run() {
        int lastPosition = -1;
        while (this.isRunning()) {
            try {
                if (this.isRunning() && this.player.getPosition() < this.lenght) {
                    if (this.player.getPosition() == lastPosition) {
                        this.controller.next();
                    } else {
                        lastPosition = this.player.getPosition();
                        this.ui.updateReproductionSongPosition(this.player.getPosition());
                        Thread.sleep(1000);
                    }
                } else {
                    this.setRunning(false);
                }
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
            thread = new Thread(this, "Position");
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

    /**
     * 
     */
    public void setLenghtCurrentSong() {
        this.lenght = this.player.getLength();
    }
}
