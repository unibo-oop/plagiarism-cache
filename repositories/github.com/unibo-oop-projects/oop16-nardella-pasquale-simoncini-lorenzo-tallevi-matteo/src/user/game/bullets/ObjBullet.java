package user.game.bullets;

import user.enums.GameSprites;
import user.enums.Objects;
import user.game.effects.ObjEffectEmitter;

import zengine.core.GameObject;

/**
 * This class represents a generic bullet.
 */
public abstract class ObjBullet extends GameObject {

    private static final double MAX_DAMAGE = 6;

    // the speed of the bullet
    private double speed;
    private double damage;

    @Override
    public void update() {
        // move according to my speed and angle
        moveX(z().lengthDirX(speed, getImageAngle()));
        moveY(z().lengthDirY(speed, getImageAngle()));

        // make hitbox follow the bullet
        moveHitboxToCurrentPosition();

        // destroys bullet when goes outside of the room
        if (isOutsideRoom()) {
            z().instanceDestroy(this);
        }
    }

    @Override
    public void destroy() {
        final ObjEffectEmitter ef = (ObjEffectEmitter) z().instanceCreate(this.getX(), this.getY(),
                Objects.EFFECT_EMITTER.getValue());
        ef.createSmallExplosion();
    }

    @Override
    public void draw() {
        drawSelf();
    }

    /**
     * This method returns the damage of the bullet.
     * 
     * @return the damage
     */
    public double getDamage() {
        return damage;
    }

    /**
     * This method sets the damage of the bullet.
     * 
     * @param damage
     *            the damage to set
     */
    public void setDamage(final double damage) {
        this.damage = damage;
        if (damage > 2 && damage < MAX_DAMAGE) {
            this.setSpriteIndex(GameSprites.BULLET_2.getValue());
        } else if (damage == MAX_DAMAGE) {
            this.setSpriteIndex(GameSprites.BULLET_3.getValue());
        }
    }

    /**
     * This method returns the bullet's speed.
     * 
     * @return bullet's speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * This method sets the bullet's speed.
     * 
     * @param speed
     *            the speed to set
     */
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

}
