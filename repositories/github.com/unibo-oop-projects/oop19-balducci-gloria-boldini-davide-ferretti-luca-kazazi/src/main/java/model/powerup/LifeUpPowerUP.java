package model.powerup;

import java.awt.image.BufferedImage;
import controllers.timer.LimitedTimer;
import model.ID;
import model.gameObject.GameObject;
import model.player.Player;

public class LifeUpPowerUP extends GameObject implements PowerUPInterface {

    private static final int MAX_LIFES = 3;
    /**
     * Constructor for LifeUpPowerUP.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param image
     */
    public LifeUpPowerUP(final ID id, final int posX, final int posY, final BufferedImage image) {
        super(id, posX, posY, 0, 0, image);
    }

    @Override
    public void effect(final Player player) {
        this.setVisible(false);
        if (player.getHealth() < MAX_LIFES) {
            player.addLife();
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
