package it.unibo.isaccoop.model.collision;

import it.unibo.isaccoop.model.enemy.Enemy;
import it.unibo.isaccoop.model.item.Item;
import it.unibo.isaccoop.model.weapon.WeaponShot;

/**
 * EventFactory interface which represents the game event factory concept.
 * */
public interface EventFactory {

    /**
     * Get a new event when a pickup is made.
     *
     * @param target item picked up
     * @return event created
     * */
    Event getItemPickUpEvent(Item target);

    /**
     * Get a new event when an enemy is shot.
     *
     * @param enemy player who fired
     * @return event created
     * */
    Event getEnemyShotEvent(Enemy enemy);

    /**
     * Get a new event when a player is shot.
     *
     * @param enemy enemy that hit
     * @return event created
     * */
    Event getEnemyHitEvent(Enemy enemy);

    /**
     *  Get a new event when a shot is colliding with the room boundaries.
     *
     *  @param shot shot colliding
     *  @return event created
     * */
    Event getShotToRemoveEvent(WeaponShot shot);

}
