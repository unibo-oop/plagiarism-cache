package it.unibo.shoot.GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.shoot.loader.SpriteSheet;
import it.unibo.shoot.model.ID;
public class Block extends GameObject{

    private final BufferedImage block_tile;

    public Block(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
        block_tile = ss.grabImage(0, 0);
    }

    @Override
    public void tick(){
    }

    @Override
    public void render(Graphics g){
       g.drawImage(block_tile, x, y, null);
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x,y,32,32);
    }
}
 