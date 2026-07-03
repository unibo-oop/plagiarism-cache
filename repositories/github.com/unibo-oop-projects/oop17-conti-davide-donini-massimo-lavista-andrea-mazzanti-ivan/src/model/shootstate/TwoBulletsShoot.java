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
import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;
import model.utilities.StaticVelocity;

/**
 * 
 * Implements behavior associated with a state: this behavior consists in firing two bullets spaced 5 degrees.
 *
 */
public final class TwoBulletsShoot extends AbstractShoot {

    private static final int DEGREE_DISTANCE = 5;

    private TwoBulletsShoot(final Optional<ShootState> nextState, final Optional<ShootState> previousState) {
        super();
        nextState.ifPresent(state -> super.setNextState(state));
        previousState.ifPresent(state -> super.setPreviosState(state));
    }

    @Override
    public List<Bullet> shoot(final Position spaceshipPosition, final double bulletDamage) {
        final Velocity bulletSpeed = new VelocityImpl(0, BULLET_SPEED);
        final Velocity lowerBullet = StaticVelocity.velocityByDegree(bulletSpeed, -DEGREE_DISTANCE);
        final Velocity upperBullet = StaticVelocity.velocityByDegree(bulletSpeed, DEGREE_DISTANCE);
        return Arrays.asList(new BulletImpl(new Circle(spaceshipPosition.getX(), spaceshipPosition.getY(), 
                RADIUS_BULLET_SHAPE), lowerBullet, bulletDamage, BulletType.FRIENDLY), 
                new BulletImpl(new Circle(spaceshipPosition.getX(), spaceshipPosition.getY(),
                        RADIUS_BULLET_SHAPE), upperBullet, bulletDamage, BulletType.FRIENDLY));
    }

    /**
     * 
     * TwoBulletsShoot Builder.
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
            return new TwoBulletsShoot(this.next, this.previous);
        }
    }
}
