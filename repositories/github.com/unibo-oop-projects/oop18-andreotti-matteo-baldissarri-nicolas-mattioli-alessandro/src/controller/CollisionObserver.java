package controller;

import java.util.List;

import model.bonus.Bonus;
import model.enemy.Enemy;
import model.entities.Character;
import model.palace.Floor;

/**
 * Manages the collisions.
 */
public interface CollisionObserver {

    /**
     * Check if the stuntman is over a closed window.
     * 
     * @param stuntman The Stuntman
     * @param floors   The floors list of the Palace
     */
    void collideWithClosedWindow(Character stuntman, List<Floor> floors);

    /**
     * Check if the stuntman collides with an enemy.
     * 
     * @param stuntman The Stuntman
     * @param enemy    The enemy with which detect the collision
     */
    void collideWithEnemy(Character stuntman, Enemy enemy);

    /**
     * Check if the stuntman collides with an enemy.
     * 
     * @param stuntman The stuntman
     * @param bonus    The bonus with which detect the collision
     */
    void collideWithBonus(Character stuntman, Bonus bonus);

}
