package it.unibo.isaccoop.model.player;

import java.util.List;
import java.util.Optional;

import it.unibo.isaccoop.controller.input.InputController;
import it.unibo.isaccoop.graphics.GraphicsComponent;
import it.unibo.isaccoop.graphics.factory.ConcretePlayerGraphicsComponentFactory;
import it.unibo.isaccoop.model.action.HitStrategy;
import it.unibo.isaccoop.model.action.ShootingHitStrategy;
import it.unibo.isaccoop.model.common.Direction;
import it.unibo.isaccoop.model.common.Vector2D;
import it.unibo.isaccoop.model.enemy.Enemy;
import it.unibo.isaccoop.model.enemy.Hitable;
import it.unibo.isaccoop.model.weapon.BaseWeaponShot;
import it.unibo.isaccoop.model.weapon.TimeIntervalWeapon;
import it.unibo.isaccoop.model.weapon.WeaponShot;

/**
 * The class for the player.
 * */
public class Player extends PlayerMovementImpl implements Hitable<Enemy> {

    private final InputController movementController;
    private final InputController shootingController;

    private final HitStrategy hitStrategy;

    /**
     * Player constructor.
     * @param moveController
     * @param shotController
     * @param gr
     */
    public Player(final InputController moveController, final InputController shotController, final GraphicsComponent gr) {
        super(gr);
        this.hitStrategy = new ShootingHitStrategy(new TimeIntervalWeapon(super.getTears(),
                (start, direction) -> new BaseWeaponShot(start, direction,
                        new ConcretePlayerGraphicsComponentFactory().getPlayerBaseWeaponShotGraphicsComponent())));
        this.movementController = moveController;
        this.shootingController = shotController;
    }

    /**
     * Move the player along passed direction.
     * @param direction the direction in which the player moves
     * */
    void move(final Direction direction) {
        super.update(direction);
    }

    /**
     * Perform hit action delegating to the player hit strategy.
     * @param direction the direction in which the bullet is fired
     * */
    public void hit(final Direction direction) {
        final Vector2D direct = new Vector2D(direction.getX(), direction.getY());
        this.hitStrategy.hit(Optional.of(direct), this);
    }

    /**
     * Get player weapon shots if available.
     *
     * @return weapon shots list or empty list if shots not available
     * */
    public List<WeaponShot> getWeaponShots() {
        return ((ShootingHitStrategy) this.hitStrategy).getWeaponShots();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void onHit(final Enemy enemy) {
        enemy.setHearts(this.getDamage());
    }

    /**
     * Get movement controller.
     * @return the movement controller
     */
    public InputController getMovementController() {
        return this.movementController;
    }

    /**
     * Get shooting controller.
     * @return the shooting controller
     */
    public InputController getShootingController() {
        return this.shootingController;
    }

    /**
     * Remove a player shot, delegating remove to the hit strategy.
     * @param element the shot to remove
     * */
    public void removePlayerShot(final WeaponShot element) {
        ((ShootingHitStrategy) this.hitStrategy).removeShot(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTears(final Double tears) {
        super.setTears(tears);
        ((ShootingHitStrategy) this.hitStrategy).updateWeaponInterval(super.getTears());
    }
}
