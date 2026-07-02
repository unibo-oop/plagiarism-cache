package model.powerup;

import java.awt.image.BufferedImage;
import controllers.timer.LimitedTimer;
import model.ID;
import model.player.Player;
import model.staticObject.ActiveStaticObject;

public class SpeedUPPowerUP extends ActiveStaticObject implements PowerUPInterface {

    private static final double MULT_CONSTRAINT = 2.0;
    private LimitedTimer limitedTimer;
    private double oldSpeed;

    /**
     * Constructor for SpeedUpPowerUP.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param image
     */
    public SpeedUPPowerUP(final ID id, final int posX, final int posY, final BufferedImage image) {
        super(id, posX, posY, 0, 0, image);
        this.oldSpeed = 0;
    }


    @Override
    public void effect(final Player player) {
        this.setVisible(false);
        this.replaceStaticObject(player);
        player.setPowerUpDebuff(this);
        this.oldSpeed = player.getSpeed();
        player.getMovement().updateSpeed(oldSpeed * MULT_CONSTRAINT);
        this.limitedTimer = new LimitedTimer(player, ID.SPEEDUPPU);
        this.limitedTimer.start();
    }

    @Override
    public void tick() {
    }


    @Override
    public LimitedTimer getTimer() {
        return this.limitedTimer;
    }

    @Override
    public void terminateEffect(final Player player) {
        player.getMovement().updateSpeed(this.oldSpeed);
        player.removePowerUpDebuff();
    }
}
