package controller;

import java.util.List;

import model.bonus.Bonus;
import model.enemy.Enemy;
import model.entities.Character;
import model.palace.Palace;

/**
 * 
 * Interface to control the main functions of entities.
 *
 */

public interface EntityController {

    /**
     * 
     * Change the status of all windows in the palace.
     * 
     * @param palace The palace on which to change the state of the windows
     * @param stuntman The Stuntman.
     * 
     */
    void switchPalaceStatus(Palace palace, Character stuntman);

    /**
     * Spawn a Hawk that crosses the map.
     * 
     * @param hawks The list of Hawk.
     */
    void spawnHawk(List<Enemy> hawks);

    /**
     * Spawn a Jar that crosses the map.
     * 
     * @param palace The palace on witch spawn the vase.
     * @param vases  The list of Vase.
     */
    void spawnVase(Palace palace, List<Enemy> vases);

    /**
     * Spawn a LifeBonus at a random point on the map.
     * @param palace The palace
     * @param bonus The list of Bonus.
     */
    void spawnBonus(Palace palace, List<Bonus> bonus);
}
