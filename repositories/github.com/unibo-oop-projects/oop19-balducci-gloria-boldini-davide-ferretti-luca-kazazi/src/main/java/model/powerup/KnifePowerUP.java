package model.powerup;

import java.awt.image.BufferedImage;
import controllers.timer.LimitedTimer;
import model.ID;
import model.player.Player;
import model.staticObject.ActiveStaticObject;

public class KnifePowerUP extends ActiveStaticObject implements PowerUPInterface {

    private LimitedTimer limitedTimer;

    /**
     * Constructor for KnifePowerUP.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param image
     */
    public KnifePowerUP(final ID id, final int posX, final int posY, final BufferedImage image) {
        super(id, posX, posY, 0, 0, image);
    }

    @Override
    public void effect(final Player player) {
        this.setVisible(false);
        this.replaceStaticObject(player);
        player.setPowerUpDebuff(this);
        player.setKnife(true);
        this.limitedTimer = new LimitedTimer(player, ID.KNIFEPU);
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
        player.removePowerUpDebuff();
        player.setKnife(false);
        if (player.getActivePowerUpDebuff() == null || (player.getActivePowerUpDebuff().getId() != ID.INVISIBLEPU
                || player.getActivePowerUpDebuff().getId() != ID.KNIFEPU)) {
            player.setVisible(true);
        }

    }
}
