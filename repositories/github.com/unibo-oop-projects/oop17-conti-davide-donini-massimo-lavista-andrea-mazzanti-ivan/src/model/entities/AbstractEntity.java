package model.entities;

import javafx.scene.shape.Shape;

import javafx.scene.shape.Rectangle;

import model.entities.properties.Position;
import model.entities.properties.PositionImpl;
import model.entities.properties.Velocity;
import model.utilities.Shapes;

/**
 * AbstractEntity contains the common data of all the entities: shape and
 * velocity.
 */
public abstract class AbstractEntity implements Entity {

    private static final double TRANSFORMATION_FROM_MILLISECOND_TO_SECOND = 0.001;

    private Shape shape;
    private Velocity velocity;

    /**
     * @param shape
     *            initial entity's shape.
     * @param velocity
     *            initial entity's velocity.
     */
    public AbstractEntity(final Shape shape, final Velocity velocity) {
        this.velocity = velocity;
        this.shape = shape;
        if (this.shape instanceof Rectangle) {
            ((Rectangle) this.shape).setX(((Rectangle) this.shape).getX() - ((Rectangle) this.shape).getWidth() / 2);
            ((Rectangle) this.shape).setY(((Rectangle) this.shape).getY() - ((Rectangle) this.shape).getHeight() / 2);
        }
    }

    @Override
    public final Velocity getVelocity() {
        return this.velocity;
    }

    @Override
    public final Shape getShape() {
        return this.shape;
    }

    @Override
    public final void setShape(final Shape shape) {
        this.shape = shape;
    }

    @Override
    public final Position getPosition() {
        return Shapes.getPositionbyShape(this.shape);
    }

    @Override
    public final void setPosition(final Position position) {
        Shapes.setShapePosition(this.shape, position);
    }

    @Override
    public final void setVelocity(final Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * Update the entity's position according to velocity and position.
     */
    @Override
    public void update(final int time) {
        final Velocity velocity = this.getVelocity().mul(TRANSFORMATION_FROM_MILLISECOND_TO_SECOND * time);
        final Position shapePosition = this.getPosition();
        this.setPosition(new PositionImpl(shapePosition.getX() + velocity.getX(), 
                shapePosition.getY() + velocity.getY()));
    }

}
