package controllers.timer;

import java.util.Objects;
import java.util.TimerTask;
import model.player.Player;
import other.Pair;

public class FreezeTask extends TimerTask {

    private static final int FREEZE_SECS = 5;
    private static final int STOP = FREEZE_SECS * 1000;
    private static final int INTERVAL = 10;
    private final Player player;
    private final Pair<Integer, Integer> oldPair;
    private int x;
    private LimitedTimer timer;

    public FreezeTask(final Player player, final Pair<Integer, Integer> oldCoordsdPair, final LimitedTimer timer, final int secs) {
        this.player = Objects.requireNonNull(player);
        this.oldPair = Objects.requireNonNull(oldCoordsdPair);
        this.x = secs;
        this.timer = timer;
    }

    @Override
    public void run() {
        x = x + INTERVAL;
        if (x < STOP) {
            player.setCoord(oldPair);
        }
        else {
            this.timer.stop();
        }
    }
    
    public int getSecs() {
        return this.x;
    }
}
