package controllers.timer;

import java.util.Objects;
import java.util.TimerTask;

public class DeathTimerTask extends TimerTask {

    private static final double STOP = 1;
    private int secs;
    private final DeathTimer deathTimer;

    public DeathTimerTask(final DeathTimer deathTimer) {
        this.deathTimer = Objects.requireNonNull(deathTimer);
        this.secs = 0;
    }

    @Override
    public void run() {
        if (this.secs < STOP) {
            this.secs++;
        } else if (this.secs >= STOP) {
            this.deathTimer.stop();
        }
    }

}