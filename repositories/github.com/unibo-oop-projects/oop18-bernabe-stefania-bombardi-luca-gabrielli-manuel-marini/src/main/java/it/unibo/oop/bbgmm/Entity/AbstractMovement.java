package it.unibo.oop.bbgmm.Entity;
import  it.unibo.oop.bbgmm.Entity.Component.AbstractEntityComponent;


public abstract class AbstractMovement extends AbstractEntityComponent implements Movement {
    private State currentState = State.STABLE;
    private Direction directionMovement = Direction.NOTHING;
    private double speedMovement;


    /**
     * Set the new state of the entity
     * @param newState
     */
    protected final void setState(final State newState){
        if(!currentState.equals(newState)){
            currentState = newState;
        }
    }

    protected final Direction getDesiredDirection(){
        return directionMovement;
    }

    protected final void setDesiredDirection(final Direction changeDirection){
        directionMovement = changeDirection;

    }

    @Override
    public void move(Direction direction,  double speed) {
            this.directionMovement = direction;
            this.speedMovement = speed;

    }

    @Override
    public State getState() {
        return currentState;
    }
}
