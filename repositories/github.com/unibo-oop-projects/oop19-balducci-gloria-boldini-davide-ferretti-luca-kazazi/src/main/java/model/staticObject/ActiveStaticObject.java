package model.staticObject;

import java.awt.image.BufferedImage;
import controllers.timer.LimitedTimer;
import model.ID;
import model.gameObject.GameObject;
import model.player.Player;

public abstract class ActiveStaticObject extends GameObject implements ActiveStaticObjectInterface {

    /**
     * Constructor ActiveStaticObject.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param image
     */
    public ActiveStaticObject(final ID id, final int posX, final int posY, final double velX, final double velY,
            final BufferedImage image) {
        super(id, posX, posY, velX, velY, image);
    }

    @Override
    public void replaceStaticObject(final Player player) {
        if (player.getActivePowerUpDebuff() != null) {
            player.getActivePowerUpDebuff().getTimer().stop();
        }
        player.removePowerUpDebuff();
    }

    public abstract LimitedTimer getTimer();

    public abstract void terminateEffect(Player player);
}
