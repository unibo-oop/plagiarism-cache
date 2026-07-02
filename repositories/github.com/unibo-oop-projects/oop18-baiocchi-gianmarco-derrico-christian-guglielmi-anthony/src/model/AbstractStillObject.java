package model;

import java.awt.geom.Rectangle2D;

import physics.PhysicComponent;
import physics.PhysicComponentImpl;
import utilities.Position;

/**
 *  It defines an object with no movement.
 */
public abstract class AbstractStillObject implements GameObject {

    private final boolean solidity;
    private final boolean breakable;
    private final Position position;
    private final PhysicComponent physicComponent;

    /**
     * Constructor.
     * @param solidity : the solidity of object's body
     * @param breakable : the possibility to break the object
     * @param position : object's position
     */
    public AbstractStillObject(final boolean solidity, final boolean breakable, final Position position) {
        this.solidity = solidity;
        this.breakable = breakable;
        this.position = position;
        this.physicComponent = new PhysicComponentImpl(this);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSolid() {
        return this.solidity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBreakable() {
        return this.breakable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getBounds() {
        return this.physicComponent.getBounds();
    }
}
