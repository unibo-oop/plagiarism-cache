package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.AbstractMovement;
import it.unibo.oop.bbgmm.Entity.Direction;
import it.unibo.oop.bbgmm.Entity.Entity;

/**
 * Permit at the entity to walk
 */
public final class Feet extends AbstractMovement {

    private final double walkingSpeed;

    /**
     *
     * @param walkingSpeed
     *            entity speed for the movement
     */
    public Feet(final double walkingSpeed){
        super();
        this.walkingSpeed = walkingSpeed;
    }

    /**
     * attach the component to the entity
     * @param owner
     */
    @Override
    public void attach(Entity owner) {
        super.attach(owner);
    }

    @Override
    public void update(double delta) {

    }


    @Override
    public void move(Direction direction, double speed) {
        super.move(direction, speed);
        setDesiredDirection(direction);
    }

    private void updateState(Direction direction){
        if(direction.equals(direction.NOTHING)){
            setState(State.STABLE);
        }
        else {
            setState(State.WALKING);
        }
    }

}
