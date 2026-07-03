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
 * Implements behavior associated with a state: this behavior consists in firing four bullets.
 *
 */
public final class FourBulletsShoot extends AbstractShoot {

    private static final int DEGREE_DISTANCE1 = 2;
    private static final int DEGREE_DISTANCE2 = 20;

    private FourBulletsShoot(final Optional<ShootState> nextState, final Optional<ShootState> previousState) {
        super();
        nextState.ifPresent(state -> super.setNextState(state));
        previousState.ifPresent(state -> super.setPreviosState(state));
    }

    @Override
    public List<Bullet> shoot(final Position spaceshipPosition, final double bulletDamage) {
        final Velocity bulletSpeed = new VelocityImpl(0, BULLET_SPEED);
        final Velocity lowerBullet1 = StaticVelocity.velocityByDegree(bulletSpeed, -DEGREE_DISTANCE1);
        final Velocity upperBullet1 = StaticVelocity.velocityByDegree(bulletSpeed, DEGREE_DISTANCE1);
        final Velocity lowerBullet2 = StaticVelocity.velocityByDegree(bulletSpeed, -DEGREE_DISTANCE2);
        final Velocity upperBullet2 = StaticVelocity.velocityByDegree(bulletSpeed, DEGREE_DISTANCE2);
        return Arrays.asList(new BulletImpl(new Circle(spaceshipPosition.getX(), spaceshipPosition.getY(),
                        RADIUS_BULLET_SHAPE), lowerBullet1, bulletDamage, BulletType.FRIENDLY), 
                        new BulletImpl(new Circle(spaceshipPosition.getX(), spaceshipPosition.getY(),
                                RADIUS_BULLET_SHAPE), upperBullet1, bulletDamage, BulletType.FRIENDLY),
                                new BulletImpl(new Circle(spaceshipPosition.getX(), spaceshipPosition.getY(),
                                        RADIUS_BULLET_SHAPE), lowerBullet2, bulletDamage, BulletType.FRIENDLY), 
                                        new BulletImpl(new Circle(spaceshipPosition.getX(), 
                                                spaceshipPosition.getY(), RADIUS_BULLET_SHAPE), upperBullet2, 
                                                bulletDamage, BulletType.FRIENDLY));
    }

    /**
     * 
     * FourBulletsShoot Builder.
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
            return new FourBulletsShoot(this.next, this.previous);
        }
    }

}
