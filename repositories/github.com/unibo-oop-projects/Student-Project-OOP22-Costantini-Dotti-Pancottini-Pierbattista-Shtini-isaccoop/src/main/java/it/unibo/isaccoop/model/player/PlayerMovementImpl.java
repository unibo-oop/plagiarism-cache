package it.unibo.isaccoop.model.player;

import it.unibo.isaccoop.graphics.GraphicsComponent;
import it.unibo.isaccoop.model.common.Direction;
import it.unibo.isaccoop.model.common.Point2D;

/**
 * Implement the PlayerMovement interface and
 * model player position directly by extending PlayerStatImpl.
 * */
public class PlayerMovementImpl extends PlayerStatImpl implements PlayerMovement {

    /**
     * Constructor.
     * @param gr
     * */
    public PlayerMovementImpl(final GraphicsComponent gr) {
        super(gr);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void update(final Direction direction) {
        super.setCoords(this.getMovePreview(direction));
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Point2D getMovePreview(final Direction direction) {
        final Double distance = super.getSpeed();
        final double x = super.getCoords().getX();
        final double y = super.getCoords().getY();
        switch (direction) {
        case UP:
            return new Point2D(x, y - distance);
        case RIGHT:
            return new Point2D(x + distance, y);
        case DOWN:
            return new Point2D(x, y + distance);
        case LEFT:
            return new Point2D(x - distance, y);
        default:
            return new Point2D(x, y);
        }
    }
}
