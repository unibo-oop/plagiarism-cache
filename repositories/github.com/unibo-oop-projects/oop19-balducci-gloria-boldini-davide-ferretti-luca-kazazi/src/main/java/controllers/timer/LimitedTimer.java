package controllers.timer;

import java.util.Objects;
import java.util.Timer;
import model.ID;
import model.player.Player;

public class LimitedTimer implements GeneralTimerInterface {

    private static final int SLOW_SECS = 5;
    private static final int SPEEDUP_SECS = 5;
    private static final int INVISIBLE_SECS = 5;
    private static final int RECOVER_SECS = 2;
    private static final int KNIFE_SECS = 10;
    private Timer timer;
    private final Player player;
    private final ID idStaticObject;
    private int secs;
    private LimitedTimerTask limitedTimerTask;
    private FreezeTask freezeTask;

    public LimitedTimer(final Player player, final ID id) {
        this.player = Objects.requireNonNull(player);
        this.idStaticObject = Objects.requireNonNull(id);
        this.secs = 0;
    }

    @Override
    public void start() {
        this.timer = new Timer();
        if (this.idStaticObject.equals(ID.FREEZEDB)) {
            this.timer.schedule(this.freezeTask = new FreezeTask(this.player, this.player.getCoord(), this, this.secs),
                    0, 10);
        } else if (this.idStaticObject.equals(ID.SLOWDOWNDB)) {
            this.timer.schedule(this.limitedTimerTask = new LimitedTimerTask(this.player, this, this.secs, SLOW_SECS),
                    0, 1000);
        } else if (this.idStaticObject.equals(ID.SPEEDUPPU)) {
            this.timer.schedule(
                    this.limitedTimerTask = new LimitedTimerTask(this.player, this, this.secs, SPEEDUP_SECS), 0, 1000);
        } else if (this.idStaticObject.equals(ID.INVISIBLEPU)) {
            this.timer.schedule(
                    this.limitedTimerTask = new LimitedTimerTask(this.player, this, this.secs, INVISIBLE_SECS), 0,
                    1000);
        } else if (this.idStaticObject.equals(ID.PLAYER)) {
            this.timer.schedule(
                    this.limitedTimerTask = new LimitedTimerTask(this.player, this, this.secs, RECOVER_SECS), 0, 1000);
        } else if (this.idStaticObject.equals(ID.KNIFEPU)) {
            this.timer.schedule(this.limitedTimerTask = new LimitedTimerTask(this.player, this, this.secs, KNIFE_SECS),
                    0, 1000);
        }
    }

    @Override
    public void stop() {
        if (this.isActiveStaticObject()) {
            this.player.getActivePowerUpDebuff().terminateEffect(this.player);
        } else if (this.idStaticObject.equals(ID.PLAYER)) {
            this.terminateRecoverFromCollision();
        }
        this.timer.cancel();
    }

    public void pause() {
        if (this.isActiveStaticObject() && !this.idStaticObject.equals(ID.FREEZEDB)) {
            this.secs = limitedTimerTask.getSecs();
        } else if (this.idStaticObject.equals(ID.FREEZEDB)) {
            this.secs = freezeTask.getSecs();
        }
        this.timer.cancel();
    }

    private void terminateRecoverFromCollision() {
        this.player.setRecovering(false);
        if (this.player.getActivePowerUpDebuff() != null
                && (this.player.getActivePowerUpDebuff().getId().equals(ID.INVISIBLEPU)
                        || this.player.getActivePowerUpDebuff().getId().equals(ID.KNIFEPU))) {
            this.player.setVisible(false);
        } else {
            this.player.setVisible(true);
        }
    }

    private boolean isActiveStaticObject() {
        return this.idStaticObject.equals(ID.SLOWDOWNDB) || this.idStaticObject.equals(ID.SPEEDUPPU)
                || this.idStaticObject.equals(ID.INVISIBLEPU) || this.idStaticObject.equals(ID.KNIFEPU)
                || this.idStaticObject.equals(ID.FREEZEDB);
    }

}
