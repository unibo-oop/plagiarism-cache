package it.unibo.oop.crossline.game.bullet;

import com.badlogic.gdx.math.Vector2;
import it.unibo.oop.crossline.game.actor.Actor;

/**
 * This class is used when there's a need of creating bullets in the game world.
 */
public class BulletBuilderImpl implements BulletBuilder {

    private static final float DEFAULT_DAMAGE = 10f;
    private static final Vector2 DEFAULT_DIRECTION = Vector2.Zero;
    private static final Vector2 DEFAULT_POSITION = Vector2.Zero;
    private static final float DEFAULT_SIZE = 0.1f;
    private static final float DEFAULT_SPEED = 2f;

    private float damage = DEFAULT_DAMAGE;
    private Vector2 direction = DEFAULT_DIRECTION;
    private Actor owner;
    private float size = DEFAULT_SIZE;
    private float speed = DEFAULT_SPEED;
    private Vector2 position = DEFAULT_POSITION;

    @Override
    public final Bullet build() {
        if (owner == null) {
            throw new IllegalArgumentException();
        }
        return new BulletImpl(damage, direction, owner, position, size, speed);
    }

    @Override
    public final BulletBuilder setDamage(final float damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public final BulletBuilder setDirection(final Vector2 direction) {
        this.direction = direction;
        return this;
    }

    @Override
    public final BulletBuilder setOwner(final Actor owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public final BulletBuilder setPosition(final Vector2 position) {
        this.position = position;
        return this;
    }

    @Override
    public final BulletBuilder setSize(final float size) {
        this.size = size;
        return this;
    }

    @Override
    public final BulletBuilder setSpeed(final float speed) {
        this.speed = speed;
        return this;
    }

}
