package model.ai;

import java.io.Serializable;
import java.util.Collection;

import model.entities.Bullet;
import model.hitbox.HitboxImpl;
import model.hitbox.Hitbox;
import model.hitbox.HitboxCircle;

/**
 * 
 */
public interface AI extends Serializable {

    /**
     * @param currentHealth
     *            actual character's health
     */
    void decide(int currentHealth);

    /**
     * Manage the shoot of the character using this AI.
     * 
     * @param from
     *            origin position
     * @param delta
     *            delta movement
     * @param fireRate
     *            rate of fire
     * @param damage
     *            damage dealt by the bullet
     * @param range
     *            range when the bullet disappear
     * @param steps
     *            bullet's speed
     * @return a collection of bullets
     */
    Collection<Bullet> shoot(Hitbox from, double delta, double fireRate, double damage, double range, double steps);

    /**
     * Manage the movement of the character using this AI.
     * 
     * @param h
     *            origin position
     * @param steps
     *            character's movement speed
     * @param delta
     *            delta movement
     * @return character's new position
     */
    HitboxImpl move(HitboxCircle h, double steps, double delta);

}
