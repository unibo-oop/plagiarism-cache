package model.debuff;

import java.awt.image.BufferedImage;
import controllers.timer.LimitedTimer;
import model.ID;
import model.gameObject.GameObject;
import model.player.Player;

public class FireDebuff extends GameObject implements DebuffInterface {

    /**
     * Constructor for FireDebuff.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param image
     */
    public FireDebuff(final ID id, final int posX, final int posY, final BufferedImage image) {
        super(id, posX, posY, 0, 0, image);
    }

    @Override
    public void effect(final Player player) {
        this.setVisible(false);
        if (player.isVisible()) {
            player.removeLife();
        }
    }

    @Override
    public void tick() {
    }

    @Override
    public LimitedTimer getTimer() {
        return null;
    }

}
