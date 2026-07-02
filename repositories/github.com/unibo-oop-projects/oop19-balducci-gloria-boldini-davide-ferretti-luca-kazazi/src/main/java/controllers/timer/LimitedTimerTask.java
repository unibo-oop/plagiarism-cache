package controllers.timer;

import java.util.Objects;
import java.util.TimerTask;
import model.player.Player;

public class LimitedTimerTask extends TimerTask {

    private final LimitedTimer timer;
    private int secs;
    private int stop;

    public LimitedTimerTask(final Player player, final LimitedTimer timer, final int secs, final int stop) {
        this.timer = Objects.requireNonNull(timer);
        this.secs = secs;
        this.stop = stop;
    }

    @Override
    public void run() {
        this.secs = this.secs + 1; 
        if (this.secs > this.stop) {
            timer.stop();
        }
    }
    
    public int getSecs() {
        return this.secs;
    }

}
