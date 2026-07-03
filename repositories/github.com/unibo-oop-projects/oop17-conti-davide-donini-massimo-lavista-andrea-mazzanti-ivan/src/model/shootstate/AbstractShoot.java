package model.shootstate;

import java.util.Objects;
import java.util.Optional;

/**
 * 
 * This class factories the common methods for ShootState.
 *
 */
public abstract class AbstractShoot implements ShootState {

    /**
     * The radius of the bullet.
     */
    protected static final double RADIUS_BULLET_SHAPE = 7.0;
    /**
     * The speed's bullet.
     */
    protected static final int BULLET_SPEED = -1350;

    private Optional<ShootState> nextState = Optional.empty();
    private Optional<ShootState> previosState = Optional.empty();

    @Override
    public final void setNextState(final ShootState nextState) {
        this.nextState = Objects.requireNonNull(Optional.of(nextState));
    }

    @Override
    public final void setPreviosState(final ShootState previosState) {
        this.previosState = Objects.requireNonNull(Optional.of(previosState));
    }

    @Override
    public final Optional<ShootState> getNextState() {
        return this.nextState;
    }

    @Override
    public final Optional<ShootState> getPreviosState() {
        return this.previosState;
    }

}
