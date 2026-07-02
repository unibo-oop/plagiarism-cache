package model.powerup;

import java.awt.image.BufferedImage;
import java.util.Objects;

import controllers.timer.GameTime;
import controllers.timer.LimitedTimer;
import model.ID;
import model.gameObject.GameObject;
import model.player.Player;

public class GainTimePowerUP extends GameObject implements PowerUPInterface {

    private static final int HOURS_LESS = 0;
    private static final int MINS_LESS = 0;
    private static final int SECS_LESS = 10;
    private GameTime gameTime;
    private static final int PREV_MIN = 60;

    /**
     * Constructor for GainTimePowerUP.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param image
     */
    public GainTimePowerUP(final ID id, final int posX, final int posY, final BufferedImage image) {
        super(id, posX, posY, 0, 0, image);
    }

    /**
     * setter GameTime.
     * 
     * @param gameTime
     */
    public void setGameTime(final GameTime gameTime) {
        this.gameTime = Objects.requireNonNull(gameTime);
    }

    @Override
    public void effect(final Player player) {
        this.setGameTime(player.getTimer());
        this.setVisible(false);
        final int oldHours = this.gameTime.getTimerTask().getHours();
        final int oldMins = this.gameTime.getTimerTask().getMins();
        final int oldSecs = this.gameTime.getTimerTask().getSecs();
        int newHours = oldHours - HOURS_LESS;
        int newMins = oldMins - MINS_LESS;
        int newSecs = oldSecs - SECS_LESS;

        if (newSecs >= 0) {
            this.gameTime.getTimerTask().setSecs(newSecs);
        } else if (newSecs < 0) {
            newMins--;
            if (newMins < 0) {
                newSecs = 0;
                newMins = 0;
                this.gameTime.getTimerTask().setSecs(newSecs);
            } else {
                this.gameTime.getTimerTask().setSecs(PREV_MIN + newSecs);
            }
        }
        if (newMins >= 0) {
            this.gameTime.getTimerTask().setMins(newMins);
        } else if (newMins < 0) {
            newHours--;
            this.gameTime.getTimerTask().setMins(PREV_MIN + newMins);
        }
        if (newHours < 0) {
            this.gameTime.getTimerTask().setHours(0);
        }
        this.gameTime.getTimerTask().setHours(newHours);
    }

    @Override
    public void tick() {
    }

    @Override
    public LimitedTimer getTimer() {
        return null;
    }

}
