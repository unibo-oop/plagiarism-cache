package model.entities;

import java.awt.Dimension;
import java.util.Optional;

import model.ModelImpl;

/**
 * 
 * An implementation of a {@link AbstractBarrel}
 * 
 * Those kind of barrels will flip their horizontal direction at every fall.
 *
 */
public abstract class AbstractBarrelImpl extends DynamicEntityImpl implements AbstractBarrel, DynamicEntity {

    private final static int TRIGGER_HEIGHT = 20;
    private final static int TRIGGER_WIDTH = 5;
    private final static double STEP = 1;
    private Optional<Entity> trigger;
    protected Movement lastDirection = Movement.RIGHT;
    private EntityStatus prevStatus;

    /**
     * A constructor for a Barrel
     * @param x The starting x Coordinate.
     * @param y The starting Y Coordinate.
     * @param dim Dimension of a barrel's hitbox
     */
    public AbstractBarrelImpl(final Double x, final Double y, final Dimension dim) {
        super(x, y, dim);
        setPrevStatus(getStatus());
        this.trigger = Optional.of(new BarrelTriggerImpl(this.getHitbox().getCenterX(), this.getY()-TRIGGER_HEIGHT,
                new Dimension(TRIGGER_WIDTH, TRIGGER_HEIGHT)));
    }

    @Override
    protected void tryToMove(Movement dir) {
        if (dir == Movement.LEFT) {
            this.setDeltaX(-STEP);
            this.setDeltaY(0);
        } else if (dir == Movement.RIGHT) {
            this.setDeltaX(STEP);
            this.setDeltaY(0);
        } else if(dir == Movement.DOWN) {
            this.setDeltaY(getDeltaY()+ModelImpl.GRAVITY);
            this.setDeltaX(0);
        }
    }

    @Override
    public void update() {
        checkDirection();
        super.update();
    }
    
    abstract void changeDir();
    
    abstract void checkDirection();
    
    @Override
    public Optional<Entity> getTrigger() {
        if(trigger.isPresent()) {
            trigger.get().setX(this.getHitbox().getCenterX());
            trigger.get().setY(this.getY() - TRIGGER_HEIGHT);
            return trigger;
        }
        else {
            return Optional.empty();
        }
    }
    
    public void removeTrigger() {
        this.trigger = Optional.empty();
    }

    @Override
    public String toString() {
        return "BARREL | Coord[" + this.getX() + "," + this.getY() + "] - " + "Direction["
                + this.getCurrentDirection() + "] - Dy[" + "Status["
                + this.getStatus() + "] - Dy[" + this.getDeltaY() + "]" + " - Dx[" + this.getDeltaX() + "]";
    }

    public EntityStatus getPrevStatus() {
        return prevStatus;
    }

    public void setPrevStatus(EntityStatus prevStatus) {
        this.prevStatus = prevStatus;
    }
}
