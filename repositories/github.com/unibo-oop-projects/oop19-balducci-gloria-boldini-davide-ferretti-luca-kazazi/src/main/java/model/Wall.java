package model;

import java.awt.image.BufferedImage;

import model.gameObject.GameObject;

public class Wall extends GameObject {

    /**
     * Constructor for Wall.
     * 
     * @param id
     * @param posX
     * @param posY
     * @param image
     */
    public Wall(final ID id, final int posX, final int posY, final BufferedImage image) {
        super(id, posX, posY, 0, 0, image);
    }

    @Override
    public void tick() {
    }

}
