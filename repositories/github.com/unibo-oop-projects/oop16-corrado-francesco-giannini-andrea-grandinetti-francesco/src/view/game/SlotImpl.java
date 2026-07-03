package view.game;

import model.PositionImpl;
import utility.Position;
/**
 * Slot interface implementation.
 *
 */
public class SlotImpl extends PositionImpl implements Slot {

    private final double x;
    private final double y;
    private final double rotation;
    /**
     * 
     * @param pos position of slot
     * @param x coordinate of x
     * @param y coordinate of y
     * @param rotation of node
     */
    public SlotImpl(final Position pos, final double x, final double y, final double rotation) {
        super(pos.getX(), pos.getY(), pos.getTrackNumber());
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    @Override
    public double getXCoord() {
        return x;
    }

    @Override
    public double getYCoord() {
        return y;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public Position getPosition() {
        return new PositionImpl(this.getX(), this.getY(), this.getTrackNumber());
    }
}
