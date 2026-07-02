package powpaw.map.model.impl;

import javafx.geometry.Point2D;
import powpaw.map.model.api.Block;
import powpaw.map.model.api.BlockHitbox;
import powpaw.world.controller.ScreenController;

/**
 * Class representing the block (terrain) entity.
 * 
 * @author Giacomo Grassetti
 */

public final class BlockImpl implements Block {

    private final BlockHitbox hitbox;
    private Point2D position;
    private double width;
    private double height;

    /**
     * Constructor of the BlockImpl class.
     * 
     * @param pos    position of the block
     * @param width  width of the block
     * @param height height of the block
     */
    public BlockImpl(final Point2D pos, final double width, final double height) {
        this.position = new Point2D(pos.getX() * ScreenController.SIZE_HD_W / ScreenController.NUM_BLOCK_W,
                pos.getY() * ScreenController.SIZE_HD_H / ScreenController.NUM_BLOCK_H);
        this.hitbox = new BlockHitboxImpl(position, width * ScreenController.SIZE_HD_W / ScreenController.NUM_BLOCK_W,
                height * ScreenController.SIZE_HD_H / ScreenController.NUM_BLOCK_H);
    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public void setX(final double x) {
        this.position = new Point2D(x, this.position.getY());
    }

    @Override
    public void setY(final double y) {
        this.position = new Point2D(this.position.getX(), y);
    }

    @Override
    public BlockHitbox getHitbox() {
        return this.hitbox;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public void setWidth(final double width) {
        this.width = width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void setHeight(final double height) {
        this.height = height;
    }
}
