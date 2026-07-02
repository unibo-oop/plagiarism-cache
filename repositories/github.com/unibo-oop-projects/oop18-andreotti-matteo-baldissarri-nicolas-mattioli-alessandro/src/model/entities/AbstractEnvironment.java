package model.entities;

import java.util.Optional;

import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

/**
 * This class provides a skeletal implementation of the {@link Environment}
 * interface to minimize the effort required to implement this interface.
 */
public abstract class AbstractEnvironment implements Environment {

    private final Rectangle entityShape;
    private final Optional<Border> borders;

    /**
     * @param initPos The initial position of the entity
     * @param height  The height of the entity
     * @param width   The width of the entity
     * @param borders The entity's boundaries
     */
    public AbstractEnvironment(final Position initPos, final double height, final double width,
            final Optional<Border> borders) {
        this.entityShape = new Rectangle(width, height);
        this.entityShape.translate(initPos.getX(), initPos.getY());
        this.borders = borders;
    }

    @Override
    public abstract boolean canMove(Position position);

    /**
     * Move the entity in a new position.
     */
    @Override
    public void move(final double x, final double y) {
        final Vector2 v = this.entityShape.getCenter().sum(x, y);
        final Position pos = new Position(v.x, v.y);
        if (this.canMove(pos)) {
            this.entityShape.translate(x, y);
        }
    }

    /**
     * Set the entity's position.
     */
    @Override
    public void setPosition(final Position position) {
        this.entityShape.translate(-this.entityShape.getCenter().x + position.getX(),
                -this.entityShape.getCenter().y + position.getY());
    }

    /**
     * @return The entity position
     */
    @Override
    public Position getPosition() {
        return new Position(this.entityShape.getCenter().x, this.entityShape.getCenter().y);
    }

    /**
     * @return The boundaries of the entity's environment
     */
    @Override
    public Optional<Border> getBorders() {
        return this.borders;
    }

    /**
     * @return The shape of the entity
     */
    @Override
    public Rectangle getShape() {
        return this.entityShape;
    }

}
