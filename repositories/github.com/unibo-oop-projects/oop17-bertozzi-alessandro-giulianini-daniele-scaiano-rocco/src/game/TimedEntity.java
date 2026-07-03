package game;

import utilities.Pair;

/**
 * An entity with a time-limited life.
 */
public abstract class TimedEntity extends Entity implements Timer {

    private final Timer timer;

    /**
     * @param position position of this entity
     * @param velocityX xVelocity of this entity
     * @param velocityY yVelocity of this entity
     * @param id id of the this entity
     * @param timer the lifetime of the entity
     */
    public TimedEntity(final Pair<Integer, Integer> position, final int velocityX, final int velocityY, final ID id, final int timer) {
        super(position, velocityX, velocityY, id);
        this.timer = new TimerImpl(timer);
    }

    @Override
    public final void tick() {
        this.timer.tick();
    }

    @Override
    public final boolean isEnded() {
        return timer.isEnded();
    }

    @Override
    public final int getTimeLeft() {
        return timer.getTimeLeft();
    }

    @Override
    public abstract void update();

    @Override
    public abstract void collide(GameObject entity);
}
