package controllers.timer;

import java.util.Objects;
import java.util.Timer;

import application.StealthNinja;
import model.player.Player;

public class DeathTimer implements GeneralTimerInterface {

    private static final int PERIOD = 110;
    private final Timer timer;
    private final Player player;

    public DeathTimer(final Player player) {
        this.timer = new Timer();
        this.player = Objects.requireNonNull(player);
    }

    @Override
    public void start() {
        if (this.player.getActivePowerUpDebuff() != null) {
            this.player.getActivePowerUpDebuff().getTimer().stop();
        }
        this.timer.schedule(new DeathTimerTask(this), 0, PERIOD);
    }

    @Override
    public void stop() {
        this.timer.cancel();
        StealthNinja.MODEL_ACTION.interruptLevel();
    }

}
