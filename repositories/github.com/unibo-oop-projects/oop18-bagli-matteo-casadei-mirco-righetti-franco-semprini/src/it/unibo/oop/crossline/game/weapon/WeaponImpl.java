package it.unibo.oop.crossline.game.weapon;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import it.unibo.oop.crossline.game.actor.Actor;
import it.unibo.oop.crossline.game.bullet.BulletBuilder;

/**
 * This class is the implementation of a weapon, used by an {@link it.unibo.oop.crossline.game.actor.Actor} to shoot in the game.
 */
public class WeaponImpl implements Weapon {

    private final BulletBuilder bulletBuilder;
    private long lastShot = TimeUtils.millis();
    private Actor owner;
    private final long shotDelay;
    private Vector2 direction = new Vector2(0, 0);

    /**
     * Initialize the weapon.
     * @param shotDelay the delay between shots
     * @param bulletbuilder the {@link it.unibo.oop.crossline.game.bullet.BulletBuilder} used to spawn bullets
     */
    public WeaponImpl(final long shotDelay, final BulletBuilder bulletbuilder) {
        this.shotDelay = shotDelay;
        this.bulletBuilder = bulletbuilder;
    }

    @Override
    public final boolean canShoot() {
        return (TimeUtils.millis() - lastShot) > shotDelay;
    }

    @Override
    public final BulletBuilder getBulletFactory() {
        return bulletBuilder;
    }

    @Override
    public final Vector2 getDirection() {
        return direction;
    }

    @Override
    public final void setDirection(final Vector2 direction) {
        this.direction = direction;
    }

    @Override
    public final void setOwner(final Actor owner) {
        this.owner = owner;
    }

    @Override
    public final void shoot() {
        final Vector2 ownerPosition = owner.getBody().getPosition().cpy();
        final Vector2 bulletPosition = ownerPosition.add(direction.nor()); // We should get the owner size and use that to set the correct position
        bulletBuilder.setOwner(owner)
                     .setPosition(bulletPosition)
                     .setDirection(direction)
                     .build();
        lastShot = TimeUtils.millis();
    }

}
