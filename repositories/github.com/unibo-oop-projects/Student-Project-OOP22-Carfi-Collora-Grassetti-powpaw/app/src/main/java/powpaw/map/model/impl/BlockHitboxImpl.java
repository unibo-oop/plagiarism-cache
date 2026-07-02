package powpaw.map.model.impl;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import powpaw.map.model.api.BlockHitbox;

/**
 * Class representing the hitbox entity of a Block.
 * 
 * @author Giacomo Grassetti
 */

public final class BlockHitboxImpl implements BlockHitbox {

    private final Rectangle shape;

    /**
     * Constructor for the BlockHitboxImpl class that takes in a
     * Point2D object
     * representing the position of the block and two double values representing
     * the width and height
     * of the block. It creates a new Rectangle object with the given position,
     * width, and height,
     * and assigns it to the shape field of the BlockHitboxImpl instance.
     * 
     * @param pos    position of hitbox (x, y)
     * @param width  hitbox width
     * @param height hitbox height
     */

    public BlockHitboxImpl(final Point2D pos, final double width, final double height) {
        this.shape = new Rectangle(pos.getX(), pos.getY(), width, height);
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    @Override
    public boolean checkCollision(final Shape otherHitbox) {
        return this.shape.getBoundsInParent().intersects(otherHitbox.getBoundsInParent());
    }

}
