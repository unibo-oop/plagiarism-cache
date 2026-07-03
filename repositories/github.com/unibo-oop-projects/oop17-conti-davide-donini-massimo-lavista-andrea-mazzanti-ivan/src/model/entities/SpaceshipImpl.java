package model.entities;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import model.ModelImpl;
import model.entities.properties.DirectionX;
import model.entities.properties.DirectionY;
import model.entities.powerup.PowerUpType;
import model.entities.properties.Position;
import model.entities.properties.PositionImpl;
import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;
import model.shootstate.ShootState;
import model.shootstate.FourBulletsShoot;
import model.shootstate.OneBulletShoot;
import model.shootstate.ThreeBulletsShoot;
import model.shootstate.TwoBulletsShoot;
import model.utilities.StaticVelocity;

/**
 * Represent the player.
 */
public class SpaceshipImpl extends AbstractCharacter implements Spaceship {

    private static final double INITIAL_BULLET_DAMAGE = 25.0;
    private static final double FINAL_BULLET_DAMAGE = 100.0;
    private static final double AVERAGE_DAMAGE_BULLET = FINAL_BULLET_DAMAGE / 2;
    private static final int MAX_NUM_BULLET_DAMAGE_COLLECTED = 4;
    private static final int INITIAL_FIRE_RATE = 400;
    private static final int FINAL_FIRE_RATE = 100;
    private static final int MAX_NUM_FIRE_RATE_COLLECTED = 8;
    private static final double LIFE_POWER_UP = 1.0;
    private static final int DURATION_SHIELD = 6000;
    private static final double INIT_POSITION_AXIS_X = 600.0 * 3 / 7;
    private static final double INIT_POSITION_AXIS_Y = 500.0;
    private static final double INIT_VELOCITY_AXIS_X = 0.0;
    private static final double INIT_VELOCITY_AXIS_Y = 0.0;
    private static final double RADIUS_SHAPE = 7.0;
    private static final double INIT_LIFE = 3.0;
    private static final double MAX_LIFE = 5.0;
    private static final double DAMAGE_COLLISION_DAMAGE = 100.0;
    private static final double TRANSFORMATION_FROM_MILLISECOND_TO_SECOND = 0.001;
    private static final int LOWER_EXTREME_LIMIT = 0;
    private static final int MILLISECOND_IN_A_MINUT = 60000;
    private static final double MOVEMENT_VELOCITY = 330.0;
    private static final double MARGIN_0F_ERROR = 0.1;

    private double bulletDamage;
    private int fireRate;
    private boolean shield;
    private int shieldCounterElapsed;
    private boolean fire;
    private ShootState shootState;
    private Pair<DirectionX, DirectionY> direction;

    /**
     * The constructor of the class SpaceshipImpl.
     * 
     * @param shape
     *            initial entity's shape.
     * @param velocity
     *            initial entity's velocity.
     * @param life
     *            initial entity's life.
     * @param maxLife
     *            maximum life that the entity can take on.
     * @param collisionDamage
     *            damage issued from the collision with other entity.
     */
    public SpaceshipImpl(final Shape shape, final Velocity velocity, final double life, final double maxLife,
            final double collisionDamage) {
        super(shape, velocity, life, maxLife, collisionDamage);
        this.bulletDamage = INITIAL_BULLET_DAMAGE;
        this.fireRate = INITIAL_FIRE_RATE;
        this.direction = new ImmutablePair<DirectionX, DirectionY>(DirectionX.STOP, DirectionY.STOP);
        this.fire = false;
        this.shield = false;
        this.shieldCounterElapsed = 0;
        this.setBulletShootState();
    }

    /**
     * This constructor instantiate the spaceship with default values.
     */
    public SpaceshipImpl() {
        this(new Circle(INIT_POSITION_AXIS_X, INIT_POSITION_AXIS_Y, RADIUS_SHAPE),
                new VelocityImpl(INIT_VELOCITY_AXIS_X, INIT_VELOCITY_AXIS_Y), INIT_LIFE, MAX_LIFE,
                DAMAGE_COLLISION_DAMAGE);
    }

    private void setBulletShootState() {
        this.shootState = new OneBulletShoot.Builder().build();
        final ShootState twoBulletShoot = new TwoBulletsShoot.Builder().previoustState(this.shootState).build();
        final ShootState threeBulletShoot = new ThreeBulletsShoot.Builder().previoustState(twoBulletShoot).build();
        final ShootState fourBulletShoot = new FourBulletsShoot.Builder().previoustState(threeBulletShoot).build();
        this.shootState.setNextState(twoBulletShoot);
        twoBulletShoot.setNextState(threeBulletShoot);
        threeBulletShoot.setNextState(fourBulletShoot);
    }

    @Override
    public final List<Bullet> shoot() {
        super.setCounterElapsed(0);
        return this.shootState.shoot(super.getPosition(), this.bulletDamage);
    }

    @Override
    public final int getFireRate() {
        return this.fireRate;
    }

    @Override
    public final void setFireRate(final int fireRate) {
        this.fireRate = fireRate;
    }

    @Override
    public final void pickUpPowerUp(final PowerUpType powerUp) {
        switch (powerUp) {
        case LIFE:
            super.incrementLife(LIFE_POWER_UP);
            break;
        case DAMAGE:
            final double increaseDamage = (FINAL_BULLET_DAMAGE - INITIAL_BULLET_DAMAGE)
                    / MAX_NUM_BULLET_DAMAGE_COLLECTED;
            if (this.bulletDamage < FINAL_BULLET_DAMAGE) {
                this.bulletDamage = this.bulletDamage + increaseDamage < FINAL_BULLET_DAMAGE - MARGIN_0F_ERROR
                        ? this.bulletDamage + increaseDamage
                        : FINAL_BULLET_DAMAGE;
            } else {
                this.shootState.getNextState().ifPresent(nextState -> {
                    this.shootState = nextState;
                    this.bulletDamage = AVERAGE_DAMAGE_BULLET;
                });
            }
            break;
        case DEFENCE:
            this.shield = true;
            this.shieldCounterElapsed = 0;
            break;
        case RATE_OF_FIRE:
            final int increaseRateOfFire = (INITIAL_FIRE_RATE - FINAL_FIRE_RATE) / MAX_NUM_FIRE_RATE_COLLECTED;
            if (this.fireRate > FINAL_FIRE_RATE) {
                this.fireRate = this.fireRate - increaseRateOfFire > FINAL_FIRE_RATE
                        ? this.fireRate - increaseRateOfFire
                        : FINAL_FIRE_RATE;
            }
            break;
        default:
            break;
        }
    }

    @Override
    public final boolean canShoot() {
        return this.fire && super.getCounterElapsed() >= this.fireRate;
    }

    @Override
    public final void update(final int time) {
        final Velocity velocity = this.getVelocity().mul(TRANSFORMATION_FROM_MILLISECOND_TO_SECOND * time);
        final Position spaceshipPosition = this.getPosition();
        super.setPosition(new PositionImpl(
                this.computeCoordinate(spaceshipPosition.getX(), velocity.getX(), ModelImpl.GAME_WIDTH),
                this.computeCoordinate(spaceshipPosition.getY(), velocity.getY(), ModelImpl.GAME_HEIGHT)));
        if (super.getCounterElapsed() < this.fireRate) {
            super.setCounterElapsed(super.getCounterElapsed() + time);
        }
        if (this.shield) {
            this.shieldCounterElapsed += time;
            if (this.shieldCounterElapsed >= DURATION_SHIELD) {
                this.shield = false;
            }
        }
    }

    private Double computeCoordinate(final double coordinate, final double velocityCoordinate,
            final int upperExtremeLimit) {
        final Circle circle = (Circle) super.getShape();
        return coordinate + velocityCoordinate < circle.getRadius() ? LOWER_EXTREME_LIMIT + circle.getRadius()
                : coordinate + velocityCoordinate > upperExtremeLimit - circle.getRadius()
                        ? upperExtremeLimit - circle.getRadius()
                        : coordinate + velocityCoordinate;
    }

    @Override
    public final void decreaseLife(final double decrease) {
        if (!this.shield) {
            super.decreaseLife(decrease);
            this.shootState.getPreviosState().ifPresent(previousState -> {
                this.shootState = previousState;
                this.bulletDamage = FINAL_BULLET_DAMAGE;
            });
        }
    }

    @Override
    public final boolean isActiveShield() {
        return this.shield;
    }

    @Override
    public final void setFire(final boolean fire) {
        this.fire = fire;
    }

    @Override
    public final boolean isFiring() {
        return this.fire;
    }

    @Override
    public final double getSpaceshipDamage() {
        return this.bulletDamage;
    }

    @Override
    public final int getBulletFiredForMinute() {
        return MILLISECOND_IN_A_MINUT / this.fireRate;
    }

    @Override
    public final Pair<DirectionX, DirectionY> getDirection() {
        return this.direction;
    }

    @Override
    public final void setDirectionX(final DirectionX directionX) {
        this.direction = new ImmutablePair<>(directionX, this.direction.getRight());
        this.setVelocity();
    }

    @Override
    public final void setDirectionY(final DirectionY directionY) {
        this.direction = new ImmutablePair<>(this.direction.getLeft(), directionY);
        this.setVelocity();
    }

    private void setVelocity() {
        final DirectionX directionX = this.direction.getLeft();
        final DirectionY directionY = this.direction.getRight();
        if (directionX != DirectionX.STOP && directionY != DirectionY.STOP) {
            final double velocity = StaticVelocity.diagonalVelocity(MOVEMENT_VELOCITY);
            this.setVelocity(
                    new VelocityImpl(velocity * directionX.getUnitVector(), velocity * directionY.getUnitVector()));
        } else {
            this.setVelocity(new VelocityImpl(MOVEMENT_VELOCITY * directionX.getUnitVector(),
                    MOVEMENT_VELOCITY * directionY.getUnitVector()));
        }
    }

}
