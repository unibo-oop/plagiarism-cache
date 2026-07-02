package frogger.model.implementations;

import java.util.Optional;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Carrier;

/**
 * Represents a Trunk object in the game, which moves in a specified direction and speed.
 * Trunks may carry an object and change his position, it can be setted and removed, thanks the Carrier implementation
 */
public class Trunk extends MovingObjectImpl implements Carrier<PlayerObjectImpl> {
    /** Tolerance for float comparisons. */
    private static final float EPSILON = 1e-5f;
    /** The possible object to carry is the frog.*/
    private Optional<PlayerObjectImpl> frog = Optional.empty();

    /**
     * Constructs a Trunk object.
     *
     * @param pos       the initial position of the trunk
     * @param dimension the size of the trunk (width and height in blocks)
     * @param speed     the movement speed of the trunk
     * @param direction the initial movement direction of the trunk
     */
    public Trunk(final Position pos, final Pair dimension, final float speed, final Direction direction) {
        super(pos, dimension, speed, direction);
        super.setImage("trunk.png");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObj(final PlayerObjectImpl frog) {
        this.frog = Optional.of(frog);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObj() {
        this.frog = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void step() {
        if (frog.isPresent()) {
            if (Math.abs(frog.get().getPos().y() - this.getPos().y()) > EPSILON) {
                this.removeObj();
            } else {
                frog.get().setPos(new Position(frog.get().getPos().x() + this.getDirectionValue().x() * this.getSpeed(), 
                frog.get().getPos().y() + this.getDirectionValue().y() * this.getSpeed()));
            }
        }
        super.step();
    }
}
