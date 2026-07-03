package model.entities;

import java.awt.Dimension;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import model.ModelImpl;

/**
 * An implementation of a DynamicEntity, this class extends a basic entity and
 * adds methods to manage movements.
 * 
 */
public abstract class DynamicEntityImpl extends EntityImpl implements DynamicEntity {

    private double deltaX;
    private double deltaY;
    private Movement lastDirection = Movement.RIGHT;
    private EntityStatus currentStatus = EntityStatus.OnTheFloor;
    private final BlockingQueue<Movement> movements;

    /**
     * A constructor for a dynamic entity.
     * 
     * @param x
     *            The starting x Coordinate.
     * @param y
     *            The starting Y Coordinate.
     * @param dim
     *            Dimension of the Entity's hitbox
     */
    public DynamicEntityImpl(final Double x, final Double y, final Dimension dim) {
        super(x, y, dim);
        this.movements = new LinkedBlockingQueue<Movement>();
    }

    @Override
    public final void move(final Optional<Movement> dir) {
        if (dir.isPresent()) {
            tryToMove(dir.get());
            if ((dir.get() == Movement.RIGHT || dir.get() == Movement.LEFT) && this.getStatus() != EntityStatus.Climbing) {
                this.setDirection(dir.get());
                this.setX(this.getX() + deltaX);
            }
            else if(dir.get() == Movement.DOWN) {
                this.setY(this.getY() + deltaY);
            }
            return;
        }
        this.setY(this.getY() + deltaY);
    }

    /**
     * Update method is designed for extension in special cases, eventual extension
     * should start with super() call.
     */
    @Override
    public void update() {
        this.manageMovements();
        this.move(Optional.empty());
        if (getStatus() == EntityStatus.Falling) {
            this.setDeltaY(this.getDeltaY() + ModelImpl.GRAVITY);
        }
    }

    @Override
    public final Movement getCurrentDirection() {
        return lastDirection;
    }

    @Override
    public final void setDirection(final Movement dir) {
        this.lastDirection = dir;
    }

    @Override
    public final EntityStatus getStatus() {
        return currentStatus;
    }

    @Override
    public final void setStatus(final EntityStatus status) {
        this.currentStatus = status;
    }

    @Override
    public final void addMovement(final Movement dir) {
        try {
            this.movements.put(dir);
        } catch (InterruptedException e) {
            System.err.println("The thread  was interrupted while adding a new Movement to Mario.");
            e.printStackTrace();
        }
    }

    /**
     * Tries to move in the dir Movement, if the movement is not possible, this
     * method does nothing.
     * 
     * @param dir
     *            The direction in which the entity wants to move.
     */
    protected abstract void tryToMove(Movement dir);

    /**
     * Method to process available Movements.
     * 
     */
    protected final void manageMovements() {
        while (!this.movements.isEmpty()) {
            this.move(Optional.ofNullable(movements.poll()));
        }

    }

    /**
     * This methods returns the current X increment of the element.
     * 
     * @return A double representing the X increment.
     */
    protected double getDeltaX() {
        return this.deltaX;
    }

    /**
     * This methods returns the current Y increment of the element.
     * 
     * @return A double representing the Y increment.
     */
    protected double getDeltaY() {
        return this.deltaY;
    }

    /**
     * Sets a new X increment for the coordinate.
     * 
     * @param dX
     *            The new increment.
     */
    protected void setDeltaX(final double dX) {
        this.deltaX = dX;
    }

    /**
     * Sets a new Y increment for the coordinate.
     * 
     * @param dY
     *            The new increment.
     */
    protected void setDeltaY(final double dY) {
        this.deltaY = dY;
    }

}
