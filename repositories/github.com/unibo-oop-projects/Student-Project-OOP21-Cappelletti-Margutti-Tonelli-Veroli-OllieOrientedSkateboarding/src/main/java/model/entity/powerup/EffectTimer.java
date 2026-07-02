package model.entity.powerup;

import java.util.Timer;
import java.util.TimerTask;

public class EffectTimer {

    private final Timer time; 
    private static final long DELAY = 10_000L; 

    public EffectTimer() {
        time = new Timer(); 
    }

    /**
     * Schedules the powerup task for execution after a specified time. 
     * @param powerupTask the powerup scheduled task. 
     */
    public final void scheduleTask(final TimerTask powerupTask) {

        time.schedule(powerupTask, DELAY);

    }

}
