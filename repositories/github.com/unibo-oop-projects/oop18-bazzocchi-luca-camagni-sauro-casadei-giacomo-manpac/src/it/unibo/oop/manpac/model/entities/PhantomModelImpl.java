package it.unibo.oop.manpac.model.entities;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.model.score.Points;

/**
 * Implementation of Phantom interface.
 */
public final class PhantomModelImpl extends MobileEntityImpl implements PhantomModel {

    private final Entity name;

    private Directions secondLastDirection;
    private final Points<Integer> points;

    private boolean fear;

    /**
     * Constructor of PhantomImpl.
     * 
     * @param points points awarded for eating this phantom
     * @param name   the name of this phantom
     */
    public PhantomModelImpl(final Points<Integer> points, final Entity name) {
        super();
        this.secondLastDirection = Directions.STOP;
        this.name = name;
        this.points = points;
    }

    @Override
    public Entity getName() {
        return this.name;
    }

    @Override
    public Action generateDirection(final PhantomDirectionGenerator p) {
        Directions generateDir;
        do {
            generateDir = p.generateDirection();
        } while (this.getDirection() == generateDir || this.secondLastDirection == generateDir);
        this.secondLastDirection = this.getDirection();
        return this.setDirection(generateDir);
    }

    @Override
    protected Action changeAction(final Directions direction) {
        switch (direction) {

        case UP:
            return Action.DIRECTION_CHANGED_UP;

        case DOWN:
            return Action.DIRECTION_CHANGED_DOWN;

        case LEFT:
            return Action.DIRECTION_CHANGED_LEFT;

        case RIGHT:
            return Action.DIRECTION_CHANGED_RIGHT;

        default:
            return Action.MALFUNCTION;

        }
    }

    @Override
    public void setFear(final boolean fear) {
        this.fear = fear;
    }

    @Override
    public boolean isFeared() {
        return this.fear;
    }

    @Override
    public Points<Integer> getPoints() {
        return this.points;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhantomModelImpl other = (PhantomModelImpl) obj;
        return (name != other.name);
    }

    @Override
    public String toString() {
        return "PhantomModelImpl [name=" + name + ", secondLastDirection=" + secondLastDirection + ", points=" + points
                + ", fear=" + fear + "]";
    }

}
