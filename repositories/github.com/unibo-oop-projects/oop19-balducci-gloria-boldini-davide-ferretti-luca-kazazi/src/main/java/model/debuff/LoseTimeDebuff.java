package model.debuff;

import java.awt.image.BufferedImage;

import controllers.timer.GameTime;
import controllers.timer.LimitedTimer;
import model.ID;
import model.gameObject.GameObject;
import model.player.Player;

public class LoseTimeDebuff extends GameObject implements DebuffInterface {

    private static final int HOURS_MORE = 0;
    private static final int MINS_MORE = 0;
    private static final int SECS_MORE = 30;

    private GameTime gameTime;
    private static final int NEXTMIN = 59;

    /**
     * Constructor for LoseTimeDebuff.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param image
     */
    public LoseTimeDebuff(final ID id, final int posX, final int posY, final BufferedImage image) {
        super(id, posY, posY, 0, 0, image);
    }

    /**
     * set the GameTime.
     * 
     * @param gameTime
     */
    public void setGameTime(final GameTime gameTime) {
        this.gameTime = gameTime;
    }

    @Override
    public void effect(final Player player) {
        this.setGameTime(player.getTimer());
        this.setVisible(false);
        final int oldHours = this.gameTime.getTimerTask().getHours();
        final int oldMins = this.gameTime.getTimerTask().getMins();
        final int oldSecs = this.gameTime.getTimerTask().getSecs();
        int newHours = oldHours + HOURS_MORE;
        int newMins = oldMins + MINS_MORE;
        final int newSecs = oldSecs + SECS_MORE;
        if (newSecs <= NEXTMIN) {
            this.gameTime.getTimerTask().setSecs(newSecs);
        } else if (newSecs > NEXTMIN) {
            newMins++;
            this.gameTime.getTimerTask().setSecs(newSecs - NEXTMIN + 1);
        }
        if (newMins <= NEXTMIN) {
            this.gameTime.getTimerTask().setMins(newMins);
        } else if (newMins > NEXTMIN) {
            newHours++;
            this.gameTime.getTimerTask().setMins(newMins - NEXTMIN + 1);
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
