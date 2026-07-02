package ballblast.model.powerups;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import ballblast.model.components.CollisionComponent;
import ballblast.model.components.GravityComponent;
import ballblast.model.components.MovementComponent;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.CollisionTag;
import ballblast.model.physics.handlers.PowerCollisionHandler;

/**
 * Factory to create {@link Power}.
 */
public final class PowerFactoryImpl implements PowerFactory {

    private static final Random RANDOM = new Random();
    private static final List<PowerTypes> POWERS = ImmutableList.copyOf(PowerTypes.values());
    private static final Map<PowerTypes, Action> POWER_MAP;

    static {
        POWER_MAP = ImmutableMap.of(
            PowerTypes.DOUBLEFIRE, PowerFactoryImpl::createDoubleFirePower,
            PowerTypes.SHIELD,     PowerFactoryImpl::createShieldPower,
            PowerTypes.SPEED,      PowerFactoryImpl::createSpeedPower
        );
    }

    @Override
    public Power createPower(final Vector2D velocity, final Coordinate position, final CollisionManager collisionManager) {
        return POWER_MAP.get(this.getRandomPowerType()).apply(velocity, position, collisionManager);
    }

    private static Power createShieldPower(final Vector2D velocity, final Coordinate position,
            final CollisionManager collisionManager) {
        return new ShieldPower.Builder()
                .setVelocity(velocity)
                .setPosition(position)
                .setCollisionHandler(new PowerCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.POWERUP))
                .addComponent(new MovementComponent())
                .addComponent(new GravityComponent())
                .build();
    }

    private static Power createDoubleFirePower(final Vector2D velocity, final Coordinate position,
            final CollisionManager collisionManager) {
        return new DoubleFirePower.Builder()
                .setVelocity(velocity)
                .setPosition(position)
                .setCollisionHandler(new PowerCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.POWERUP))
                .addComponent(new MovementComponent())
                .addComponent(new GravityComponent())
                .build();
    }

    private static Power createSpeedPower(final Vector2D velocity, final Coordinate position,
            final CollisionManager collisionManager) {
        return new SpeedPower.Builder()
                .setVelocity(velocity)
                .setPosition(position)
                .setCollisionHandler(new PowerCollisionHandler())
                .addComponent(new CollisionComponent(collisionManager, CollisionTag.POWERUP))
                .addComponent(new MovementComponent())
                .addComponent(new GravityComponent())
                .build();
    }

    private PowerTypes getRandomPowerType() {
        return POWERS.get(RANDOM.nextInt(POWERS.size()));
    }

    @FunctionalInterface
    private interface Action {
        Power apply(Vector2D velocity, Coordinate position, CollisionManager collisionManager);
    }

}
