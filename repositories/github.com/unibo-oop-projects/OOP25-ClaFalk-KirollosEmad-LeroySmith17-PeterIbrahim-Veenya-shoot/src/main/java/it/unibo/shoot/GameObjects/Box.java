
package it.unibo.shoot.GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.shoot.loader.SpriteSheet;
import it.unibo.shoot.model.ID;

public class Box extends GameObject{

    private final BufferedImage box_image;
    
    public Box(int x,int y,ID id,SpriteSheet ss) {
        super(x,y,id,ss);
        box_image = ss.grabImage(0, 0);
    }
    
    @Override
    public void tick() {    
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(box_image, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

}
