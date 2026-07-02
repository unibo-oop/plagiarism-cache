package model;

import java.awt.image.BufferedImage;

import model.gameObject.GameObject;
import model.player.Player;

public class Lava extends GameObject {

    /**
     * Constructor for Lava.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param image
     */
    public Lava(final ID id, final int posX, final int posY, final double velX, final double velY,
            final BufferedImage image) {
        super(id, posX, posY, velX, velY, image);
    }

    @Override
    public void tick() {
    }

    /**
     * @param player that collides with lava
     */
    public void effect(final Player player) {
        if (player.isVisible()) {
            player.setVisible(false);
            player.removeLife();
        }
    }

}
