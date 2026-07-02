package model.powerup;

import java.awt.image.BufferedImage;
import controllers.timer.LimitedTimer;
import model.ID;
import model.player.Player;
import model.staticObject.ActiveStaticObject;

public class InvisiblePowerUP extends ActiveStaticObject implements PowerUPInterface {

    private LimitedTimer limitedTimer;

    /**
     * Constructor for InvisiblePowerUP.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param image
     */
    public InvisiblePowerUP(final ID id, final int posX, final int posY, final BufferedImage image) {
        super(id, posX, posY, 0, 0, image);
    }

    @Override
    public void effect(final Player player) {
        this.setVisible(false);
        this.replaceStaticObject(player);
        player.setVisible(false);
        player.setPowerUpDebuff(this);
        this.limitedTimer = new LimitedTimer(player, ID.INVISIBLEPU);
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
        player.setVisible(true);
        player.removePowerUpDebuff();
    }

}
