package model;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import utility.GameValues;
import view.PongElement;

/**
 * @author Missi
 *
 */
public abstract class GenericPickUp implements PickUp {
    private final PongElement graphics;

    /**
     * @param elem **the graphic elem**
     */
    public GenericPickUp(final PongElement elem) {
        this.graphics = elem;
    }

    /**
     * @see model.PickUp#getGraphic()
     */
    @Override
    public PongElement getGraphic() {
        return this.graphics;
    }
    /**
     * @see model.Element#getPosition()
     */
    @Override
    public Point getPosition() {
        return this.graphics.getPosition();
    }

    /**
     * @see model.Element#setPosition(java.awt.Point)
     */
    @Override
    public void setPosition(final Point p) {
        this.graphics.setPosition(p);
    }

    /**
     * @see model.PickUp#getHitbox()
     */
    @Override
    public Ellipse2D getHitbox() {
        return new Ellipse2D.Double(this.getPosition().getX(), this.getPosition().getY(), GameValues.PU_DIMENSION, GameValues.PU_DIMENSION);
    }

    @Override
    public abstract void trigger(Ball ball);

}
