package it.unibo.superpeach.gameentities.blocks;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Optional;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameentities.GameObject;
import it.unibo.superpeach.graphics.Texturer;

/**
 * World block.
 * 
 * @author Maurizio Capuano
 */
public abstract class Block implements GameObject {

    private int x;
    private int y;
    private int width;
    private int height;
    private int scale;

    private final Texturer texturer = Game.getTexturer();
    private Optional<BufferedImage[]> sprites;

    private BlockType type;

    /**
     * Constructor method for a Block object.
     * 
     * @param x x position of the block on the tile
     * @param y y position of the block on the tile
     * @param w block width
     * @param h block height
     * @param s game scale for enlarging the item if needed
     */
    public Block(final int x, final int y, final int w, final int h, final int s) {
        this.x = x * s;
        this.y = y * s;
        this.width = w * s;
        this.height = h * s;
        this.scale = s;
    }

    @Override
    public final Texturer getTexturer() {
        return texturer;
    }

    @Override
    public final Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public final int getX() {
        return x;
    }

    @Override
    public final int getY() {
        return y;
    }

    @Override
    public final int getHeight() {
        return height;
    }

    @Override
    public final int getWidth() {
        return width;
    }

    @Override
    public final int getScale() {
        return scale;
    }

    @Override
    public final BufferedImage[] getSprites() {
        return this.sprites.get();
    }

    /**
     * @return block type
     */
    public final BlockType getType() {
        return type;
    }

    @Override
    public final void setX(final int x) {
        this.x = x;
    }

    @Override
    public final void setY(final int y) {
        this.y = y;
    }

    @Override
    public final void setHeight(final int height) {
        this.height = height;
    }

    @Override
    public final void setWidth(final int width) {
        this.width = width;
    }

    @Override
    public final void setScale(final int scale) {
        this.scale = scale;
    }

    @Override
    public final void setSprites(final BufferedImage[] sprites) {
        this.sprites = Optional.of(sprites);
    }

    /**
     * @param type new block type
     */
    public final void setType(final BlockType type) {
        this.type = type;
    }

    @Override
    public final void tick() {

    }

}
