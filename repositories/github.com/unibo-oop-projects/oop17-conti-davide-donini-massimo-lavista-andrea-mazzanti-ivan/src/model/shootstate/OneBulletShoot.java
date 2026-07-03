package model.shootstate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.scene.shape.Circle;
import model.entities.Bullet;
import model.entities.BulletImpl;
import model.entities.BulletType;
import model.entities.properties.Position;
import model.entities.properties.VelocityImpl;

/**
 * 
 * Implements behavior associated with a state: this behavior consists in firing only one bullet.
 *
 */
public final class OneBulletShoot extends AbstractShoot {

    private OneBulletShoot(final Optional<ShootState> nextState, final Optional<ShootState> previousState) {
        super();
        nextState.ifPresent(state -> super.setNextState(state));
        previousState.ifPresent(state -> super.setPreviosState(state));
    }

    @Override
    public List<Bullet> shoot(final Position spaceshipPosition, final double bulletDamage) {
        return Arrays.asList(new BulletImpl(new Circle(spaceshipPosition.getX(), spaceshipPosition.getY(), 
                RADIUS_BULLET_SHAPE), new VelocityImpl(0, BULLET_SPEED), bulletDamage, BulletType.FRIENDLY));
    }

    /**
     * 
     * OneBulletShoot Builder.
     *
     */
    public static class Builder {
        private Optional<ShootState> next = Optional.empty();
        private Optional<ShootState> previous = Optional.empty();

        /**
         * 
         * @param nextState
         *      the next state.
         * @return Builder.
         */
        public Builder nextState(final ShootState nextState) {
            this.next = Objects.requireNonNull(Optional.of(nextState));
            return this;
        }

        /**
         * 
         * @param previousState
         *      the previous state.
         * @return Builder.
         */
        public Builder previoustState(final ShootState previousState) {
            this.previous = Objects.requireNonNull(Optional.of(previousState));
            return this;
        }

        /**
         * 
         * @return the ShootState.
         */
        public ShootState build() {
            return new OneBulletShoot(this.next, this.previous);
        }
    }
}
